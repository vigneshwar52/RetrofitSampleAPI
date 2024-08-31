package com.learning.retrofitsampleapi.Edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.retrofitsampleapi.Api.RetrofitInstance
import com.learning.retrofitsampleapi.Models.Post
import kotlinx.coroutines.launch
import kotlin.math.log

private val TAG = EditViewModel::class.java.simpleName
class EditViewModel :ViewModel() {
    private val fetchPost: MutableLiveData<Post?> = MutableLiveData()
    val storePost: LiveData<Post?> = fetchPost

    private val _currentStatus = MutableLiveData<ResultStatus>(ResultStatus.IDLE)
    val currentStatus: LiveData<ResultStatus> = _currentStatus

    private val _deletionSuccess = MutableLiveData<Boolean>(false)
    val deletionSucess:LiveData<Boolean> = _deletionSuccess

    fun updatePost(postId: Int, newPostData: Post) {
        viewModelScope.launch {
            try {
                fetchPost.value = null
                _currentStatus.value = ResultStatus.WORKING
                Log.i(TAG, "updatePost: $postId")
                val response = RetrofitInstance.Api.updatePost(postId,newPostData)
                Log.i(TAG, "updatePost: ${newPostData.body}")
                fetchPost.value = response
                _currentStatus.value = ResultStatus.SUCCESS
            }catch(e:Exception){
                Log.e(TAG, "updatePost: ",e )
                _currentStatus.value = ResultStatus.ERROR
            }
        }
    }

    fun patchPost(postId: Int,title:String,body:String){
        viewModelScope.launch {
            try {
                fetchPost.value = null
                _currentStatus.value = ResultStatus.WORKING
                val response = RetrofitInstance.Api.patchPost(postId, mapOf("" to title,"" to body))
                fetchPost.value = response
                _currentStatus.value = ResultStatus.SUCCESS
            }catch (e:Exception){
                e.printStackTrace()
                _currentStatus.value = ResultStatus.ERROR
            }
        }
    }

    fun deletePost(postId: Int){
        viewModelScope.launch {
            try{
                _currentStatus.value = ResultStatus.WORKING
                Log.d(TAG, "deletePost: $postId")
                RetrofitInstance.Api.deletePost("1234AuthToken",postId)
                fetchPost.value = null
                _deletionSuccess.value = true
                _currentStatus.value = ResultStatus.SUCCESS
            }catch (e:Exception){
                e.printStackTrace()
                _currentStatus.value = ResultStatus.ERROR
            }
        }
    }
}