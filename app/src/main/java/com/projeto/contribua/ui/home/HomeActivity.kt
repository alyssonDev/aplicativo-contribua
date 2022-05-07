package com.projeto.contribua.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.models.SlideModel
import com.projeto.contribua.databinding.ActivityHomeBinding
import com.projeto.contribua.ui.information.InformationActivity
import com.projeto.contribua.utils.populateList

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupViews()
    }

    private fun setupViews() {
        setupSlider()
        setupClick()
    }

    private fun setupClick() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupSlider() {
        val imageList: MutableList<SlideModel> = mutableListOf()
        imageList.populateList()
        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
    }
}