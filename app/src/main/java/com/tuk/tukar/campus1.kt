package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_campus1.*

class campus1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campus1)
        webView6.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView6.loadUrl("https://www.tukorea.ac.kr/tukorea/1085/subview.do")
    }
    override fun onBackPressed() {
        if (webView6.canGoBack())
        {
            webView6.goBack()
        }
        else
        {
            finish()
        }
    }
  }
