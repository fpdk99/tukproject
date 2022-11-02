package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_subway.*

class subway : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subway)
        webView1.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView1.loadUrl("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%A0%95%EC%99%95%EC%97%AD+4%ED%98%B8%EC%84%A0+%EC%8B%9C%EA%B0%84%ED%91%9C")
    }//4호선
    override fun onBackPressed() {
        if (webView1.canGoBack())
        {
            webView1.goBack()
        }
        else
        {
            finish()
        }
    }
}