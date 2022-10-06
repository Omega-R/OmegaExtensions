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


    }

}