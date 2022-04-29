package com.projeto.contribua.ui.donate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.projeto.contribua.R
import com.projeto.contribua.databinding.ActivityDonateBinding
import com.projeto.contribua.extensions.maskMonetary

class DonateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViews()
    }

    override fun onBackPressed() {
        Toast.makeText(this, R.string.message_does_not_return, Toast.LENGTH_LONG).show()
    }

    private fun setupViews() {
        binding.edtValue.maskMonetary()
    }

    private fun setupBinding() {
        binding = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}