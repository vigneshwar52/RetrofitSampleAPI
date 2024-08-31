package com.learning.retrofitsampleapi.Api

import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.Models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

public interface ApiServices {
    @GET("posts")
    suspend fun getPosts(@Query("_page") page: Int = 1, @Query("_limit") limit: Int = 10): List<Post>

    @GET("posts")
    suspend fun getAll() : List<Post>

    @GET("posts/{id}")
    suspend fun getDetailPost(@Path("id") postid: Int):Post

    @GET("users")
    suspend fun getUserPost():List<User>

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") userId:Int):User

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId:Int, @Body post:Post):Post

    @PATCH("posts/{id}")
    suspend fun patchPost(@Path("id") postId: Int,@Body params: Map<String,String>):Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Header("AuthToken") auth:String,@Path("id") postId: Int)

    @GET("posts")
    fun getPostsByCallBack(@Query("_page") page: Int = 1, @Query("_limit") limit: Int = 10): Call<List<Post>>

    @GET("users")
    fun getUserPostByCallBack():Call<List<User>>

}