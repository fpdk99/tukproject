package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_telephone.*

class telephone : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telephone)
        webView5.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView5.loadUrl("https://www.tukorea.ac.kr/tukorea/2531/subview.do")
        }
    override fun onBackPressed() {
        if (webView5.canGoBack())
        {
            webView5.goBack()
        }
        else
        {
            finish()
        }
}
}