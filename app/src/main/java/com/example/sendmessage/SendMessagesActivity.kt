package com.example.sendmessage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.sendmessage.model.Message
import com.example.sendmessage.model.Person

/**
 * Esta es la primera Actividad de la aplicación que realiza las operaciones:
 * <ol>
 *     <li>Crear un componente <code>EditText</code> y <code>Button</code> en XML </li>
 *     <li>Lanzar un evento en un componente Visual</li>
 *     <li>El ciclo de vida de la <code>Activity</code></li>
 *     <li>Ver la pila de Actividades</li>
 * </ol>
 * @author Andrey Udodov
 * @version 1.0
 * @see android.widget.Button
 * @see android.widget.EditText
 * @see Intent
 * @see android.os.Bundle
 */
class SendMessagesActivity : AppCompatActivity() {
    lateinit var etMessageText: EditText
    lateinit var btSend: Button

    /**
     * Clave utilizada para pasar el mensaje como extra en el [Intent].
     */
    companion object {
        const val KEY_MESSAGE = "KEY_MESSAGE"
        const val TAG = "LogSendMessagesActivity"
    }

    /**
     * Método llamado al crear la actividad. Se encarga de inicializar la interfaz de usuario,
     * enlazar los componentes visuales y configurar los eventos de clic.
     *
     * Como medida de aprendizaje, aquí se muestra cómo pasar datos dato a dato utilizando un [Bundle]:
     * ```kotlin
     * val intent = Intent(this, ViewMessageActivity::class.java)
     * val bundle = Bundle()
     * bundle.putString("KEY_MESSAGE", etMessageText.text.toString())
     * intent.putExtras(bundle)
     * startActivity(intent)
     * ```
     *
     * @param savedInstanceState Estado guardado previamente de la actividad, si lo hubiera.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_messages)

        etMessageText = findViewById(R.id.etMessageText)
        btSend = findViewById(R.id.btSend)

        btSend.setOnClickListener {
            sendMessage()
        }

        // Se escriben mensajes de depuración en la consola LogCat
        Log.d(TAG, "SendMessagesActivity -> onCreate()")
    }

    /**
     * Función que crea un mensaje con la información de la persona que envía y de la persona
     * que recoge el mensaje
     */
    private fun sendMessage() {
        // 1. Crear el intent
        val intent = Intent(this, ViewMessageActivity::class.java)

        // 2. Crear el bundle
        val bundle = Bundle()

        // 3. La información del mensaje
        val sender = Person("12345678A", "Juan", "Pérez")
        val receiver = Person("87654321B", "María", "López")

        val message = Message(1, etMessageText.text.toString(), sender, receiver)
        bundle.putSerializable(KEY_MESSAGE, message)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    //region Ciclo de Vida de una Actividad
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SendMessagesActivity -> onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SendMessagesActivity -> onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SendMessagesActivity -> onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SendMessagesActivity -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SendMessagesActivity -> onDestroy()")
    }
    //endregion
}