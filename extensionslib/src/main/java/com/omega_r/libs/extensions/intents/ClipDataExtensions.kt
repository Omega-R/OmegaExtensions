package com.omega_r.libs.extensions.intents

import android.content.ClipData

/**
 * Created by Anton Knyazev on 16.11.2020.
 */
operator fun ClipData.iterator(): Iterator<ClipData.Item> {
  return (0 until itemCount).asSequence().map(::getItemAt).iterator()
}

val ClipData.items: List<ClipData.Item>
    get() = (0 until itemCount).map(::getItemAt)