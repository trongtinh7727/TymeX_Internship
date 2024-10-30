package com.iiex.currencyconverter.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiex.currencyconverter.data.model.LatestResponse
import com.iiex.currencyconverter.data.repository.CurrencyRepository
import com.iiex.currencyconverter.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _rate = MutableLiveData<Result<LatestResponse>>()
    val rate: LiveData<Result<LatestResponse>> get() = _rate
    private var _rateValue = 1.0

    val fromAmount = MutableLiveData<String>()
    val toAmount = MutableLiveData<String>()

    init {
        fromAmount.observeForever { calculateToAmount() }
    }

    private fun calculateToAmount() {
        val amount = fromAmount.value?.toDoubleOrNull() ?: 0.0
        toAmount.value = (amount * _rateValue).toString()
    }


    fun getRates(base: String, symbols: String) {
        _rate.value = Result.loading()
        viewModelScope.launch {
            try {
                val response = repository.getRates(base,symbols)
                response.fold(
                    onSuccess = { data -> _rate.value = Result.success(data)
                                            _rateValue = data.data.values.firstOrNull() ?: 1.0
                                            calculateToAmount()
                                },
                    onFailure = { exception -> _rate.value = Result.error(exception) }
                )
            } catch (e: Exception) {
                _rate.value = Result.error(e)
            }
        }
    }

    private val _currencies = MutableLiveData<List<String>>()
    val currencies: LiveData<List<String>> get() = _currencies

    fun loadCurrencies() {
        viewModelScope.launch {
            val response = repository.getCurrencies()
            if (response.isSuccess) {
                response.fold(
                    onSuccess = {data -> _currencies.value = data.data.keys.toList()},
                    onFailure = {}
                )
            }
        }
    }
}

