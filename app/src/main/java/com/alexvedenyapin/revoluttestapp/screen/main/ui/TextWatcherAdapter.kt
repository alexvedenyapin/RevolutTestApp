package com.alexvedenyapin.revoluttestapp.screen.main.ui

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by Alex Vedenyapin on 14.10.2019
 */
class TextWatcherAdapter(private val onTextChanged: (String) -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        s?.let { onTextChanged(it.toString()) }
    }
}