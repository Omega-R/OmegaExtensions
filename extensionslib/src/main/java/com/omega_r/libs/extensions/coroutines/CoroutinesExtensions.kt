package com.omega_r.libs.extensions.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

fun CoroutineScope.launchWithTicker(
    ticksCount: Int,
    tickDurationMillis: Long,
    onTick: suspend (Int) -> Unit,
    onEnd: (suspend () -> Unit)? = null,
    initialDelayMillis: Long = 0,
): ReceiveChannel<Unit> {
    val tickerChannel = ticker(
        tickDurationMillis,
        initialDelayMillis
    )
    launch {
        var currentTick = 0
        for (event in tickerChannel) {
            onTick(currentTick)
            if (currentTick == ticksCount) {
                onEnd?.invoke()
                tickerChannel.cancel()
                break
            }
            currentTick++
        }
    }
    return tickerChannel
}

@ExperimentalTime
fun CoroutineScope.launchWithTicker(
    ticksCount: Int,
    tickDuration: Duration,
    onTick: suspend (Int) -> Unit,
    onEnd: (suspend () -> Unit)? = null,
    initialDelay: Duration = Duration.ZERO,
): ReceiveChannel<Unit> {
    return launchWithTicker(
        ticksCount = ticksCount,
        tickDurationMillis = tickDuration.toLongMilliseconds(),
        onTick = onTick,
        onEnd = onEnd,
        initialDelayMillis = initialDelay.toLongMilliseconds()
    )
}

fun CoroutineScope.launchDelayed(delayMillis: Long, action: suspend () -> Unit) {
    launch {
        delay(delayMillis)
        action()
    }
}

@ExperimentalTime
fun CoroutineScope.launchDelayed(delayDuration: Duration, action: suspend () -> Unit) {
    launch {
        delay(delayDuration)
        action()
    }
}