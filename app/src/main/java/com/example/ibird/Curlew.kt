package com.example.ibird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class Curlew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curlew)

        val webView = findViewById<WebView>(R.id.webView)

        // Enable JavaScript (if needed)
        webView.settings.javaScriptEnabled = true

        // Load the Wiki page
        webView.loadUrl("https://en.wikipedia.org/wiki/Curlew")

        // Set a WebViewClient to handle page navigation within the WebView
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    view?.loadUrl(it)
                }
                return true
            }
        }
    }
}