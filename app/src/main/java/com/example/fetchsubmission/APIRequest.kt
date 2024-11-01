package com.example.fetchsubmission

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface APIRequest {
    @Streaming
    @GET("{endpoint}")
    fun fetchData(@Path("endpoint") endpoint: String): Call<ResponseBody>
}