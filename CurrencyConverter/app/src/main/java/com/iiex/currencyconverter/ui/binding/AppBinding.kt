package com.iiex.currencyconverter.ui.binding
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.iiex.currencyconverter.utils.Result
import com.iiex.currencyconverter.data.model.CurrencyResponse

@BindingAdapter("resultText")
fun bindResultText(textView: TextView, result: Result<CurrencyResponse>?) {
    when (result) {
        is Result.Loading -> {
            textView.text = "Loading..."
        }
        is Result.Success -> {
            // Display the data from `CurrencyResponse`
            textView.text =
                result.data.rates["EUR"].toString()
        }
        is Result.Error -> {
            textView.text = "Error: ${result.message}"
        }
        else -> {
            textView.text = ""
        }
    }
}
