package com.example.pruebaretrofit2

import com.google.gson.annotations.SerializedName

data class AboutUs (
    @SerializedName("id") val id: Int,
    @SerializedName("text") val texto: String)
