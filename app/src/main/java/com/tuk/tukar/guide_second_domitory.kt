package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityGuideSecondDomitoryBinding

class guide_second_domitory : AppCompatActivity() {
    private lateinit var binding3: ActivityGuideSecondDomitoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding3 = ActivityGuideSecondDomitoryBinding.inflate(layoutInflater)
        setContentView(binding3.root)

        binding3.imageView4.setOnClickListener {
            val intent = Intent(this, guide_tip::class.java)
            startActivity(intent)
        }
        binding3.imageView6.setOnClickListener {
            val intent = Intent(this, guide_e_engineering::class.java)
            startActivity(intent)
        }
    }
}