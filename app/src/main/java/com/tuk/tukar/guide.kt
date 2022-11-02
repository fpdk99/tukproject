package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityGuideBinding


class guide : AppCompatActivity() {
    private lateinit var binding: ActivityGuideBinding
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.imageView13.setOnClickListener {
                val intent = Intent(this, guide_vr::class.java)
                startActivity(intent)
            }
            binding.button25.setOnClickListener {
                val intent = Intent(this, guide_tip::class.java)
                startActivity(intent)
            }
            binding.button27.setOnClickListener {
                val intent = Intent(this, guide_e_engineering::class.java)
                startActivity(intent)
            }
            binding.button26.setOnClickListener {
                val intent = Intent(this, guide_second_domitory::class.java)
                startActivity(intent)
            }
            binding.button28.setOnClickListener {
                val intent = Intent(this, guide_library::class.java)
                startActivity(intent)
            }
            binding.button22.setOnClickListener {
                val intent = Intent(this, guide_climb::class.java)
                startActivity(intent)
            }
            binding.button24.setOnClickListener {
                val intent = Intent(this, guide_info::class.java)
                startActivity(intent)
            }
            binding.button30.setOnClickListener {
                val intent = Intent(this, guide_facility::class.java)
                startActivity(intent)
            }




    }
}