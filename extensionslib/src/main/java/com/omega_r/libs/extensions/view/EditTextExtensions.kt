package com.omega_r.libs.extensions.view

import android.text.InputFilter
import android.widget.EditText

/**
 * Created by Anton Knyazev on 16.11.2020.
 */
var EditText.maxLength: Int
    get() = filters.filterIsInstance(InputFilter.LengthFilter::class.java).firstOrNull()?.max ?: 0
    set(value) {
        val nonLengthFilters = filters.filter { it !is InputFilter.LengthFilter }
        filters = (nonLengthFilters + InputFilter.LengthFilter(value)).toTypedArray()
    }