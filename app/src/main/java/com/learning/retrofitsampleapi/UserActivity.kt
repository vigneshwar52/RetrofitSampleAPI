package com.learning.retrofitsampleapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.retrofitsampleapi.Models.User
import com.learning.retrofitsampleapi.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var userAdapter: AdapterSampleUser
    private var userNew = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = AdapterSampleUser(this@UserActivity,userNew)
        binding.userRecView.adapter = userAdapter
        binding.userRecView.layoutManager = LinearLayoutManager(this)



        binding.back.setOnClickListener {
            finish()
        }
    }
}