import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File

class PdfGenerator(private val context: Context) {

    suspend fun addImagesToPdf(document: Document, imagePaths: List<String>, pageSize: PageSize) {
        withContext(Dispatchers.IO) {
            imagePaths.forEach { imagePath ->
                val imageFile = File(imagePath)
                if (imageFile.exists()) {
                    val bitmap = BitmapFactory.decodeFile(imagePath)
                    if (bitmap != null) {
                        val imageData = ImageDataFactory.create(bitmapToByteArray(bitmap))
                        val image = Image(imageData)
                        image.setFixedPosition(0f, pageSize.height - image.imageHeight)
                        document.add(image)
                    }
                }
            }
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}
