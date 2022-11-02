package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityCafeBinding


class cafe : AppCompatActivity() {
    private lateinit var binding: ActivityCafeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCafeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list3 = listOf(f_cafe_paris(),f_cafe_juicy(),f_cafe_tospia(),f_cafe_dayday(), f_cafe_zoo())
        val pagerAdapter = FragmentPagerAdapter(list3,this)
        binding.viewpagercafe.adapter = pagerAdapter
    }
}