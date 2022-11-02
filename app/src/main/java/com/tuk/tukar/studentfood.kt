package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_studentfood.*

class studentfood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentfood)
        webView4.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView4.loadUrl("https://ibook.tukorea.ac.kr/Viewer/menu02")
    }//수인분당선
    override fun onBackPressed() {
        if (webView4.canGoBack())
        {
            webView4.goBack()
        }
        else
        {
            finish()
        }
    }
}