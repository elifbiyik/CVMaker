package com.eb.cvmaker._paragraphStyle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import com.itextpdf.io.image.ImageData
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.io.source.ByteArrayOutputStream
import com.itextpdf.layout.element.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.w3c.dom.Document
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL

class Images {

    fun image(context: Context, image: Int): Image {
        val drawable = ContextCompat.getDrawable(context, image)
        val imageData: ImageData = ImageDataFactory.create(drawable?.toByteArray())

        return Image(imageData)
    }

    fun imageString(image: String): Image {
        val imageData: ImageData = ImageDataFactory.create(image?.toByteArrayFromImage())
        return Image(imageData)
    }

    private fun Drawable?.toByteArray(): ByteArray? {
        if (this == null) return null

        val stream = ByteArrayOutputStream()
        val bitmap = if (this is BitmapDrawable) {
            this.bitmap
        } else {
            Bitmap.createBitmap(
                intrinsicWidth,
                intrinsicHeight,
                Bitmap.Config.ARGB_8888
            ).apply {
                val canvas = Canvas(this)
                setBounds(0, 0, canvas.width, canvas.height)
                draw(canvas)
            }
        }

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }


    private fun String.toByteArrayFromImage(): ByteArray? {
        val inputStream: InputStream? = try {
            // Check if it's a local path or a URL
            if (startsWith("http")) {
                URL(this).openStream()
            } else {
                FileInputStream(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return inputStream?.use { stream ->
            val buffer = ByteArray(1024)
            val byteArrayOutputStream = ByteArrayOutputStream()
            var read: Int
            while (stream.read(buffer).also { read = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, read)
            }
            byteArrayOutputStream.toByteArray()
        }
    }


}