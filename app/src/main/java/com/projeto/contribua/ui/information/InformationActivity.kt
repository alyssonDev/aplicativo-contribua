package com.projeto.contribua.ui.information

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.projeto.contribua.R
import com.projeto.contribua.databinding.ActivityInformationBinding
import com.projeto.contribua.extensions.getCongregationByArea
import com.projeto.contribua.extensions.getDistrictAttorneyAtCongregationName

class InformationActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()

    }

    private fun initialize() {
        insertValuesInSpinnerArea()
    }

    private fun insertValuesInSpinnerArea() {
        initializeSppiner(binding.autocompleteSelectArea, R.array.list_area)
    }


    private fun populateSpinnerCongregation(areaName: String) {
        initializeSppiner(binding.autocompleteSelectCongregation, areaName.getCongregationByArea())
    }

    private fun initializeSppiner(autocompleteTextView: AutoCompleteTextView, referenceList: Int) {
        ArrayAdapter.createFromResource(
            this,
            referenceList,
            R.layout.support_simple_spinner_dropdown_item
        ).also {
            autocompleteTextView.setAdapter(it)
            autocompleteTextView.onItemClickListener = this
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0?.adapter) {
            binding.autocompleteSelectArea.adapter -> {
                binding.autocompleteSelectCongregation.setText(R.string.txv_hint_congregation)
                binding.autocompleteSelectDistrict.setText(R.string.txv_select_distinc_text)
                populateSpinnerCongregation(p0?.getItemAtPosition(p2).toString())
            }
            binding.autocompleteSelectCongregation.adapter -> {
                binding.autocompleteSelectDistrict.setText(R.string.txv_select_distinc_text)
                initializeSppiner(
                    binding.autocompleteSelectDistrict,
                    p0?.getItemAtPosition(p2).toString().getDistrictAttorneyAtCongregationName()
                )
            }
        }

    }

}