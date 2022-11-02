package com.tuk.tukar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuk.tukar.databinding.ActivityGuideFacilityBinding

class guide_facility: AppCompatActivity() {
    private lateinit var binding: ActivityGuideFacilityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGuideFacilityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list4 = listOf(f_bookstore(),f_convenience(),f_convenience2(),f_laundry(), fac_sports(),f_copy())
        val pagerAdapter = FragmentPagerAdapter(list4,this)
        binding.viewpagercon.adapter = pagerAdapter
    }
}