package com.learning.retrofitsampleapi.Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.retrofitsampleapi.Api.RetrofitInstance
import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.Models.User
import kotlinx.coroutines.launch
import retrofit2.HttpException

private val TAG = DetailViewModel::class.java.simpleName
class DetailViewModel:ViewModel(){
    private val fetchDetailPost = MutableLiveData<Post>()
    val storeFetchedPostData: LiveData<Post> = fetchDetailPost

    private val fetchDetailUser = MutableLiveData<User>()
    val storeFetchedUser:LiveData<User> = fetchDetailUser

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading:MutableLiveData<Boolean> = _isLoading

    fun getDetails(postId: Int){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val postDetailResponse = RetrofitInstance.Api.getDetailPost(postId)
                Log.i(TAG, "getDetails: $postDetailResponse")
                fetchDetailPost.value = postDetailResponse
            }catch (e:Exception){
                Log.e(TAG, "getDetails: ", e)
            }finally {
                _isLoading.value = false
            }
        }
    }
    fun getUserDetails(userId: Int){
        val userId = userId%10
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val userDetailResponse = RetrofitInstance.Api.getUserDetail(userId)
                Log.i(TAG, "getDetails: $userDetailResponse")
                fetchDetailUser.value = userDetailResponse
            }catch (e: HttpException) {
                // Handle HTTP errors separately if needed
                when (e.code()) {
                    404 -> Log.e(TAG, "User not found: $userId")
                    else -> Log.e(TAG, "HTTP error occurred: ${e.message}")
                }
            }
            catch (e:Exception){
                Log.e(TAG, "getDetails: ", e)
            }finally {
                _isLoading.value = false
            }
        }
    }
}