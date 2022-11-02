package com.tuk.tukar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_subway_suin.*

class subway_suin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subway_suin)
        webView2.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView2.loadUrl("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%88%98%EC%9D%B8%EB%B6%84%EB%8B%B9%EC%84%A0+%EC%A0%95%EC%99%95%EC%97%AD+%EC%8B%9C%EA%B0%84%ED%91%9C&oquery=%EC%88%98%EC%9D%B8%EB%B6%84%EB%8B%B9%EC%84%A0+%EC%A0%95%EC%99%95%EC%97%AD")
    }//수인분당선
    override fun onBackPressed() {
        if (webView2.canGoBack())
        {
            webView2.goBack()
        }
        else
        {
            finish()
        }
    }
}