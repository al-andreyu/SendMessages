package com.example.sendmessage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sendmessage.model.Message

/**
 * Pantalla secundaria que recibe y muestra el mensaje enviado desde [SendMessagesActivity].
 *
 * Extrae el objeto [Message] (Serializable) de los extras del [Intent] mediante la clave
 * [SendMessagesActivity.KEY_MESSAGE] y despliega en pantalla tanto el remitente como el mensaje.
 */
class ViewMessageActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LogViewMessageActivity"
    }

    /**
     * Método de ciclo de vida que inicializa el diseño, ajusta los insets de ventana (edge-to-edge)
     * y obtiene el mensaje del [Intent] para mostrarlo en pantalla.
     *
     * @param savedInstanceState Estado guardado de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_message)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvUser = findViewById<TextView>(R.id.tvUser)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)

        val message = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(SendMessagesActivity.KEY_MESSAGE, Message::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(SendMessagesActivity.KEY_MESSAGE) as? Message
        }

        message?.let {
            tvUser.text = getString(R.string.tv_user, "${it.sender.name} ${it.sender.surname}")
            tvMessage.text = it.content
        }

        Log.d(TAG, "ViewMessageActivity -> onCreate()")
    }

    //region Ciclo de Vida de una Actividad
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "ViewMessageActivity -> onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ViewMessageActivity -> onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "ViewMessageActivity -> onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ViewMessageActivity -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ViewMessageActivity -> onDestroy()")
    }
    //endregion
}