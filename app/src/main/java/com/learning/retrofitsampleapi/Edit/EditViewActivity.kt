package com.learning.retrofitsampleapi.Edit

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.learning.retrofitsampleapi.Detail.POST_ID
import com.learning.retrofitsampleapi.MainActivity
import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.databinding.ActivityEditViewBinding

class EditViewActivity : AppCompatActivity() {
    companion object{
       private const val POST = "EDIT_POST"
    }
    private lateinit var viewModel: EditViewModel
    private lateinit var binding: ActivityEditViewBinding
    private val TAG = "EditActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditViewBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        Log.d(TAG, "onCreate: EditActivity")
        val post: Post? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(POST,Post::class.java) as Post
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(POST)
        }

        title = "Editing Post #${post?.id}"

        binding.etTitle.setText(post?.title)
        binding.etContent.setText(post?.body)

        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)

        viewModel.storePost.observe(this, Observer { updatedPost ->
            if(updatedPost == null){
                binding.clPostResult.visibility = View.GONE
                return@Observer
            }
            binding.tvUpdatedTitle.text = updatedPost.title
            binding.tvUpdatedContent.text = updatedPost.body
            binding.clPostResult.visibility = View.VISIBLE
        })

        viewModel.currentStatus.observe(this, Observer { currentStatus ->
            when (currentStatus) {
                ResultStatus.IDLE -> {
                    binding.tvStatus.text = "Idle"
                    binding.tvStatus.setTextColor(Color.GRAY)
                }
                ResultStatus.WORKING -> {
                    binding.tvStatus.text = "Working..."
                    binding.tvStatus.setTextColor(Color.MAGENTA)
                }
                ResultStatus.SUCCESS -> {
                    binding.tvStatus.text = "Success!"
                    binding.tvStatus.setTextColor(Color.GREEN)
                }
                ResultStatus.ERROR -> {
                    binding.tvStatus.text = "Error :("
                    binding.tvStatus.setTextColor(Color.RED)
                }
                else -> {
                    throw IllegalStateException("Unexpected result state found")
                }
            }
        })
        viewModel.deletionSucess.observe(this, Observer { wasDeletionSuccessful ->
            if (wasDeletionSuccessful) {
                Toast.makeText(this, "Deleted post successfully!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })
        binding.btnUpdatePut.setOnClickListener {
            Log.i(TAG, "Update via PUT")
            if (post != null) {
                viewModel.updatePost(post.id,
                    Post(
                        post.userId,
                        post.id,
                        binding.etTitle.text.toString(),
                        binding.etContent.text.toString()
                    )
                )
            }
        }
        binding.btnUpdatePatch.setOnClickListener(){
            Log.i(TAG, "Update Via Patch")
            if (post!=null){
                viewModel.patchPost(post.id,post.title,post.body)
            }
        }
        binding.btnDelete.setOnClickListener(){
            Log.i(TAG,"Delete Post")
            if(post!=null){
                viewModel.deletePost(post.id)
            }
        }

    }

}