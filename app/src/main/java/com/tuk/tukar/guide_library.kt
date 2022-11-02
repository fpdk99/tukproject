package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityGuideLibraryBinding

class guide_library : AppCompatActivity() {
    private lateinit var binding: ActivityGuideLibraryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button18.setOnClickListener {
            val intent = Intent(this, guide_library_2::class.java)
            startActivity(intent)
        }
        binding.button19.setOnClickListener {
            val intent = Intent(this, guide_library_3::class.java)
            startActivity(intent)
        }
        binding.button20.setOnClickListener {
            val intent = Intent(this, guide_ebook::class.java)
            startActivity(intent)
        }
        binding.imageView43.setOnClickListener {
            val intent = Intent(this, guide_e_engineering::class.java)
            startActivity(intent)
        }
        binding.imageView42.setOnClickListener {
            val intent = Intent(this, guide_climb::class.java)
            startActivity(intent)
        }

    }

}