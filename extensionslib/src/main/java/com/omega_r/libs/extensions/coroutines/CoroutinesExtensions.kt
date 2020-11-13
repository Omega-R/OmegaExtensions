package com.omega_r.libs.extensions.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun CoroutineScope.launchWithTicker(
    ticksCount: Int,
    durationUnit: DurationUnit,
    onTick: suspend (Int) -> Unit,
    onEnd: (suspend () -> Unit)? = null,
    initialDelayMillis: Long = 0,
): ReceiveChannel<Unit> {
    val tickerChannel = ticker(
        ticksCount.toDuration(durationUnit).toLongMilliseconds(),
        initialDelayMillis
    )
    launch {
        var currentTick = 0
        for (event in tickerChannel) {
            if (currentTick >= ticksCount) {
                onEnd?.invoke()
                tickerChannel.cancel()
                break
            }
            onTick(currentTick)
            currentTick++
        }
    }
    return tickerChannel
}

fun CoroutineScope.launchDelayed(delayMillis: Long, action: suspend () -> Unit) {
    launch {
        delay(delayMillis)
        action()
    }
}

fun CoroutineScope.launchDelayed(delayDuration: Duration, action: suspend () -> Unit) {
    launch {
        delay(delayDuration)
        action()
    }
}