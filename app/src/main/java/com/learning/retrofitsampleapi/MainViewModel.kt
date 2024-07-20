package com.learning.retrofitsampleapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.retrofitsampleapi.Api.RetrofitInstance
import com.learning.retrofitsampleapi.Models.Post
import kotlinx.coroutines.launch

class MainViewModel :ViewModel() {
    private val api = RetrofitInstance.retrofit
    private lateinit var PostAdapter:PostAdapter

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }
    private val _posts:MutableLiveData<List<Post>> = MutableLiveData();
    val posts :LiveData<List<Post>> get() = _posts

    private val _isLoading = MutableLiveData(false)
    val isLoading :LiveData<Boolean> get() = _isLoading

    fun fetchAndLogPosts(page: Int = 1, limit: Int = 10) {
        viewModelScope.launch {
            try {
                val fetchedPosts = RetrofitInstance.retrofit.getPosts(page, limit)
                Log.i(TAG, "Number of posts fetched: ${fetchedPosts.size}")
                _posts.value = fetchedPosts
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching posts", e)
            }
        }
    }
}