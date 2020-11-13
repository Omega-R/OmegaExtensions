package omega_r.com.extensions.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.omega_r.libs.extensions.coroutines.launchDelayed
import com.omega_r.libs.extensions.coroutines.launchWithTicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.time.DurationUnit
import kotlin.time.seconds

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).apply {
            launchDelayed(4900) {
                Log.d("MainActivity", "launchDelayed(4900)")
            }
            launchDelayed(5.seconds) {
                Log.d("MainActivity", "launchDelayed(5.seconds)")
            }
            launchWithTicker(
                ticksCount = 10,
                tickDurationMillis = 1000,
                initialDelayMillis = 5000,
                onTick = {
                    Log.d("MainActivity", "launchWithTicker($it)")
                },
                onEnd = {
                    Log.d("MainActivity", "launchWithTicker(end)")
                }
            )
            launchWithTicker(
                ticksCount = 10,
                tickDuration = 1.seconds,
                initialDelay = 15.seconds,
                onTick = {
                    Log.d("MainActivity", "launchWithTicker2($it)")
                },
                onEnd = {
                    Log.d("MainActivity", "launchWithTicker2(end)")
                }
            )
        }
    }

}