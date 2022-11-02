package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_insta_domi.*

class insta_domi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_domi)
        webView_domi.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView_domi.loadUrl("https://instagram.com/tuk_our_17?igshid=YmMyMTA2M2Y=")
    }
    override fun onBackPressed() {
        if (webView_domi.canGoBack())
        {
            webView_domi.goBack()
        }
        else
        {
            finish()
        }
    }
}