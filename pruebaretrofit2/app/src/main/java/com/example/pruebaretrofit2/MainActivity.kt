package com.example.pruebaretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.suspendCoroutine


class MainActivity : AppCompatActivity() {

    private val apiService = RetrofitClient.retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonAboutUs = findViewById<Button>(R.id.AboutUs)

        botonAboutUs.setOnClickListener {

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val respuesta = obtenerAboutUs()

                    val text = findViewById<TextView>(R.id.tvtext)
                    text.text = respuesta.texto
                } catch (e: Exception) {
                    val text = findViewById<TextView>(R.id.tvtext)
                    text.text = e.message
                    println(e.message)
                }
            }
        }

    }

    private suspend fun obtenerAboutUs(): AboutUs {
        return suspendCoroutine { continuation ->
            val call = apiService.getAboutUs()
            call.enqueue(object : Callback<AboutUs> {
                override fun onResponse(call: Call<AboutUs>, response: Response<AboutUs>) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()
                        continuation.resumeWith(Result.success(respuesta!!))
                    } else {
                        // Manejar error de la API
                        continuation.resumeWith(Result.failure(Exception("Error de la API")))
                        val text = findViewById<TextView>(R.id.tvtext)
                        text.text = "Error de la API"
                    }
                }

                override fun onFailure(call: Call<AboutUs>, t: Throwable) {
                    // Manejar error de conexión
                    continuation.resumeWith(Result.failure(t))
                }
            })
        }
    }


}
