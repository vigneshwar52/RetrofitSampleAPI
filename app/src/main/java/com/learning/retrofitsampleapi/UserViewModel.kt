package com.learning.retrofitsampleapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.retrofitsampleapi.Api.RetrofitInstance
import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.Models.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _postsUser: MutableLiveData<List<User>> = MutableLiveData();
    val postUser : LiveData<List<User>> get() = _postsUser

    fun fetchUserData(){
        viewModelScope.launch {
            val fetchDetails = RetrofitInstance.retrofit.getUserPost()
            Log.i(TAG, "fetchUserData: $fetchDetails")
            _postsUser.value = fetchDetails
        }
    }

    companion object private val TAG = UserViewModel::class.java.simpleName
}