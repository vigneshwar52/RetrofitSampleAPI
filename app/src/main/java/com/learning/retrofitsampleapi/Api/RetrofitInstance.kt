package com.learning.retrofitsampleapi.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
object RetrofitInstance {

     val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
    /*val Api:ApiServices by lazy{
        retrofit.create(ApiServices::class.java)
    }*/
}