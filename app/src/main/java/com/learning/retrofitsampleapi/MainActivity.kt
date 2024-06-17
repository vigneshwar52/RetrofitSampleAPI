package com.learning.retrofitsampleapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.learning.retrofitsampleapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

//    private lateinit var button: Button
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "onCreate: check")

//        button = findViewById(R.id.button)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.button.setOnClickListener {
            viewModel.getPosts()
        }
    }

}