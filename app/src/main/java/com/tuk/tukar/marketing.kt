package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityMarketingBinding

class marketing : AppCompatActivity() {
    private lateinit var binding: ActivityMarketingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        binding = ActivityMarketingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView74.setOnClickListener {
            val intent = Intent(this, youtube::class.java)
            startActivity(intent)
        }
        binding.imageView81.setOnClickListener {
            val intent = Intent(this, insta_official::class.java)
            startActivity(intent)
        }
        binding.imageView82.setOnClickListener {
            val intent = Intent(this, insta_domi::class.java)
            startActivity(intent)
        }
        binding.imageView83.setOnClickListener {
            val intent = Intent(this, insta_club::class.java)
            startActivity(intent)
        }
        binding.imageView84.setOnClickListener {
            val intent = Intent(this, promotion::class.java)
            startActivity(intent)
        }
    }
}