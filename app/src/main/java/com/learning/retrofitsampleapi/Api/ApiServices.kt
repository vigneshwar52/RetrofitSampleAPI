package com.learning.retrofitsampleapi.Api

import com.learning.retrofitsampleapi.Models.Post
import retrofit2.http.GET

interface ApiServices {
    @GET("posts")
    suspend fun getPosts():List<Post> //suspend means it should be called from coroutines
}