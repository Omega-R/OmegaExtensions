package com.omega_r.libs.extensions.string

private val NON_DIGIT_REGEX = Regex("[^A-Za-z0-9]")
private val DIGIT_REGEX = Regex("[^0-9]")

fun String.removeAllWithoutDigitAndLetters() = replace(NON_DIGIT_REGEX, "")

fun String.removeNonDigit() = replace(DIGIT_REGEX, "")