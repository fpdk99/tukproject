package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityGuideEengineeringBinding

class guide_e_engineering : AppCompatActivity() {
    private lateinit var binding4: ActivityGuideEengineeringBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding4 = ActivityGuideEengineeringBinding.inflate(layoutInflater)
        setContentView(binding4.root)

        binding4.imageView10.setOnClickListener {
            val intent = Intent(this, guide_second_domitory::class.java)
            startActivity(intent)
        }
        binding4.imageView11.setOnClickListener {
            val intent = Intent(this, guide_library::class.java)
            startActivity(intent)
        }
    }
}