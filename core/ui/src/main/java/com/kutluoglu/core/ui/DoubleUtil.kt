package com.kutluoglu.core.ui

import java.text.NumberFormat
import java.util.Locale

// Extension function for clean, reusable currency formatting
fun Double.toCurrencyString(): String {
    return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
}