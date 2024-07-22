package com.learning.retrofitsampleapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.retrofitsampleapi.Adapters.PostAdapter
import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter
    private var newPost = mutableListOf<Post>()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //To Initialize and Configure recyclerView Adapter
        adapter = PostAdapter(this, listOf())
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)

        binding.button.setOnClickListener {
            viewModel.fetchAndLogPosts()
        }

        viewModel.posts.observe(this, Observer { posts ->
            Log.i(TAG, "Number of posts: ${posts.size}")
            adapter.updatePosts(posts)
        })

        binding.user.setOnClickListener {
            val intent = Intent(this@MainActivity,UserActivity::class.java)
            startActivity(intent)
        }
    }
}