package com.eb.cvmaker.ui._Create

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eb.cvmaker.R
import com.eb.cvmaker._paragraphStyle.Images
import com.eb.cvmaker._paragraphStyle.Styles
import com.eb.cvmaker.addValueCell
import com.eb.cvmaker.addValueSameCell
import com.eb.cvmaker.cellStyleSameCell
import com.eb.cvmaker.createLine
import com.eb.cvmaker.emptyCell
import com.eb.cvmaker.observe
import com.eb.cvmaker.ui.Communication.CommunicationFragment
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject


class Template_2 @Inject constructor(
    private val viewModel: ChooseTemplateVM,
    var requireActivity: FragmentActivity,
    var context: Context,
) {

    // val widthLeft = PageSize.A4.width * 0.32f
    val widthLeft = PageSize.A4.width * 0.32f
    val widthSpace = PageSize.A4.width * 0.03f // 2 tablo arası mesafe
    val widthRight = PageSize.A4.width * 0.52f

    var style = Styles()
    var image = Images()

    // lifecycleOwner fragment'ın yaşam döngüsü olaylarını gözlemlemek için kullanılır.
    val lifecycleOwner = requireActivity as LifecycleOwner // Assuming you're in a fragment

    fun generateCV(document: Document) {

        background(document)
        personalInformation(document)
        profile(document)

        val tableLeft = Table(1).setWidth(widthLeft).setTextAlignment(TextAlignment.LEFT)
            .setHeight(UnitValue.createPercentValue(45f))

        val tableRight = Table(1).setWidth(widthRight).setTextAlignment(TextAlignment.LEFT)
            .setHeight(UnitValue.createPercentValue(60f))
        //   .setFixedPosition(widthSpaceForRight, bottom, widthRight)

        informationCommunicationLeft(tableLeft)
        informationCommunicationRight(tableRight)

        val tableContainer = Table(2)
            .setMarginTop(60f)
            .setHeight(UnitValue.createPercentValue(100f))
            .setWidth(PageSize.A4.width)
            .setMarginLeft(-18f)

        val firstColumn = Cell().add(tableLeft).setWidth(widthLeft).setBorder(Border.NO_BORDER)
        tableContainer.addCell(firstColumn)

        val secondColumn =
            Cell().add(tableRight).setWidth(widthRight).setPaddingLeft(widthSpace)
                .setPaddingTop(10f)
                .setBorder(Border.NO_BORDER)
        tableContainer.addCell(secondColumn)

        document.add(tableContainer)
    }


    fun informationCommunicationLeft(tableLeft: Table) {
        titleContact(tableLeft)
        personalInformationContact(tableLeft)

        titleSocialMedia(tableLeft)
        informationSocialMedia(tableLeft)

        titleLanguages(tableLeft)
        informationLanguages(tableLeft)

        titleAbilities(tableLeft)
        informationAbilities(tableLeft)

        titleReferences(tableLeft)
        informationReferences(tableLeft)
    }

    fun informationCommunicationRight(tableRight: Table) {

        titleAboutMe(tableRight)
        titleEmpty(tableRight)
        informationAboutMe(tableRight)
        titleEmpty(tableRight)

        titleExperience(tableRight)
        titleEmpty(tableRight)
        informationExperience(tableRight)

        titleEducation(tableRight)
        titleEmpty(tableRight)
        informationEducation(tableRight)

    }

    fun background(document: Document) {
        /*    val paragraph = Paragraph()
            val imageData = image.image(context, R.drawable.template2_background)
            paragraph.add(imageData)
            //  document.setMargins(pageHeight, pageWidth, pageHeight, pageWidth)
            document.setMargins(0f, 0f, 0f, 0f)
            document.add(paragraph)
    */

        val A4_WIDTH_MM = 210f
        val A4_HEIGHT_MM = 297f
        val DPI = 72 // veya 96, kullanılan DPI'ye bağlı olarak değiştirin

// Drawableden resmi alıyor.
        val d1 = context.getDrawable(R.drawable.template2_background)
        val bitmapOriginal = (d1 as BitmapDrawable).bitmap

// A4 boyutuna göre resize ????
        val widthPx = (A4_WIDTH_MM / 25.4f * DPI).toInt()
        val heightPx = (A4_HEIGHT_MM / 25.4f * DPI).toInt()
        val bitmapResized = Bitmap.createScaledBitmap(bitmapOriginal, widthPx, heightPx, false)

// Resmi PDF'e ekle.
        val stream1 = ByteArrayOutputStream()
        bitmapResized.compress(Bitmap.CompressFormat.JPEG, 100, stream1)
        val byte = stream1.toByteArray()

        val imageData1 = ImageDataFactory.create(byte)
        val image1 = Image(imageData1)

        document.add(image1.setFixedPosition(0f, 0f))

    }

    fun profile(document: Document) {
        var sharedPreferences =
            context?.getSharedPreferences("userImage", AppCompatActivity.MODE_PRIVATE)
        var filePath = sharedPreferences?.getString("imagePath", "").toString()

        if (filePath != null) {
            Log.d("xxxxxEeeliff", filePath)
            try {
            val file = File(filePath)
                val inputStream = FileInputStream(file)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                val circularBitmap = cropToCircle(bitmap)

                val outputStream = ByteArrayOutputStream()
                circularBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                val imageData: ByteArray = outputStream.toByteArray()

                val image = Image(ImageDataFactory.create(imageData))

                 image.scaleToFit(150f, 150f)
                 image.setFixedPosition(40f, 700f)



                document.add(image)





            } catch (e: Exception) {
                Log.e("Template_2", "Resim yüklemede hata:", e)
            }
        } else {
            Log.e("Template_2", "Resim path'i bulunamadı")
        }
    }

    fun cropToCircle(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle(bitmap.width / 2f, bitmap.height / 2f, bitmap.width / 2f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output
    }


    fun profilee(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {

                        Log.d("xxxxxElifff imagee", it.image.toString())


                        /*
                        val xObject =
                            PdfImageXObject(ImageDataFactory.createPng(UrlUtil.toURL(it.image)))
                        val image =
                            Image(xObject, 100f).setHorizontalAlignment(HorizontalAlignment.RIGHT)
                        document.add(image)

                        val contentUri: Uri = Uri.parse(it.image)
                        val filePath = getFilePathFromContentUri(context, contentUri)
                        if (filePath != null) {
                      //      addImageToPdf(document, filePath)
                        }
*/


                        //TODO  Şu case ile çalışıyor :
                        // Communicationda delete yap.
                        // Communicationa resim, isim, soyisim ..... gir
                        // PDF oluştur
                        // Resim gözüküyor.
                        // FAKAT
                        // Uygulamadan çıkıp girdiğinde
                        // Communicationlar varken ( değişiklik yapmadan) PDF oluşturulmak istendiğinde hata veriyor.
                        // Hata :  java.lang.SecurityException: Permission Denial: opening provider com.miui.gallery.provider.GalleryOpenProvider from ProcessRecord{90f871f 23301:com.eb.cvmaker/u0a415} (pid=23301, uid=10415) that is not exported from UID 10090


                        var imageUri = Uri.parse(it.image)
                        val outputStream = ByteArrayOutputStream()

                        if (imageUri != null) {
                            Log.d("xxxxxElifff ", it.image.toString())

                            val inputStream: InputStream? =
                                context.contentResolver.openInputStream(imageUri!!)
                            if (inputStream != null) {
                                Log.d("xxxxxElifff imagee", it.image.toString())

                                // InputStream null değilse, resmi yükle ve PDF'e ekle
                                val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                                val imageData: ByteArray = outputStream.toByteArray()
                                val image = Image(ImageDataFactory.create(imageData))
                                image.scaleToFit(100f, 100f)
                                image.setFixedPosition(36f, 750f)
                                document.add(image)
                            } else {
                                Log.e("Template_2", "Input stream is null")
                            }
                        } else {
                            Log.e("Template_2", "Image URI is null")
                        }


                    }
                }
            }
        }
    }

    fun getFilePathFromContentUri(context: Context, contentUri: Uri): String? {
        var filePath: String? = null
        val projection = arrayOf(android.provider.MediaStore.Images.ImageColumns.DATA)
        val cursor = context.contentResolver.query(contentUri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex =
                    it.getColumnIndexOrThrow(android.provider.MediaStore.Images.ImageColumns.DATA)
                filePath = it.getString(columnIndex)
            }
        }
        return filePath
    }

    // Dosyayı PDF'e ekleme işlevi
    fun addImageToPdf(pdfDocument: Document, imagePath: String) {
        try {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            val image = Image(ImageDataFactory.create(imagePath))
            val A4_HEIGHT_MM = 297f
            val DPI = 72 // veya 96, kullanılan DPI'ye bağlı olarak değiştirin
            val heightPx = (A4_HEIGHT_MM / 25.4f * DPI).toInt()
            image.setFixedPosition(0f, PageSize.A4.height - heightPx)
            pdfDocument.add(image)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }


    fun personalInformation(document: Document) {

        var table = Table(1)
        table.setMarginTop(0f)

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {

                    var cellNameAndSurname = cellStyleSameCell(
                        val1 = it.name,
                        styles1 = style.template_2_Name_Surname(),
                        val2 = requireActivity.resources.getString(R.string.space),
                        styles2 = style.template_2_Name_Surname(),
                        val3 = it.surname?.toUpperCase(),
                        styles3 = style.template_2_Name_Surname(),
                        TextAlignment.CENTER
                    )

                    table.addCell(cellNameAndSurname)

                    var cellJob = cellStyleSameCell(
                        val1 = it.job?.toUpperCase(),
                        style.template_2_Job(),
                        alignment = TextAlignment.CENTER
                    )
                    table.addCell(cellJob)
                }

                table.setWidth(UnitValue.createPercentValue(100f))
                table.setTextAlignment(TextAlignment.CENTER)

                document.add(table)
            }
        }
    }

    // Communication
    fun titleContact(table: Table) {
        var title = requireActivity.resources.getString(R.string.contact)
        table.addCell(addValueCell(title, style.template_2_Heebo_Medium_16f(), bottom = -10f))
        table.addCell(createLine(widthLeft))
    }

    fun personalInformationContact(table: Table) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(table) {
                        // Phone
                        if (!it.phone.isNullOrEmpty()) {
                            var imagePhone = image.image(context, R.drawable.baseline_phone_24)
                            addCell(
                                addValueSameCell(
                                    image = imagePhone,
                                    val1 = it.phone,
                                    styles1 = style.template_2_Arial_11f(),
                                    top = 10f
                                )
                            )
                        }

                        // Mail
                        if (!it.email.isNullOrEmpty()) {
                            var imageMail = image.image(context, R.drawable.baseline_email_24)
                            addCell(
                                addValueSameCell(
                                    image = imageMail,
                                    val1 = it.email,
                                    styles1 = style.template_2_Arial_11f(),
                                )
                            )
                        }

                        // Location
                        if (!it.address.isNullOrEmpty()) {
                            var imageLocation =
                                image.image(context, R.drawable.baseline_location_on_24)
                            addCell(
                                addValueSameCell(
                                    image = imageLocation,
                                    val1 = it.address,
                                    styles1 = style.template_2_Arial_11f(),
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    // Social Media
    fun titleSocialMedia(table: Table) {
        var title = requireActivity.resources.getString(R.string.SocialMedia)
        table.addCell(addValueCell(title, style.template_2_Heebo_Medium_16f(), bottom = -10f))
        table.addCell(createLine(widthLeft)).setPaddingBottom(-15f)
    }

    fun informationSocialMedia(table: Table) {
        val imageGithub = image.image(context, R.drawable.github_)
        val imageLinkedin = image.image(context, R.drawable.linkedin_)
        val imageWebSite = image.image(context, R.drawable.website_)

        lifecycleOwner.observe(viewModel.socialMediaMLD) { socialMedia ->
            if (!socialMedia.isNullOrEmpty()) {
                socialMedia.forEach {
                    with(table) {
                        if (!it.github.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageGithub,
                                val1 = it.github,
                                styles1 = style.template_2_Arial_105f(),
                            )
                            addCell(cell)
                        }

                        if (!it.linkedIn.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageLinkedin,
                                val1 = it.linkedIn,
                                styles1 = style.template_2_Arial_105f(),
                            )
                            addCell(cell)
                        }

                        if (!it.webSite.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageWebSite,
                                val1 = it.webSite,
                                styles1 = style.template_2_Arial_105f()
                            )
                            addCell(cell)
                        }
                    }
                }
            }
        }
    }

    // Language
    fun titleLanguages(table: Table) {
        var title = requireActivity.resources.getString(R.string.Languages)
        table.addCell(addValueCell(title, style.template_2_Heebo_Medium_16f(), bottom = -10f))
        table.addCell(createLine(widthLeft))
    }

    fun informationLanguages(table: Table) {
        val width = PageSize.A4.width * 0.25f

        lifecycleOwner.observe(viewModel.languageMLD) { socialMedia ->
            if (!socialMedia.isNullOrEmpty()) {
                socialMedia.forEach {
                    with(table) {
                        if (!it.languageName.isNullOrEmpty()) {

                            var cell = addValueSameCell(
                                val1 = it.languageName!!,
                                styles1 = style.template_2_Arial_11f(),
                                val2 = requireActivity.resources.getString(R.string.dots),
                                val3 = requireActivity.resources.getString(R.string.space),
                                val4 = it.level,
                                styles4 = style.template_2_Arial_11f(),
                            )
                            addCell(cell)
                        }
                    }
                }
            }
        }
    }

    // Skills
    fun titleAbilities(table: Table) {
        var title = requireActivity.resources.getString(R.string.skills)
        table.addCell(addValueCell(title, style.template_2_Heebo_Medium_16f(), bottom = -10f))
        table.addCell(createLine(widthLeft))
    }

    fun informationAbilities(table: Table) {
        lifecycleOwner.observe(viewModel.abilitiesMLD) { skills ->
            if (!skills.isNullOrEmpty()) {
                skills.forEach {
                    with(table) {
                        if (!it.abilitiesName.isNullOrEmpty()) {

                            var point = image.image(context, R.drawable.baseline_point_24)

                            var cell =
                                addValueSameCell(
                                    point,
                                    it.abilitiesName!!,
                                    style.template_2_Arial_11f(),
                                )
                            addCell(cell)
                        }
                    }
                }
            }
        }
    }

    // References
    fun titleReferences(table: Table) {
        var title = requireActivity.resources.getString(R.string.References)
        table.addCell(addValueCell(title, style.template_2_Heebo_Medium_16f(), bottom = -10f))
        table.addCell(createLine(widthLeft))
    }

    fun informationReferences(table: Table) {
        lifecycleOwner.observe(viewModel.referencesMLD) { skills ->
            if (!skills.isNullOrEmpty()) {
                skills.forEach {
                    with(table) {
                        if (!it.name.isNullOrEmpty()) {

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var user = it.name + " " + it.surname

                            var cellUser = addValueSameCell(
                                point,
                                user,
                                style.template_2_Arial_11f(),
                                top = -6f,
                                bottom = -6f
                            )
                            addCell(cellUser)

                            var cellPosition = addValueCell(
                                it.positionName,
                                style.template_2_Arial_11f()?.setPaddingTop(-15f)
                            )
                            addCell(cellPosition)
                        }
                    }
                }
            }
        }
    }

    // About Me
    fun titleAboutMe(table: Table) {
        var title = requireActivity.resources.getString(R.string.summary)
        table.addCell(
            addValueCell(
                title,
                style.template_2_Heebo_Medium_16f(),
                top = 10f,
                bottom = -10f
            )
        )
        table.addCell(createLine(widthRight))
    }

    fun informationAboutMe(table: Table) {
        lifecycleOwner.observe(viewModel.communicationMLD) { communicationData ->
            if (!communicationData.isNullOrEmpty()) {
                communicationData.forEach {
                    with(table) {
                        if (!it.aboutMe.isNullOrEmpty()) {
                            addCell(
                                addValueCell(
                                    it.aboutMe,
                                    style.template_2_Arial_11f(),
                                    bottom = 5f
                                )
                            )
                        }
                    }
                }

            }
        }
    }

    // Experience
    fun titleExperience(table: Table) {
        var title = requireActivity.resources.getString(R.string.Experience)
        table.addCell(addValueCell(title, style.template_2_Heebo_Medium_16f(), bottom = -10f))
        table.addCell(createLine(widthRight))
    }

    fun informationExperience(table: Table) {
        lifecycleOwner.observe(viewModel.experienceMLD) { experience ->
            if (!experience.isNullOrEmpty()) {
                experience?.forEach {
                    with(table) {
                        if (!it.positionName.isNullOrEmpty()) {

                            addCell(
                                addValueCell(
                                    it.positionName,
                                    style.template_2_Heebo_Medium_14f()
                                )
                            )?.setMarginTop(-5f)


                            var companyNameAndDate = Cell()
                            if (!it.finishDate.isNullOrEmpty()) {
                                companyNameAndDate = addValueSameCell(
                                    val1 = it.companyName,
                                    styles1 = style.template_2_Arial_Bold_11f()?.setMarginTop(-14f),
                                    val2 = requireActivity.resources.getString(R.string.verticaLine),
                                    styles2 = style.template_2_Arial_Gray_11f(),
                                    val3 = it.startDate,
                                    styles3 = style.template_2_Arial_Gray_11f(),
                                    val4 = requireActivity.resources.getString(R.string.line),
                                    styles4 = style.template_2_Arial_Gray_11f(),
                                    val5 = it.finishDate,
                                    styles5 = style.template_2_Arial_Gray_11f(),
                                    top = -10f
                                )
                            } else {
                                companyNameAndDate = addValueSameCell(
                                    val1 = it.companyName,
                                    styles1 = style.template_2_Arial_Bold_11f()?.setMarginTop(-14f),
                                    val2 = requireActivity.resources.getString(R.string.verticaLine),
                                    styles2 = style.template_2_Arial_Gray_11f(),
                                    val3 = it.startDate,
                                    styles3 = style.template_2_Arial_Gray_11f(),
                                    val4 = requireActivity.resources.getString(R.string.line),
                                    styles4 = style.template_2_Arial_Gray_11f(),
                                    val5 = requireActivity.resources.getString(R.string.continueEx),
                                    styles5 = style.template_2_Arial_Gray_11f(),
                                    top = -10f
                                )
                            }

                            addCell(companyNameAndDate)


                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cellInfo = Cell()

                            if (!it.infoAboutJob.isNullOrEmpty()) {
                                cellInfo = addValueSameCell(
                                    point,
                                    val1 = it.infoAboutJob,
                                    styles1 = style.template_2_Arial_10f(),
                                    //       top = -5f
                                )
                            } else {
                                addCell(emptyCell())
                            }

                            addCell(cellInfo)
                        }
                    }
                }
            }
        }
    }


    // Education
    fun titleEducation(table: Table) {
        var title = requireActivity.resources.getString(R.string.Education)
        table.addCell(addValueCell(title, style.template_2_Heebo_Medium_16f(), bottom = -10f))
        table.addCell(createLine(widthRight))
    }

    fun informationEducation(table: Table) {
        lifecycleOwner.observe(viewModel.educationMLD) { education ->
            if (!education.isNullOrEmpty()) {
                education?.forEach {
                    with(table) {
                        if (!it.schoolName.isNullOrEmpty()) {

                            if (!it.gano.isNullOrEmpty()) {
                                addCell(
                                    addValueSameCell(
                                        val1 = it.schoolName,
                                        styles1 = style.template_2_Heebo_Medium_14f(),
                                        val2 = requireActivity.resources.getString(R.string.verticaLine),
                                        styles2 = style.template_2_Arial_Gray_105f(),
                                        val3 = it.gano,
                                        styles3 = style.template_2_Arial_Gray_10f(),
                                        top = -5f
                                    )
                                )
                            } else {
                                addCell(emptyCell())
                            }

                            var departmentNameAndDate = addValueSameCell(
                                val1 = it.departmentName,
                                styles1 = style.template_2_Arial_Bold_105f()?.setMarginTop(-14f),
                                val2 = requireActivity.resources.getString(R.string.verticaLine),
                                styles2 = style.template_2_Arial_Gray_105f(),
                                val3 = it.startDate,
                                styles3 = style.template_2_Arial_Gray_105f(),
                                val4 = requireActivity.resources.getString(R.string.line),
                                styles4 = style.template_2_Arial_Gray_105f(),
                                val5 = it.finishDate,
                                styles5 = style.template_2_Arial_Gray_105f(),
                                top = -10f
                            )

                            addCell(departmentNameAndDate)
                        }
                    }
                }
            }
        }
    }

    // Empty
    fun titleEmpty(table: Table) {
        table.addCell(emptyCell())
    }


}
