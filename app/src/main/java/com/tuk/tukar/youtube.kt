package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_promotion.*
import kotlinx.android.synthetic.main.activity_youtube.*

class youtube : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        webView8.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView8.loadUrl("https://www.youtube.com/c/%ED%95%9C%EA%B5%AD%EA%B3%B5%ED%95%99%EB%8C%80%ED%95%99%EA%B5%90")
    }
    override fun onBackPressed() {
        if (webView8.canGoBack())
        {
            webView8.goBack()
        }
        else
        {
            finish()
        }
    }
    }
