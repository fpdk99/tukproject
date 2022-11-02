package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_insta_official.*


class insta_official : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_official)
        webView_offi.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView_offi.loadUrl("https://instagram.com/tukorea_official?igshid=YmMyMTA2M2Y=")
    }
    override fun onBackPressed() {
        if (webView_offi.canGoBack())
        {
            webView_offi.goBack()
        }
        else
        {
            finish()
        }
    }
}