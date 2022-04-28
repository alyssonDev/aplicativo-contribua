package com.projeto.contribua.ui.information

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import com.projeto.contribua.R
import com.projeto.contribua.databinding.ActivityInformationBinding
import com.projeto.contribua.extensions.getCongregationByArea
import com.projeto.contribua.extensions.getDistrictAttorneyAtCongregationName

class InformationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        insertValuesInSpinnerArea()
    }

    private fun insertValuesInSpinnerArea() {
        initializeSppiner(binding.sppinnerSelectArea, R.array.list_area)
    }


    private fun populateSpinnerCongregation(areaName: String) {
        initializeSppiner(binding.sppinnerSelectCongregation, areaName.getCongregationByArea())
    }

    private fun initializeSppiner(spinnerView: AppCompatSpinner, referenceList: Int) {
        ArrayAdapter.createFromResource(
            this,
            referenceList,
            R.layout.support_simple_spinner_dropdown_item
        ).also {
            it.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinnerView.adapter = it
            spinnerView.onItemSelectedListener = this
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        p0?.visibility = View.GONE
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0?.id) {
            binding.sppinnerSelectArea.id -> {
                setListViewVisible(false)
                if (p0.getItemAtPosition(p2).toString() != resources.getString(R.string.select)) {
                    populateSpinnerCongregation(
                        p0.getItemAtPosition(p2).toString()
                    )
                }
            }
            binding.sppinnerSelectCongregation.id -> {
                if (p0.getItemAtPosition(p2)
                        .toString() != resources.getString(R.string.txv_select_congregation)
                ) {
                    populateRecyclerAtCongregation(p0.getItemAtPosition(p2).toString())
                }
            }
        }
    }

    private fun setListViewVisible(isVisible: Boolean) {
        if (isVisible) {
            binding.recyclerDistrictAttorney.visibility = View.VISIBLE

        } else {
            binding.recyclerDistrictAttorney.visibility = View.GONE
        }

    }

    private fun populateRecyclerAtCongregation(congregationName: String) {
        setListViewVisible(true)
        binding.recyclerDistrictAttorney.adapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            congregationName.getDistrictAttorneyAtCongregationName()
        )
    }

}