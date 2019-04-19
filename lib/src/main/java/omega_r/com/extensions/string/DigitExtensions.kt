package omega_r.com.extensions.string

private val NON_DIGIT_REGEX = Regex("[^A-Za-z0-9]")
private val DIGIT_REGEX = Regex("[^0-9]")

fun String?.replaceNonDigit() = this?.replace(NON_DIGIT_REGEX, "")

fun String?.replaceDigit() = this?.replace(DIGIT_REGEX, "")