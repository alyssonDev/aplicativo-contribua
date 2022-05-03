package com.projeto.contribua.ui.donate.signature

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.projeto.contribua.databinding.ActivitySignatureBinding
import java.io.ByteArrayOutputStream

class SignatureActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignatureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setupViews()
    }

    private fun setupViews() {
        binding = ActivitySignatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonClearSignature.setOnClickListener(this)
        binding.buttonSaveSignature.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonClearSignature -> binding.signaturePad.clear()
            binding.buttonSaveSignature -> saveSignature()
        }
    }

    private fun saveSignature() {
        binding.progress.visibility = View.VISIBLE
        binding.signaturePad.isEnabled = false
        setupExtra()
        Handler().postDelayed(
            {
                binding.progress.visibility = View.GONE
                finish()
            },
            3200
        )
    }

    private fun setupExtra() {
        val outStream = ByteArrayOutputStream()
        binding.signaturePad.getTransparentSignatureBitmap(true)
            .compress(Bitmap.CompressFormat.PNG, 50, outStream)
        intent.putExtra(SIGNATURE_EXTRA, outStream.toByteArray())
        setResult(-1, intent)
    }

    companion object {
        const val SIGNATURE_EXTRA = "SIGNATURE_EXTRA"
    }
}