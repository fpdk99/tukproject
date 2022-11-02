package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityGuideBinding
import com.tuk.tukar.databinding.ActivityGuideInfoBinding
import kotlinx.android.synthetic.main.activity_guide_info.*

class guide_info : AppCompatActivity() {
    private lateinit var binding:ActivityGuideInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button32.setOnClickListener {
            val intent = Intent(this, campus1::class.java)
            startActivity(intent)
        }
        binding.button31.setOnClickListener {
            val intent = Intent(this, telephone::class.java)
            startActivity(intent)
        }
        binding.button29.setOnClickListener {
            val intent = Intent(this, guide_info_way::class.java)
            startActivity(intent)
        }
        binding.button33.setOnClickListener {
            val intent = Intent(this, marketing::class.java)
            startActivity(intent)
        }


    }
}