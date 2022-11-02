package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_campus1.*
import kotlinx.android.synthetic.main.activity_guide_vr.*

class guide_vr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_vr)
        webViewVr.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webViewVr.loadUrl("http://asis.tukorea.ac.kr/kpu/Camp_1/index.html")
    }
    override fun onBackPressed() {
        if (webViewVr.canGoBack())
        {
            webViewVr.goBack()
        }
        else
        {
            finish()
        }
    }
}