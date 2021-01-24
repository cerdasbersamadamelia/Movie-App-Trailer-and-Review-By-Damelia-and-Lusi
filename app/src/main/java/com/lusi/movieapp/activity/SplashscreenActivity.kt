package com.lusi.movieapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.lusi.movieapp.R
import com.lusi.movieapp.fragment.HomeActivity

class SplashscreenActivity : AppCompatActivity() {

    val SPLASH_SCREEN = 5000

    private  lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    private lateinit var bgsplashscreen: ImageView
    private lateinit var text_title: TextView
    private lateinit var text_desc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar!!.hide()

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        bgsplashscreen = findViewById(R.id.bgsplashscreen)
        text_title = findViewById(R.id.text_title)
        text_desc = findViewById(R.id.text_desc)

        bgsplashscreen.animation = topAnimation
        text_title.animation = bottomAnimation
        text_desc.animation = bottomAnimation

        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())

    }
}