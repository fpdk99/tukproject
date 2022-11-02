package com.tuk.tukar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityShuttlebusBinding

class shuttlebus : AppCompatActivity() {
    private lateinit var binding:ActivityShuttlebusBinding
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           binding=ActivityShuttlebusBinding.inflate(layoutInflater)
           setContentView(binding.root)

           val list4 = listOf(f_shuttletime(),f_shuttleplace(),f_streetview())
           val pagerAdapter = FragmentPagerAdapter(list4,this)
           binding.shuttlepager.adapter = pagerAdapter

           binding.button4.setOnClickListener {
               val intent = Intent(this, subway::class.java)
               startActivity(intent)
           }
           binding.button13.setOnClickListener {
               val intent = Intent(this, subway_suin::class.java)
               startActivity(intent)
           }

       }
}