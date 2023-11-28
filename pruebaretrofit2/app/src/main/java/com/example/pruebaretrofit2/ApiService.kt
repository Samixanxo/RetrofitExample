package com.example.pruebaretrofit2

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("AboutUs")
    fun getAboutUs(): Call<AboutUs>

    @GET("Contacto")
    fun getContacto(): Call<Contacto>
}