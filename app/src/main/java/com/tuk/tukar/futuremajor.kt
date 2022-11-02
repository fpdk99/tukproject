package com.tuk.tukar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_futuremajor.*

class futuremajor : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_futuremajor, container, false)
}}