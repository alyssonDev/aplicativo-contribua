package com.projeto.contribua.ui.information

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.projeto.contribua.R
import com.projeto.contribua.data.model.InformationDistrict
import com.projeto.contribua.databinding.ActivityInformationBinding
import com.projeto.contribua.extensions.getCongregationByArea
import com.projeto.contribua.extensions.getDistrictAttorneyAtCongregationName
import com.projeto.contribua.ui.donate.DonateActivity

class InformationActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityInformationBinding
    private var informationDistrict: InformationDistrict? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
        setupBinding()
        initialize()
        setupOnclickButtonNext()
    }

    private fun setupBinding() {
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupActionBar() {
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
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
            binding.autocompleteSelectArea.adapter -> setupClickSelectArea(
                p0?.getItemAtPosition(p2).toString()
            )
            binding.autocompleteSelectCongregation.adapter -> setupClickSelectCongregation(
                p0?.getItemAtPosition(
                    p2
                ).toString()
            )
            binding.autocompleteSelectDistrict.adapter -> setuClickSelectDistrict(
                p0?.getItemAtPosition(
                    p2
                ).toString()
            )
        }

    }

    private fun setuClickSelectDistrict(districName: String) {
        informationDistrict?.districtName = districName
    }

    private fun setupClickSelectCongregation(nameCongregation: String) {
        informationDistrict?.congregationName = nameCongregation
        binding.autocompleteSelectDistrict.setText(R.string.txv_select_distinc_text)
        initializeSppiner(
            binding.autocompleteSelectDistrict,
            nameCongregation.getDistrictAttorneyAtCongregationName()
        )
    }

    private fun setupClickSelectArea(congregationArea: String) {
        informationDistrict = InformationDistrict()
        informationDistrict?.congregationArea = congregationArea
        binding.autocompleteSelectCongregation.setText(R.string.txv_hint_congregation)
        binding.autocompleteSelectDistrict.setText(R.string.txv_select_distinc_text)
        populateSpinnerCongregation(congregationArea)
    }

    private fun setupOnclickButtonNext() {
        binding.btnNext.setOnClickListener {
            validateInformationObject()
        }
    }

    private fun validateInformationObject() {
        informationDistrict?.let {
            if (it.districtName.isEmpty()) {
                Toast.makeText(this, R.string.toast_message_information_empty, Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, DonateActivity::class.java)
                intent.putExtra(OBJECT_INFORMATIONS, informationDistrict)
                startActivity(intent)
                finish()
            }
        }

    }

    companion object {
        const val OBJECT_INFORMATIONS = "INFORMATION_DISTRICT"
    }
}