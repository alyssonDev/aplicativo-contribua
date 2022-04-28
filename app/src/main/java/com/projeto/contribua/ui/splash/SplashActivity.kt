package com.projeto.contribua.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.projeto.contribua.R
import com.projeto.contribua.databinding.ActivitySplashBinding
import com.projeto.contribua.ui.information.InformationActivity
import com.rbddevs.splashy.Splashy

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        settingSplashyLibrary()
        val intentHome = Intent(this, InformationActivity::class.java)
        Handler().postDelayed(
            {
                startActivity(intentHome)
                finish()
            },
            3000
        )

    }

    private fun settingSplashyLibrary() {
        Splashy(this)
            .setTitle(R.string.title_for_splash)
            .setTitleColor(R.color.black)
            .setSubTitle(R.string.contribute_description_for_splash)
            .setFullScreen(true)
            .setSubTitleColor(R.color.black)
            .setSubTitleSize(16F)
            .setAnimation(Splashy.Animation.SLIDE_IN_LEFT_RIGHT, 2000)
            .setInfiniteDuration(true)
            .show()
    }
}