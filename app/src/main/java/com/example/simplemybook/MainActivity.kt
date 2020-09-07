package com.example.simplemybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.IntegerRes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        start(a = "")
        //stop(b = "")
    }

    fun start (a:String)
    {
     startButton.setOnClickListener { startButton.setOnClickListener {  textView.text="Hi"} }
    }

    fun stop (b: String) {
        startButton.setOnClickListener { }
    }


}