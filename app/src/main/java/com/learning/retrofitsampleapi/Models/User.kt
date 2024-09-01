package com.learning.retrofitsampleapi.Models

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id:Int,
    val name:String,
    val username:String,
    val email:String,
    val website:String
//    val company:Company
) : Parcelable {
}