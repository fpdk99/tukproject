package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityRestaurantBinding

class restaurant : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button21.setOnClickListener {
            val intent = Intent(this, studentfood::class.java)
            startActivity(intent)
        }
        binding.button23.setOnClickListener {
            val intent = Intent(this, efood::class.java)
            startActivity(intent)
        }
    binding.button16.setOnClickListener {
        val intent = Intent(this, food::class.java)
        startActivity(intent)
    }
        binding.imageView38.setOnClickListener {
            val intent = Intent(this, food::class.java)
            startActivity(intent)
        }
        binding.button17.setOnClickListener {
            val intent = Intent(this, cafe::class.java)
            startActivity(intent)
        }
        binding.imageView37.setOnClickListener {
            val intent = Intent(this, cafe::class.java)
            startActivity(intent)
        }
    }
}