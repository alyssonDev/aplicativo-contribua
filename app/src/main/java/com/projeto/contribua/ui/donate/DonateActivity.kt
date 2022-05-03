package com.projeto.contribua.ui.donate

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.projeto.contribua.R
import com.projeto.contribua.databinding.ActivityDonateBinding
import com.projeto.contribua.extensions.maskMonetary
import com.projeto.contribua.ui.donate.signature.SignatureActivity
import com.projeto.contribua.utils.formatdateInString
import java.util.Calendar

class DonateActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDonateBinding
    private lateinit var datePickerDialog: DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViews()
        setupDatePickerDialog()
    }

    private fun setupDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        datePickerDialog = DatePickerDialog(
            this,
            { _, mYear, mMonth, mDay ->
                binding.buttonContribuationDate.text = formatdateInString(mDay, mMonth, mYear)
            },
            year,
            month,
            day
        )

    }

    override fun onBackPressed() {
        Toast.makeText(this, R.string.message_does_not_return, Toast.LENGTH_LONG).show()
    }

    private fun setupViews() {
        binding.edtValue.maskMonetary()
        binding.buttonContribuationDate.setOnClickListener(this)
        binding.buttonSignapure.setOnClickListener(this)
        binding.buttonFinishContribuation.setOnClickListener(this)
    }

    private fun setupBinding() {
        binding = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonContribuationDate -> datePickerDialog.show()
            binding.buttonSignapure -> startSignatureActivity()
            binding.buttonFinishContribuation -> validateFields()
        }

    }

    private fun startSignatureActivity() {
        val intent = Intent(this, SignatureActivity::class.java)
        startActivity(intent)
    }

    private fun validateFields() {
        val nameContributor = binding.edtNameContributor.text.toString()
        val valueContribuation = binding.edtValue.text.toString()
        val dateContribuation = binding.buttonContribuationDate.text.toString()

        if (nameContributor.isEmpty() || valueContribuation.isEmpty() || dateContribuation.isEmpty()) {
            Toast.makeText(
                this,
                R.string.input_data_in_all_fields,
                Toast.LENGTH_LONG
            ).show()
        } else {
            finish()
        }
    }
}