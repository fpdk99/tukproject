package com.tuk.tukar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.tuk.tukar.databinding.ActivityGuideClimbBinding
import kotlinx.android.synthetic.main.activity_guide_climb.*

class guide_climb : AppCompatActivity() {
    private lateinit var binding: ActivityGuideClimbBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideClimbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView48.setOnClickListener {
            val intent = Intent(this, guide_library::class.java)
            startActivity(intent)
        }
        binding.imageView49.setOnClickListener {
            val intent = Intent(this, guide_tip::class.java)
            startActivity(intent)
        }
        val VIDEO_PATH = "android.resource://" + packageName + "/" + R.raw.climbing
        var uri: Uri = Uri.parse(VIDEO_PATH)
        videoView.setVideoURI(uri)
        videoView.setMediaController(MediaController(this))// 없으면 에러
        videoView.requestFocus() // 준비하는 과정을 미리함

    }
}