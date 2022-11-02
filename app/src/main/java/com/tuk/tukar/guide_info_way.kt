package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_guide_info_way.*
import kotlinx.android.synthetic.main.activity_telephone.*

class guide_info_way : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_info_way)
        webView11.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView11.loadUrl("http://www.tukorea.ac.kr/tukorea/1134/subview.do")
    }
    override fun onBackPressed() {
        if (webView11.canGoBack())
        {
            webView11.goBack()
        }
        else
        {
            finish()
        }

    }
}