package com.projeto.contribua.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale


class MoneyTextWatcher(
    editText: EditText?,
    private val locale: Locale?
) : TextWatcher {

    private var editTextWeakReference: WeakReference<EditText> =  WeakReference<EditText>(editText)

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val editText: EditText = editTextWeakReference.get() ?: return
        editText.removeTextChangedListener(this)
        val parsed: BigDecimal = parseToBigDecimal(editable.toString(), locale)
        val formatted: String = parsed.toString()
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }

    private fun parseToBigDecimal(value: String, locale: Locale?): BigDecimal {
        val replaceable = java.lang.String.format(
            "[%s,.\\s]",
            locale?.let { NumberFormat.getCurrencyInstance(it).currency?.symbol }
        )
        val cleanString = value.replace(replaceable.toRegex(), "")
        return BigDecimal(cleanString).setScale(
            2, BigDecimal.ROUND_FLOOR
        ).divide(
            BigDecimal(100), BigDecimal.ROUND_FLOOR
        )
    }
}