package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.MemberMenuBinding
import com.unity3d.player.UnityPlayerActivity


class Member_menu : AppCompatActivity() {
    private lateinit var binding: MemberMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MemberMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, Arnavigationsearch::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, UnityPlayerActivity::class.java)
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            val intent = Intent(this, major::class.java)
            startActivity(intent)
        }
        binding.button7.setOnClickListener {
            val intent = Intent(this, restaurant::class.java)
            startActivity(intent)
        }
        binding.button8.setOnClickListener {
            val intent = Intent(this, guide::class.java)
            startActivity(intent)
        }
        binding.button9.setOnClickListener {
            val intent = Intent(this, shuttlebus::class.java)
            startActivity(intent)
        }
    }
}