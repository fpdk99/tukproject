package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.tuk.tukar.databinding.NonMemberMenuBinding
import com.unity3d.player.UnityPlayerActivity

class Non_member_menu: AppCompatActivity() {
    private lateinit var binding: NonMemberMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NonMemberMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button10.setOnClickListener {
            val intent = Intent(this,Arnavigationsearch::class.java)
            startActivity(intent)
        }
        binding.button11.setOnClickListener {
            val intent = Intent(this, UnityPlayerActivity::class.java)
            startActivity(intent)
        }
        binding.button12.setOnClickListener {
            val intent = Intent(this, guide::class.java)
            startActivity(intent)
        }
        binding.button5.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        binding.button14.setOnClickListener {
            val intent = Intent(this, restaurant::class.java)
            startActivity(intent)
        }
        binding.button15.setOnClickListener {
                val intent = Intent(this, major::class.java)
                startActivity(intent)
            }
    }

}
