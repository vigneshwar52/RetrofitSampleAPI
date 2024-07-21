package com.learning.retrofitsampleapi.Api

import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.Models.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("posts")
    suspend fun getPosts( @Query("_page") page: Int = 1, @Query("_limit") limit: Int = 10) : List<Post>

    @GET("users")
    suspend fun getUserPost():List<User>
}