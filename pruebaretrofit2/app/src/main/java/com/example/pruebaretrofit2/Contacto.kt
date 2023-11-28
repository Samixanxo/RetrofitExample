package com.example.pruebaretrofit2

import com.google.gson.annotations.SerializedName

data class Contacto (
    @SerializedName("id") val id: Int,
    @SerializedName("text") val texto: String
)