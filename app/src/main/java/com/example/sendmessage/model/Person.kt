package com.example.sendmessage.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Representa a una persona dentro de la aplicación.
 *
 * Sus datos identifican al participante que envía o recibe un **mensaje**.
 *
 * @property dni Documento nacional de identidad de la persona.
 * @property name Nombre de la persona.
 * @property surname Apellido de la persona.
 * @author Andrey Udodov
 * @version 1.0
 */
@Parcelize
data class Person(val dni: String, val name: String, val surname: String) : Parcelable