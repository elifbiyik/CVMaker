package com.eb.cvmaker._paragraphStyle

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.io.source.ByteArrayOutputStream
import com.itextpdf.layout.element.Image

class Images {

    fun image(context : Context, image: Int): Image {

        val drawable = ContextCompat.getDrawable(context, image)
        val bitmap: Bitmap? = drawable?.toBitmap()

        // bitmap byte dizisine dönüştürülüyor. Çünkü create metodu byte, url veya string istiyor.
        var stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        var byte = stream.toByteArray()

        var imageData = ImageDataFactory.create(byte)
        var image = Image(imageData)


        return image
    }
}