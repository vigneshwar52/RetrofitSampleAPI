package com.learning.retrofitsampleapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.retrofitsampleapi.Adapters.PostAdapter
import com.learning.retrofitsampleapi.Api.RetrofitInstance
import com.learning.retrofitsampleapi.Models.Post
import kotlinx.coroutines.launch

class MainViewModel :ViewModel() {
    private val api = RetrofitInstance.retrofit
    private lateinit var PostAdapter: PostAdapter

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }
    private val _posts:MutableLiveData<List<Post>> = MutableLiveData();
    val posts :LiveData<List<Post>> = _posts

    private var _isLoading = MutableLiveData(false)
    val isLoading :LiveData<Boolean> = _isLoading
    private var currentPage:Int = 1;

    private var _error = MutableLiveData<String?>(null)
    val fetchError: LiveData<String?> = _error

    fun fetchAndLogPosts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val fetchedPosts = RetrofitInstance.Api.getPosts(currentPage)
                currentPage += 1;
                Log.i(TAG, "Number of posts fetched: ${fetchedPosts.size}")
                val currentPosts = _posts.value ?: emptyList()
                _posts.value = currentPosts + fetchedPosts

                /*//Get all Posts
                val fetchPosts = RetrofitInstance.Api.getAll()
                _posts.value = fetchPosts*/
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching posts", e)
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}