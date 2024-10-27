package com.example.fetchsubmission

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object RetrofitClient {
    private val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

    private val client = OkHttpClient.Builder().also { client ->
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
    }.build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}


object ApiClient {
    val apiService =  RetrofitClient.retrofit.create(APIRequest::class.java)
}


