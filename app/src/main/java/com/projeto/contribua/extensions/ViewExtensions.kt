package com.projeto.contribua.extensions

import android.widget.EditText
import com.projeto.contribua.utils.MoneyTextWatcher
import java.util.Locale


fun EditText.maskMonetary() {
    val mLocale = Locale("pt", "BR")
    this.addTextChangedListener(MoneyTextWatcher(this, mLocale))
}


