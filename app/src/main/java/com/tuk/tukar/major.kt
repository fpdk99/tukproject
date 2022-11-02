package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tuk.tukar.databinding.ActivityMajorBinding

class major : AppCompatActivity() {
    private lateinit var binding: ActivityMajorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMajorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(frag_computer_engineering(),frag_mechanical_engineering(),frag_design_engineering(),frag_mechanical_design_engineering(),
            frag_advancedmaterials_engineering(),frag_electronics_engineering(),frag_business_administration(),frag_nano_engineering(),frag_biochemical_engineering(),
            frag_energy_engineering(),frag_game_engineering(), frag_mechatronics_engineering())
        val pagerAdapter = FragmentPagerAdapter(list,this)
        binding.viewpagermajor.adapter = pagerAdapter
    }
}

class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
                                                            : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount()= fragmentList.size
    override fun createFragment(position: Int)= fragmentList.get(position)
}