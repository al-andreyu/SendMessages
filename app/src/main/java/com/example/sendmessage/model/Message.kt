package com.example.sendmessage.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Representa un **mensaje** enviado entre dos personas ([Person]) dentro de la aplicación.
 *
 * Esta clase almacena la información del mensaje así como el remitente (*sender*) y el destinatario (*receiver*).
 *
 * @property id Identificador único del mensaje.
 * @property content Contenido de texto del mensaje.
 * @property sender Objeto [Person] que representa a la persona que envía el mensaje.
 * @property receiver Objeto [Person] que representa a la persona que recibe el mensaje.
 * @author Andrey Udodov
 * @version 1.0
 * @see Person
 */
@Parcelize
data class Message(val id: Int, val content: String, val sender: Person, val receiver: Person) : Parcelable