package com.omega_r.libs.extensions.string

import java.util.regex.Pattern

private val PATTERN_ABSOLUTE_URL = Pattern.compile("\\A[a-z0-9.+-]+://.*", Pattern.CASE_INSENSITIVE)

fun String.isAbsoluteUrl(): Boolean = PATTERN_ABSOLUTE_URL.matcher(this).matches()