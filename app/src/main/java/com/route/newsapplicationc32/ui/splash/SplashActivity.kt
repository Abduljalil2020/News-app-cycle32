package com.route.newsapplicationc32.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.route.newsapplicationc32.R
import com.route.newsapplicationc32.ui.homePage.HomePageActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onStart() {
        super.onStart()
        Handler()
            .postDelayed({
                startActivity(Intent(this@SplashActivity, HomePageActivity::class.java))
                finish()
            }, 2000)

    }
}