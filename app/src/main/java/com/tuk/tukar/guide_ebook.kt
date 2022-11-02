package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_guide_ebook.*

class guide_ebook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_ebook)
        webebook.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webebook.loadUrl("https://ebook.tukorea.ac.kr/FxLibrary/")
    }//ebook
    override fun onBackPressed() {
        if (webebook.canGoBack())
        {
            webebook.goBack()
        }
        else
        {
            finish()
        }
    }
}
