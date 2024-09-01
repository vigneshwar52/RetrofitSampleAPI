package com.learning.retrofitsampleapi.Detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.learning.retrofitsampleapi.Edit.EditViewActivity
import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.R
import com.learning.retrofitsampleapi.databinding.ActivityDetailBinding

const val POST = "EDIT_POST"
const val POST_ID = "POST_ID"
class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private val TAG = "DetailedActivity"
    private var Post: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val postId = intent.getIntExtra(POST_ID,-1)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.isLoading.observe(this, Observer { isLoading->
            binding.detailProgressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
            binding.clContent.visibility = if(isLoading) View.GONE else View.VISIBLE
        })

        viewModel.storeFetchedPostData.observe(this, Observer { post->
            Post = post
            binding.tvPostId.text = "Post #${post.id}"
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body
        })

        viewModel.storeFetchedUser.observe(this, Observer { user->
            binding.tvUserName.text = user.name
            binding.tvUsername.text = user.username
            binding.tvUserEmail.text = user.email
            binding.tvWebsite.text = user.website
        })

        viewModel.getDetails(postId)
        viewModel.getUserDetails(postId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.miEdit){
            Log.d(TAG, "onOptionsItemSelected: ")
            viewModel.storeFetchedPostData.observe(this, Observer { post->
                val intent = Intent(this@DetailActivity,EditViewActivity::class.java)
                Log.d(TAG, "onOptionsItemSelected: ")
                intent.putExtra(POST,post)
                startActivity(intent)
            })
        }
        return super.onOptionsItemSelected(item)
    }
}