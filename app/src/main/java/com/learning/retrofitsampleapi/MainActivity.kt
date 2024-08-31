package com.learning.retrofitsampleapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.retrofitsampleapi.Adapters.PostAdapter
import com.learning.retrofitsampleapi.Detail.DetailActivity
import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.databinding.ActivityMainBinding

const val POST_ID = "POST_ID"
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter

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
        adapter = PostAdapter(this, listOf(),object :PostAdapter.ItemClickListener{
            override fun onItemClick(post: Post) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(POST_ID,post.id)
                startActivity(intent)
            }
        })
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)

        viewModel.isLoading.observe(this, Observer { isLoading ->
            Log.i(TAG, "isLoading $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.fetchError.observe(this) { errorFetch ->
            if(errorFetch==null) {
                binding.tvError.visibility = View.GONE
            }else {
                binding.tvError.visibility = View.VISIBLE
                Toast.makeText(this,errorFetch,Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.posts.observe(this, Observer { posts ->
            Log.i(TAG, "Number of posts: ${posts.size}")
            val size = posts.size
            adapter.updatePosts(posts)
            binding.rvPosts.smoothScrollToPosition(size)
        })

        viewModel.fetchAndLogPosts()

        binding.button.setOnClickListener {
            viewModel.fetchAndLogPosts()
        }

        binding.user.setOnClickListener {
            val intent = Intent(this@MainActivity,UserActivity::class.java)
            startActivity(intent)
        }
    }
}