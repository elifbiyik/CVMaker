package com.eb.cvmaker._paragraphStyle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.itextpdf.io.image.ImageData
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.io.source.ByteArrayOutputStream
import com.itextpdf.layout.element.Image

class Images {

    fun image(context: Context, image: Int): Image {
        val drawable = ContextCompat.getDrawable(context, image)
        val imageData: ImageData = ImageDataFactory.create(drawable?.toByteArray())
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
}