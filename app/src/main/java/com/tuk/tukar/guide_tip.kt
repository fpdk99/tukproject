package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityGuideTipBinding

class guide_tip : AppCompatActivity() {

    private lateinit var binding2: ActivityGuideTipBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityGuideTipBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        binding2.imageView9.setOnClickListener {
            val intent = Intent(this, guide_second_domitory::class.java)
            startActivity(intent)
        }
        binding2.imageView8.setOnClickListener {
            val intent = Intent(this, guide_climb::class.java)
            startActivity(intent)
        }
    }
}