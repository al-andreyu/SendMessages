# AGENTS.md

Proyecto Android/Kotlin de un solo módulo (`:app`), MVC con Activities y XML Views, sin backend.
Todo el material del repo está en **español** (KDoc, `README.md`, `CHANGELOG.md`, `MANUAL_USUARIO.md`, mensajes de commit): escribe en español salvo que el usuario pida lo contrario.

## Comandos (wrapper de Gradle, nunca un Gradle instalado)

```bash
./gradlew :app:lintDebug                    # lint Android (verificado: pasa)
./gradlew :app:testDebugUnitTest            # tests unitarios JVM (verificado: pasa)
./gradlew :app:testDebugUnitTest --tests "com.example.sendmessage.ExampleUnitTest"   # un solo test
./gradlew :app:connectedDebugAndroidTest    # tests instrumentados (requiere dispositivo/emulador)
./gradlew dokkaGenerate                     # regenera KDoc en documentation/
./gradlew :app:installDebug                 # instala en el dispositivo conectado
```

- Verificación mínima antes de dar un cambio por terminado: `lintDebug` → `testDebugUnitTest`.
- No hay ktlint/detekt/spotless: la única regla de estilo es `kotlin.code.style=official` en `gradle.properties`.
- Los tests existentes son solo los de plantilla (`ExampleUnitTest`, `ExampleInstrumentedTest`): no hay cobertura real del flujo de mensajes.
- `local.properties` (con `sdk.dir`) es obligatorio y está gitignored; lo genera Android Studio.
- El daemon de Gradle exige JDK 25 (`gradle/gradle-daemon-jvm.properties`, toolchains foojay). Compatibilidad de código: Java 11.
- Configuration cache activado (`org.gradle.configuration-cache=true`).

## Arquitectura y hechos no obvios

- Módulo único `:app`, paquete `com.example.sendmessage`. Dependencias y plugins: version catalog `gradle/libs.versions.toml` (AGP 9.4.1, Kotlin 2.4.20, Dokka 2.2.0).
- Flujo real: `SendMessagesActivity` (launcher) → `Intent` explícito + `Bundle` con clave `SendMessagesActivity.KEY_MESSAGE` → `ViewMessageActivity`. El payload es `model/Message` (`@Parcelize`, con `Person` sender/receiver).
- **`res/navigation/nav_graph.xml` es una plantilla muerta**: referencia `FirstFragment`/`SecondFragment` y layouts `fragment_first`/`fragment_second` que no existen, y no hay ningún `NavHostFragment` en los layouts. La navegación es solo por Intents. No lo "arregles" ni lo conectes sin que lo pidan; el README menciona Navigation Component pero no está cableada.
- `viewBinding` está habilitado, pero el código existente usa `findViewById`: sigue el patrón existente.
- **i18n**: ningún texto hardcodeado en layouts/código; todo va a `res/values/strings.xml` (español, por defecto) **y** `res/values-en/strings.xml` (inglés). Al añadir una cadena, actualiza ambos ficheros (`app_name` es `translatable="false"`). Dimensiones centralizadas en `res/values/dimens.xml` con cualificadores (`land`, `w600dp`, `w1240dp`).
- Convención de IDs de vistas (húngara en camelCase): prefijos `tv` (TextView), `et` (EditText), `bt` (Button), `iv` (ImageView).
- KDoc en todas las clases/funciones públicas; Dokka documenta todas las visibilidades (incl. private/internal).

## Documentación generada y CI

- `documentation/` es **salida generada de Dokka y está commiteada**: no la edites a mano; se regenera con `./gradlew dokkaGenerate` (produce diffs).
- CI: `.github/workflows/desplegar-dokka.yml` ejecuta `dokkaGenerate` y publica `documentation/` en GitHub Pages en cada push a `main` (usa Java 17, no el toolchain local).
- Al modificar `README.md`, valídalo con el script del skill:
  `python3 .opencode/skills/personalice-docs-generator/scripts/validate_readme.py README.md` (verificado: pasa)
- Skills disponibles en `.opencode/skills/`: `personalice-docs-generator` (KDoc/README) y `generar-fichero-license`.

## Gotchas de git

- `.gitignore` solo ignora `/build`: `app/release/` (APK) y `.idea/*` parcialmente **no** están ignorados — no commitees artefactos de build ni APKs.
- `resources/svetze.otf` en la raíz es un duplicado de `app/src/main/res/font/svetze.otf` (el que usa la app).
- La librería MaterialAbout se resuelve vía JitPack (añadido en `settings.gradle.kts`); `RepositoriesMode.FAIL_ON_PROJECT_REPOS` — no añadas repositorios en `app/build.gradle.kts`.
