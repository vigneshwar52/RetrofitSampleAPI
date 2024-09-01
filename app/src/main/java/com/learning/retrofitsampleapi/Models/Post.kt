package com.learning.retrofitsampleapi.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Post(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String
    ) : Parcelable