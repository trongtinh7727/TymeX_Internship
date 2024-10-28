package com.iiex.currencyconverter.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiex.currencyconverter.data.model.CurrencyResponse
import com.iiex.currencyconverter.data.repository.CurrencyRepository
import com.iiex.currencyconverter.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _result = MutableLiveData<Result<CurrencyResponse>>()
    val result: LiveData<Result<CurrencyResponse>> get() = _result

    fun getRates( symbols: String) {
        _result.value = Result.loading()

        viewModelScope.launch {
            try {
                val response = repository.getRates(symbols)
                response.fold(
                    onSuccess = { data -> _result.value = Result.success(data) },
                    onFailure = { exception -> _result.value = Result.error(exception) }
                )

            } catch (e: Exception) {
                _result.value = Result.error(e)
            }
        }
    }
}

