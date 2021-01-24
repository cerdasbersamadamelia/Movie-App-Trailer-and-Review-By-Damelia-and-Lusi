package com.lusi.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.lusi.movieapp.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "About Us"
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

    }
}