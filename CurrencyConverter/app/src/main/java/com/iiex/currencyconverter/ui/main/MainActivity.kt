package com.iiex.currencyconverter.ui.main

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.iiex.currencyconverter.R
import com.iiex.currencyconverter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CurrencyViewModel by viewModels()

    private var selectedCurrencyFrom: String? = null
    private var selectedCurrencyTo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.loadCurrencies()

        viewModel.currencies.observe(this){
            currencyList ->
            val adapter = ArrayAdapter(this, R.layout.spinner_item, currencyList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.currencySpinnerFrom.adapter = adapter
            binding.currencySpinnerTo.adapter = adapter
        }

        binding.currencySpinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCurrencyFrom = parent.getItemAtPosition(position).toString()
                fetchRatesIfReady()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }
        }

        binding.currencySpinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCurrencyTo = parent.getItemAtPosition(position).toString()
                fetchRatesIfReady()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }
        }
    }

    private fun fetchRatesIfReady() {
        if (selectedCurrencyFrom != null && selectedCurrencyTo != null) {
            viewModel.getRates(selectedCurrencyFrom!!, selectedCurrencyTo!!)
        }
    }
}
