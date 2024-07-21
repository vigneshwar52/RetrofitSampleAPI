package com.learning.retrofitsampleapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.retrofitsampleapi.Models.User
import com.learning.retrofitsampleapi.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var userAdapter: AdapterSampleUser
    private var userNew = mutableListOf<User>()
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = AdapterSampleUser(this@UserActivity,userNew)
        binding.userRecView.adapter = userAdapter
        binding.userRecView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.fetchUser.setOnClickListener {
            viewModel.fetchUserData()
        }

        viewModel.postUser.observe(this, Observer { user->
            userAdapter.updateUserData(user)
        })

        binding.back.setOnClickListener {
            finish()
        }
    }
}