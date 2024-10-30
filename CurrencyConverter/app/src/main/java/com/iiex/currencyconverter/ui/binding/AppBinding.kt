package com.iiex.currencyconverter.ui.binding
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.iiex.currencyconverter.utils.Result
import com.iiex.currencyconverter.data.model.LatestResponse

@BindingAdapter("rateText")
fun bindRate(textView: TextView, result: Result<LatestResponse>?) {
    when (result) {
        is Result.Loading -> {
            textView.text = "Convert rate: Loading..."
        }
        is Result.Success -> {
            val rate = result.data.data.values.firstOrNull()
            textView.text = if (rate != null) {
                "Convert rate: $rate"
            } else {
                "Convert rate: No data available"
            }
        }
        is Result.Error -> {
            textView.text = "Error: ${result.message}"
        }
        else -> {
            textView.text = "Convert rate: 0.000"
        }
    }
}
