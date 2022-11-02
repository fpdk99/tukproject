package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_efood.*

class efood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_efood)
        webView3.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView3.loadUrl("https://ibook.tukorea.ac.kr/Viewer/menu01")
    }//수인분당선
    override fun onBackPressed() {
        if (webView3.canGoBack())
        {
            webView3.goBack()
        }
        else
        {
            finish()
        }
    }
}