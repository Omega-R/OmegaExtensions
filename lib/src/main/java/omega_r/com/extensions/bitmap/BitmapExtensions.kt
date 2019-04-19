package omega_r.com.extensions.bitmap

import android.graphics.Bitmap
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

fun Bitmap.toOutputStream(
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 100
): OutputStream {
    val stream = ByteArrayOutputStream()
    compress(compressFormat, quality, stream)
    return stream
}

fun Bitmap.toInputStream(
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 100
): InputStream {
    val stream = ByteArrayOutputStream()
    compress(compressFormat, quality, stream)
    val byteArray = stream.toByteArray()
    return ByteArrayInputStream(byteArray)
}