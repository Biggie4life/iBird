package com.example.ibird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class AfricanGreyParrot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_african_grey_parrot)

        val webView = findViewById<WebView>(R.id.webView)

        // Enable JavaScript (if needed)
        webView.settings.javaScriptEnabled = true

        // Load the Wiki page
        webView.loadUrl("https://en.wikipedia.org/wiki/Grey_parrot")

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