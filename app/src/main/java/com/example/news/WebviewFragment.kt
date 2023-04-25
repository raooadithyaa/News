package com.example.news

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException


class WebviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        val mwebview = view.findViewById<WebView>(R.id.wv)
        val webSettings: WebSettings = mwebview.settings
        webSettings.javaScriptEnabled = true
        mwebview.webViewClient = WebViewClient()

        mwebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        val btn = view.findViewById<Button>(R.id.button)
        val value = arguments?.getString("url")
        println(value)
        if(value!=null) {
            mwebview.loadUrl(value.toString())
        }
        btn.setOnClickListener {
            btn.setVisibility(View.GONE)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, HomeFragment())?.commit()
        }
        return view
    }

}