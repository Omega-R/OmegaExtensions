package omega_r.com.extensions.context

import android.content.Context
import android.net.Uri
import java.io.InputStream

fun Context.createInputStreamFromUri(uri: Uri): InputStream? {
    return contentResolver.openInputStream(uri)
}