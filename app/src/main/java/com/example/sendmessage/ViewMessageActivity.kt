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
 * Extrae el objeto [Message] (Parcelable) de los extras del [Intent] mediante la clave
 * [SendMessagesActivity.KEY_MESSAGE] y despliega en pantalla tanto el remitente como el mensaje.
 *  @author Andrey Udodov
 *  @version 1.0
 */
class ViewMessageActivity : AppCompatActivity() {

    /**
     * Objeto de compañía con constantes utilizadas en [ViewMessageActivity].
     */
    companion object {
        /** Etiqueta para los mensajes de depuración en LogCat. */
        const val TAG = "LogViewMessageActivity"
    }

    /**
     * Función de ciclo de vida que inicializa el diseño, ajusta los insets de ventana (edge-to-edge)
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
            intent.getParcelableExtra(SendMessagesActivity.KEY_MESSAGE, Message::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(SendMessagesActivity.KEY_MESSAGE) as? Message
        }

        message?.let {
            tvUser.text = getString(R.string.tv_user, "${it.sender.name} ${it.sender.surname}")
            tvMessage.text = it.content
        }

        Log.d(TAG, "ViewMessageActivity -> onCreate()")
    }

    //region Ciclo de Vida de una Actividad
    /** Función llamada cuando la actividad se vuelve visible para el usuario. */
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "ViewMessageActivity -> onStart()")
    }

    /** Función llamada cuando la actividad comienza a interactuar con el usuario. */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ViewMessageActivity -> onResume()")
    }

    /** Función llamada cuando la actividad pierde el foco pero sigue siendo visible. */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "ViewMessageActivity -> onPause()")
    }

    /** Función llamada cuando la actividad ya no es visible para el usuario. */
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ViewMessageActivity -> onStop()")
    }

    /** Función llamada antes de que la actividad sea destruida. */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ViewMessageActivity -> onDestroy()")
    }
    //endregion
}