package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tuk.tukar.databinding.ActivityFoodBinding


class food : AppCompatActivity() {
    private lateinit var binding: ActivityFoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list2 = listOf(f_food_student(),f_food_tip1(),f_food_s_og(),f_food_s_nadri(),f_food_s_tomato(),f_food_raon(),f_food_sinbul(),f_food_olivegreen(),f_food_ukuya(),f_food_hansdeli(),f_food_e_edupersonal())
        val pagerAdapter = FragmentPagerAdapter(list2,this)
        binding.viewPagerfood.adapter = pagerAdapter
    }
}