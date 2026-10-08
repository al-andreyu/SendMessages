# Registro de Cambios (Changelog)

Todos los cambios notables en este proyecto serán documentados en este archivo.

---

## [v1.1] - 2026-10-08

### Corregido
- La pantalla principal se dibujaba por debajo de la barra de estado: se añadió `android:fitsSystemWindows="true"` a la raíz `LinearLayout` de `activity_send_messages.xml`, de modo que `SendMessagesActivity` aplica los insets de las barras del sistema (estado y navegación) igual que ya hacía `ViewMessageActivity` en código. El tema (`values-v23/themes.xml`) declara barras transparentes edge-to-edge y con `targetSdk 37` el sistema fuerza ese modo en Android 15+.

### Cambiado
- Actualización de la documentación: `README.md` describe ahora el manejo de insets/edge-to-edge y se ha creado `AGENTS.md` con los comandos, la arquitectura y las convenciones del repositorio.

---

## [v1.0] - 2025-09-25

### Añadido
- Paso de datos funcional entre `SendMessagesActivity` y `ViewMessageActivity` mediante un `Intent` y un `Bundle` usando la clave `KEY_MESSAGE`.
- Documentación del código mediante comentarios **KDoc** en todas las clases Kotlin (`SendMessageApplication.kt`, `SendMessagesActivity.kt`, `ViewMessageActivity.kt`).
- Creación de la documentación del proyecto: `README.md`, `CHANGELOG.md` y `MANUAL_USUARIO.md`.
- Recursos de texto adicionales en `res/values/strings.xml` para evitar textos hardcodeados.

### Cambiado
- Estandarización de la nomenclatura de IDs en `activity_view_message.xml` siguiendo la nomenclatura húngara en camelCase (`tvTitle`, `ivImage`, `tvMessage`).
- Reemplazo de texto hardcodeado `"TextView"` por la referencia a recurso `@string/tv_message`.
- Centralización de dimensiones de texto en `res/values/dimens.xml` (`tvTitle_textSize` y `tvMessage_textSize`).

---

## [v0.1] - 2025-09-25

### Añadido
- Configuración inicial del proyecto Android con soporte para Kotlin.
- Creación de la actividad principal `SendMessagesActivity` con su layout correspondiente `activity_send_messages.xml`.
- Creación de la actividad secundaria `ViewMessageActivity` con su layout correspondiente `activity_view_message.xml`.
- Configuración de colores, fuentes y estilos básicos del tema.
- Integración de `enableEdgeToEdge()` para compatibilidad con las barras del sistema.
