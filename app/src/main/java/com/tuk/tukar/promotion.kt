package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_promotion.*

class promotion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion)
        webView7.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView7.loadUrl("https://www.tukorea.ac.kr/tukorea/1113/subview.do")
    }
    override fun onBackPressed() {
        if (webView7.canGoBack())
        {
            webView7.goBack()
        }
        else
        {
            finish()
        }
    }
 }