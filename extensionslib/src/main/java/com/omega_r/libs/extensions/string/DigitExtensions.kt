package com.omega_r.libs.extensions.string

private val NON_DIGIT_REGEX = Regex("[^A-Za-z0-9]")
private val DIGIT_REGEX = Regex("[^0-9]")

fun String.removeAllWithoutDigitAndLetters() = replace(NON_DIGIT_REGEX, "")

fun String.removeNonDigit() = replace(DIGIT_REGEX, "")

@Deprecated(message = "Use removeAllWithoutDigitAndLetters instead.", replaceWith = ReplaceWith("this.removeAllWithoutDigitAndLetters()", "com.omega_r.libs.extensions.string.removeAllWithoutDigitAndLetters"))
fun String?.replaceNonDigit() = this?.removeAllWithoutDigitAndLetters()

@Deprecated(message = "", replaceWith = ReplaceWith("this.removeNonDigit()"))
fun String?.replaceDigit() = this?.removeNonDigit()