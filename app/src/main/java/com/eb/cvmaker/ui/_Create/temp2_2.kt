package com.eb.cvmaker.ui._Create

class temp2_2 {

    /*
    package com.eb.cvmaker.ui._Create

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eb.cvmaker.R
import com.eb.cvmaker._paragraphStyle.Images
import com.eb.cvmaker._paragraphStyle.Styles
import com.eb.cvmaker.addValueCell
import com.eb.cvmaker.addValueSameCell
import com.eb.cvmaker.cellStyleSameCell
import com.eb.cvmaker.emptyCell
import com.eb.cvmaker.halfLine
import com.eb.cvmaker.observe
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import javax.inject.Inject


class Template_2 @Inject constructor(
    private val viewModel: ChooseTemplateVM,
    var requireActivity: FragmentActivity,
    var context: Context,
) {

    var style = Styles()
    var image = Images()

    var pageHeight = 1120f
    var pageWidth = 792f

    // lifecycleOwner fragment'ın yaşam döngüsü olaylarını gözlemlemek için kullanılır.
    val lifecycleOwner = requireActivity as LifecycleOwner // Assuming you're in a fragment

    fun generateCV(document: Document) {

        background(document)
        personalInformation(document)

        /*     val widthLeft = PageSize.A4.width * 0.3f
             val widthSpaceForRight = PageSize.A4.width * 0.38f
             val widthRight = PageSize.A4.width * 0.6f

             val tableLeft = Table(1).setWidth(widthLeft).setTextAlignment(TextAlignment.LEFT)
                 .setHeight(UnitValue.createPercentValue(100f)).setMarginTop(140f)

             val tableRight = Table(2).setWidth(widthRight).setTextAlignment(TextAlignment.LEFT)
                 .setFixedPosition( widthSpaceForRight,0f,widthRight ).setHeight(UnitValue.createPercentValue(100f))

             informationCommunicationLeft(tableLeft)
             informationCommunicationRight(tableRight)

             document.add(tableLeft)
             document.add(tableRight)*/

        /*
                // Left column content
                val leftColumn = createLeftColumnContentpackage com.eb.cvmaker.ui._Create

class temp2_2 {

    /*
    package com.eb.cvmaker.ui._Create

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eb.cvmaker.R
import com.eb.cvmaker._paragraphStyle.Images
import com.eb.cvmaker._paragraphStyle.Styles
import com.eb.cvmaker.addValueCell
import com.eb.cvmaker.addValueSameCell
import com.eb.cvmaker.cellStyleSameCell
import com.eb.cvmaker.emptyCell
import com.eb.cvmaker.halfLine
import com.eb.cvmaker.observe
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import javax.inject.Inject


class Template_2 @Inject constructor(
    private val viewModel: ChooseTemplateVM,
    var requireActivity: FragmentActivity,
    var context: Context,
) {

    var style = Styles()
    var image = Images()

    var pageHeight = 1120f
    var pageWidth = 792f

    // lifecycleOwner fragment'ın yaşam döngüsü olaylarını gözlemlemek için kullanılır.
    val lifecycleOwner = requireActivity as LifecycleOwner // Assuming you're in a fragment

    fun generateCV(document: Document) {

        background(document)
        personalInformation(document)

        /*     val widthLeft = PageSize.A4.width * 0.3f
             val widthSpaceForRight = PageSize.A4.width * 0.38f
             val widthRight = PageSize.A4.width * 0.6f

             val tableLeft = Table(1).setWidth(widthLeft).setTextAlignment(TextAlignment.LEFT)
                 .setHeight(UnitValue.createPercentValue(100f)).setMarginTop(140f)

             val tableRight = Table(2).setWidth(widthRight).setTextAlignment(TextAlignment.LEFT)
                 .setFixedPosition( widthSpaceForRight,0f,widthRight ).setHeight(UnitValue.createPercentValue(100f))

             informationCommunicationLeft(tableLeft)
             informationCommunicationRight(tableRight)

             document.add(tableLeft)
             document.add(tableRight)*/

        /*
                // Left column content
                val leftColumn = createLeftColumnContent()

                // Right column content
                val rightColumn = createRightColumnContent()

                // Create a two-column layout with equal widths
                val twoColumnLayout = Table(2)
                    .setWidth(UnitValue.createPercentValue(100f)) // Full page width
                    .setMarginTop(32f) // Set top margin for both columns

                twoColumnLayout.addCell(leftColumn)
                twoColumnLayout.addCell(rightColumn)

                document.add(twoColumnLayout)

                */

        /*   val leftColumnTable = createLeftColumnContent()

           // Right column content (titles and information)
           val rightColumnTable = createRightColumnContent()

           // Create a two-column layout with equal widths
           val twoColumnLayout = Table(2)
               .setWidth(UnitValue.createPercentValue(100f)) // Full page width
               .setMarginTop(32f);

           // Set appropriate widths for left and right columns (30% and 70% respectively)
           val leftColumnWidth = UnitValue.createPercentValue(50f)
           val rightColumnWidth = UnitValue.createPercentValue(90f)
           twoColumnLayout.addCell(leftColumnTable.setWidth(leftColumnWidth))
           twoColumnLayout.addCell(rightColumnTable.setWidth(rightColumnWidth))

           document.add(twoColumnLayout)
   */

        val widthLeft = PageSize.A4.width * 0.3f
        val widthSpaceForRight = PageSize.A4.width * 0.38f
        val widthRight = PageSize.A4.width * 0.6f

        // İki tabloyu içeren bir tablo oluştur
        val tableContainer = Table(2)

        // Sol taraftaki tabloyu oluştur ve tablo konteynerine ekle
        val tableLeft = Table(2)
        informationCommunicationLeft(tableLeft)
        tableLeft.setWidth(widthLeft) // Sol taraftaki tablonun genişliğini ayarla
        tableContainer.addCell(tableLeft).setWidth(widthLeft)

        // Sağ taraftaki tabloyu oluştur ve tablo konteynerine ekle
        val tableRight = Table(2)
        informationCommunicationRight(tableRight)
        tableRight.setWidth(widthRight) // Sağ taraftaki tablonun genişliğini ayarla
        //  tableRight.setWidth(UnitValue.createPercentValue(70f)) // Sağ taraftaki tablonun genişliğini yüzde cinsinden ayarla
        tableContainer.addCell(tableRight).setWidth(widthRight)


        // Tablo konteynerini belgeye ekle
        document.add(tableContainer)



    }

    fun createLeftColumnContent(): Table {
        val tableLeft = Table(1)
        //        .setWidth(PageSize.A4.width * 0.7f) // 30% of page width

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
        /*
                val leftColumnCell = Cell()
                    .setWidth(UnitValue.createPercentValue(100f)) // Full cell width
                    .add(tableLeft)
                    .setWidth(PageSize.A4.width * 0.3f)
                    .setPadding(0f) // Remove default padding for better alignment*/

        return tableLeft
    }

    fun createEmptyColumnContent(): Table {
        val tableLeft = Table(1)

        return tableLeft
    }

    fun createRightColumnContent(): Table {
        val tableRight = Table(2)
        //     .setWidth(PageSize.A4.width * 0.7f) // 70% of page width

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

        /*      val rightColumnCell = Cell()
                  .setWidth(UnitValue.createPercentValue(100f)) // Full cell width
                  .add(tableRight)
                  .setWidth(PageSize.A4.width * 0.7f)
                  .setPadding(0f) // Remove default padding for better alignment
      */
        return tableRight
    }




    fun informationCommunicationLeft(tableLeft: Table) {
        //      pr(document)
        //    profile(document)
        //    personalInformation(tableLeft)

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


    fun pr(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {

                        var im = it.image
                        var data = ImageDataFactory.create(im)
                        var image = Image(data)
                        document.add(image)
                    }
                }
            }
        }
    }

    // TODO  manifestte android:requestLegacyExternalStorage="true" yapınca resimlere erişme izni istiyor
    fun profile(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {

                        Log.d("xxxxxELİF", it.image.toString())
                        val imageBytes = Base64.decode(it.image, Base64.DEFAULT)
                        val decodedImage =
                            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        var scaledbmp = Bitmap.createScaledBitmap(decodedImage, 140, 140, false)
                        //           var imageData1 = ImageDataFactory.create(scaledbmp)

                        var imageData11 = ImageDataFactory.create(imageBytes)
                        var image1 = Image(imageData11)


                        document.add(image1.setFixedPosition(20f, 20f))


                        /*                    val file = File(it.image) // Dosyanın yolunu içeren URI'yi kullanarak dosyayı alın
                                            val imageBytes = file.readBytes() // Dosyayı okuyun ve byte dizisine dönüştürün
                                            val imageData1 = ImageDataFactory.create(imageBytes) // Base64 verisi oluşturun
                                            val image1 = Image(imageData1) // Image nesnesini oluşturun
                                            document.add(image1) // PDF dokümanına görüntüyü ekleyin
                    *//*

                                                val imageBytess = Base64.decode(it.image, Base64.DEFAULT)
                                                val imageData = ImageDataFactory.create(imageBytess) // Use the create(bytes: ByteArray) method
                                                val paragraph = Paragraph("Bu bir örnek paragraftır.")
                                                val image = Image(imageData)
                                                val container = Paragraph().add(paragraph).add(image) // Create a Paragraph as a container
                                                document.add(container) // Add the container to the document

                        */

                    }
                }
            }
        }
    }

    fun personalProfilePhotooo(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {
                        val imageUrl = communication.image

                        Log.d("xxxxxELİF", imageUrl.toString())
                        Log.d("xxxxxELİFit", it.toString())
                        if (!imageUrl.isNullOrEmpty()) {
                            try {
                                //      val imageData = ImageDataFactory.create(imageUrl)
                                //         val image = Image(imageData)
                                Log.d("xxxxxELİFimage", image.toString())

                                val uri = Uri.parse(imageUrl)
                                val path = context.contentResolver.openInputStream(uri)

                                //    val imageData = ImageDataFactory.create(URL(path))
                                //     val image = Image(imageData)

                                //      document.add(image)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    fun personalProfilePhotov(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {
                        val imageUrl = communication.image

                        Log.d("xxxxxELİF", imageUrl.toString())
                        Log.d("xxxxxELİFit", it.toString())
                        if (!imageUrl.isNullOrEmpty()) {
                            try {
                                val contentUri = Uri.parse(imageUrl)
                                context?.contentResolver?.openInputStream(contentUri)
                                    ?.use { inputStream ->
                                        // Resmi byte dizisine okuyun
                                        val byteArray = inputStream.readBytes()
                                        // Byte dizisinden ImageData oluşturun
                                        val imageData = ImageDataFactory.create(byteArray)
                                        // ImageData'dan Image oluşturun ve belgeye ekleyin
                                        val image = Image(imageData)
                                        document.add(image)
                                        document.add(image)
                                    }
                            } catch (e: Exception) {
                                Log.d("xxxxxxxxxTEMPLATE", e.message.toString())
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    fun personalProfilePhotoo(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) { communicationList ->
            if (communicationList.isNullOrEmpty()) return@observe

            communicationList.forEach { communication ->
                val imageUrl = communication.image

                if (!imageUrl.isNullOrEmpty()) {
                    try {
                        val contentUri = Uri.parse(imageUrl)
                        if (ContextCompat.checkSelfPermission(
                                context!!, Manifest.permission.READ_EXTERNAL_STORAGE
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            context?.contentResolver?.openFileDescriptor(contentUri, "r")
                                ?.use { fileDescriptor ->
                                    val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
                                    val byteArray = inputStream.readBytes()
                                    val imageData = ImageDataFactory.create(byteArray)
                                    val image = Image(imageData)
                                    document.add(image)
                                }
                        } else {
                            // Request READ_EXTERNAL_STORAGE permission here
                        }
                    } catch (e: Exception) {
                        Log.e("xxxxxTEMPLATE", e.message.toString())
                        e.printStackTrace()
                    }
                }
            }
        }
    }


    fun personalInformation(document: Document) {

        var table = Table(1)
        table.setMarginTop(0f)

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {

                    var cellNameAndSurname = cellStyleSameCell(
                        it.name,
                        style.styleTemplate2ForName(),
                        it.surname?.toUpperCase(),
                        style.styleTemplate2ForSurname(),
                        TextAlignment.CENTER
                    )
                    table.addCell(cellNameAndSurname)

                    var cellJob = cellStyleSameCell(
                        val1 = it.job?.toUpperCase(),
                        style.styleTemplate2ForJob(),
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
    }

    fun personalInformationContact(table: Table) {
        val width = PageSize.A4.width * 0.3f
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
                                    styles1 = style.styleTemplate2ForText(),
                                    top = -12f
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
                                    styles1 = style.styleTemplate2ForText(),
                                ).setPaddingTop(-12f)
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
                                    styles1 = style.styleTemplate2ForText()
                                ).setPaddingTop(-15f)
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))

    }

    fun informationSocialMedia(table: Table) {
        val width = PageSize.A4.width * 0.3f

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
                                styles1 = style.styleTemplate2ForText10f(),
                            )
                            addCell(cell)
                        }

                        if (!it.linkedIn.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageLinkedin,
                                val1 = it.linkedIn,
                                styles1 = style.styleTemplate2ForText10f(),
                            ).setPaddingTop(-12f)
                            addCell(cell)
                        }

                        if (!it.webSite.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageWebSite,
                                val1 = it.webSite,
                                styles1 = style.styleTemplate2ForText10f()
                            ).setPaddingTop(-12f)
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                styles1 = style.styleTemplate2ForText(),
                                val2 = requireActivity.resources.getString(R.string.dots),
                                val3 = requireActivity.resources.getString(R.string.space),
                                val4 = it.level,
                                styles4 = style.styleTemplate2ForText(),
                                top = -9f,
                                bottom = -9f
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                    style.styleTemplate2ForText(),
                                    top = -9f,
                                    bottom = -9f
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                style.styleTemplate2ForTextBold(),
                                top = -6f,
                                bottom = -6f
                            )
                            addCell(cellUser)

                            var cellPosition = addValueCell(
                                it.positionName,
                                style.styleTemplate2ForText10f()?.setPaddingTop(-15f)
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
    }

    fun informationAboutMe(table: Table) {
        lifecycleOwner.observe(viewModel.communicationMLD) { communicationData ->
            if (!communicationData.isNullOrEmpty()) {
                communicationData.forEach {
                    with(table) {
                        if (!it.phone.isNullOrEmpty()) {
                            addCell(
                                addValueCell(
                                    it.aboutMe,
                                    style.styleTemplate2ForText()
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                    style.styleTemplate2ForTextLight()
                                )
                            )?.setMarginTop(-8f)

                            addCell(emptyCell())

                            addCell(
                                addValueCell(
                                    it.companyName,
                                    style.styleTemplate2ForText8fBold()?.setMarginTop(-18f)
                                )
                            )

                            var cellDate = Cell()
                            if (!it.finishDate.isNullOrEmpty()) {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.styleTemplate2ForTextMediumGray(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    styles2 = style.styleTemplate2ForTextMediumGray(),
                                    val3 = it.finishDate,
                                    styles3 = style.styleTemplate2ForTextMediumGray(),
                                    top = -8f
                                )
                            } else {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.styleTemplate2ForTextMediumGray(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    styles2 = style.styleTemplate2ForTextMediumGray(),
                                    val3 = requireActivity.resources.getString(R.string.continueEx),
                                    styles3 = style.styleTemplate2ForTextMediumGray(),
                                    top = -8f
                                )
                            }

                            addCell(cellDate)


                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cellInfo = Cell()

                            if (!it.infoAboutJob.isNullOrEmpty()) {
                                cellInfo = addValueSameCell(
                                    point,
                                    val1 = it.infoAboutJob,
                                    styles1 = style.styleTemplate2ForText8f(),
                                    top = -20f
                                )
                            } else {
                                addCell(emptyCell())
                            }


                            addCell(cellInfo)

                            addCell(emptyCell())

                        }
                    }
                }
            }
        }
    }


    // Education
    fun titleEducation(table: Table) {
        var title = requireActivity.resources.getString(R.string.Education)
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
        table.addCell(emptyCell(-20f))
        table.addCell(halfLine(100f))

    }

    fun informationEducation(table: Table) {
        lifecycleOwner.observe(viewModel.educationMLD) { education ->
            if (!education.isNullOrEmpty()) {
                education?.forEach {
                    with(table) {
                        if (!it.schoolName.isNullOrEmpty()) {

                            addCell(
                                addValueCell(
                                    it.schoolName,
                                    style.styleTemplate2ForTextLight()
                                )
                            )?.setMarginTop(-8f)

                            if (!it.gano.isNullOrEmpty()) {
                                addCell(
                                    addValueCell(
                                        it.gano,
                                        style.styleTemplate2ForText10f()
                                    )?.setMarginTop(-5f)
                                )
                            } else {
                                addCell(emptyCell())
                            }

                            addCell(
                                addValueCell(
                                    it.departmentName,
                                    style.styleTemplate2ForText8fBold()
                                )
                            )?.setMarginTop(-20f)

                            addCell(
                                addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.styleTemplate2ForTextMediumGray(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    styles2 = style.styleTemplate2ForTextMediumGray(),
                                    val3 = it.finishDate,
                                    styles3 = style.styleTemplate2ForTextMediumGray(),
                                    top = -20f
                                )
                            )
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

    fun halfLineLeft (table: Table, width : Float): Paragraph {
        return halfLine(width)
    }

}


}

                // Right column content
                val rightColumn = createRightColumnContent()

                // Create a two-column layout with equal widths
                val twoColumnLayout = Table(2)
                    .setWidth(UnitValue.createPercentValue(100f)) // Full page width
                    .setMarginTop(32f) // Set top margin for both columns

                twoColumnLayout.addCell(leftColumn)
                twoColumnLayout.addCell(rightColumn)

                document.add(twoColumnLayout)

                */

        /*   val leftColumnTable = createLeftColumnContent()

           // Right column content (titles and information)
           val rightColumnTable = createRightColumnContent()

           // Create a two-column layout with equal widths
           val twoColumnLayout = Table(2)
               .setWidth(UnitValue.createPercentValue(100f)) // Full page width
               .setMarginTop(32f);

           // Set appropriate widths for left and right columns (30% and 70% respectively)
           val leftColumnWidth = UnitValue.createPercentValue(50f)
           val rightColumnWidth = UnitValue.createPercentValue(90f)
           twoColumnLayout.addCell(leftColumnTable.setWidth(leftColumnWidth))
           twoColumnLayout.addCell(rightColumnTable.setWidth(rightColumnWidth))

           document.add(twoColumnLayout)
   */

        val widthLeft = PageSize.A4.width * 0.3f
        val widthSpaceForRight = PageSize.A4.width * 0.38f
        val widthRight = PageSize.A4.width * 0.6f

        // İki tabloyu içeren bir tablo oluştur
        val tableContainer = Table(2)

        // Sol taraftaki tabloyu oluştur ve tablo konteynerine ekle
        val tableLeft = Table(2)
        informationCommunicationLeft(tableLeft)
        tableLeft.setWidth(widthLeft) // Sol taraftaki tablonun genişliğini ayarla
        tableContainer.addCell(tableLeft).setWidth(widthLeft)

        // Sağ taraftaki tabloyu oluştur ve tablo konteynerine ekle
        val tableRight = Table(2)
        informationCommunicationRight(tableRight)
        tableRight.setWidth(widthRight) // Sağ taraftaki tablonun genişliğini ayarla
        //  tableRight.setWidth(UnitValue.createPercentValue(70f)) // Sağ taraftaki tablonun genişliğini yüzde cinsinden ayarla
        tableContainer.addCell(tableRight).setWidth(widthRight)


        // Tablo konteynerini belgeye ekle
        document.add(tableContainer)



    }

    fun createLeftColumnContent(): Table {
        val tableLeft = Table(1)
        //        .setWidth(PageSize.A4.width * 0.7f) // 30% of page width

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
        /*
                val leftColumnCell = Cell()
                    .setWidth(UnitValue.createPercentValue(100f)) // Full cell width
                    .add(tableLeft)
                    .setWidth(PageSize.A4.width * 0.3f)
                    .setPadding(0f) // Remove default padding for better alignment*/

        return tableLeft
    }

    fun createEmptyColumnContent(): Table {
        val tableLeft = Table(1)

        return tableLeft
    }

    fun createRightColumnContent(): Table {
        val tableRight = Table(2)
        //     .setWidth(PageSize.A4.width * 0.7f) // 70% of page width

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

        /*      val rightColumnCell = Cell()
                  .setWidth(UnitValue.createPercentValue(100f)) // Full cell width
                  .add(tableRight)
                  .setWidth(PageSize.A4.width * 0.7f)
                  .setPadding(0f) // Remove default padding for better alignment
      */
        return tableRight
    }




    fun informationCommunicationLeft(tableLeft: Table) {
        //      pr(document)
        //    profile(document)
        //    personalInformation(tableLeft)

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

    fun personalProfilePhoto(document: Document) {
        val paragraph = Paragraph()

        val imageData = image.image(context, R.drawable.communication)

        paragraph.add(imageData)
        document.setMargins(pageHeight, pageWidth, pageHeight, pageWidth)
        document.add(paragraph)

    }

    fun pr(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {

                        var im = it.image
                        var data = ImageDataFactory.create(im)
                        var image = Image(data)
                        document.add(image)
                    }
                }
            }
        }
    }

    // TODO  manifestte android:requestLegacyExternalStorage="true" yapınca resimlere erişme izni istiyor
    fun profile(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {

                        Log.d("xxxxxELİF", it.image.toString())
                        val imageBytes = Base64.decode(it.image, Base64.DEFAULT)
                        val decodedImage =
                            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        var scaledbmp = Bitmap.createScaledBitmap(decodedImage, 140, 140, false)
                        //           var imageData1 = ImageDataFactory.create(scaledbmp)

                        var imageData11 = ImageDataFactory.create(imageBytes)
                        var image1 = Image(imageData11)


                        document.add(image1.setFixedPosition(20f, 20f))


                        /*                    val file = File(it.image) // Dosyanın yolunu içeren URI'yi kullanarak dosyayı alın
                                            val imageBytes = file.readBytes() // Dosyayı okuyun ve byte dizisine dönüştürün
                                            val imageData1 = ImageDataFactory.create(imageBytes) // Base64 verisi oluşturun
                                            val image1 = Image(imageData1) // Image nesnesini oluşturun
                                            document.add(image1) // PDF dokümanına görüntüyü ekleyin
                    *//*

                                                val imageBytess = Base64.decode(it.image, Base64.DEFAULT)
                                                val imageData = ImageDataFactory.create(imageBytess) // Use the create(bytes: ByteArray) method
                                                val paragraph = Paragraph("Bu bir örnek paragraftır.")
                                                val image = Image(imageData)
                                                val container = Paragraph().add(paragraph).add(image) // Create a Paragraph as a container
                                                document.add(container) // Add the container to the document

                        */

                    }
                }
            }
        }
    }

    fun personalProfilePhotooo(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {
                        val imageUrl = communication.image

                        Log.d("xxxxxELİF", imageUrl.toString())
                        Log.d("xxxxxELİFit", it.toString())
                        if (!imageUrl.isNullOrEmpty()) {
                            try {
                                //      val imageData = ImageDataFactory.create(imageUrl)
                                //         val image = Image(imageData)
                                Log.d("xxxxxELİFimage", image.toString())

                                val uri = Uri.parse(imageUrl)
                                val path = context.contentResolver.openInputStream(uri)

                                //    val imageData = ImageDataFactory.create(URL(path))
                                //     val image = Image(imageData)

                                //      document.add(image)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    fun personalProfilePhotov(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach { communication ->
                    communication?.let {
                        val imageUrl = communication.image

                        Log.d("xxxxxELİF", imageUrl.toString())
                        Log.d("xxxxxELİFit", it.toString())
                        if (!imageUrl.isNullOrEmpty()) {
                            try {
                                val contentUri = Uri.parse(imageUrl)
                                context?.contentResolver?.openInputStream(contentUri)
                                    ?.use { inputStream ->
                                        // Resmi byte dizisine okuyun
                                        val byteArray = inputStream.readBytes()
                                        // Byte dizisinden ImageData oluşturun
                                        val imageData = ImageDataFactory.create(byteArray)
                                        // ImageData'dan Image oluşturun ve belgeye ekleyin
                                        val image = Image(imageData)
                                        document.add(image)
                                        document.add(image)
                                    }
                            } catch (e: Exception) {
                                Log.d("xxxxxxxxxTEMPLATE", e.message.toString())
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    fun personalProfilePhotoo(document: Document) {
        lifecycleOwner.observe(viewModel.communicationMLD) { communicationList ->
            if (communicationList.isNullOrEmpty()) return@observe

            communicationList.forEach { communication ->
                val imageUrl = communication.image

                if (!imageUrl.isNullOrEmpty()) {
                    try {
                        val contentUri = Uri.parse(imageUrl)
                        if (ContextCompat.checkSelfPermission(
                                context!!, Manifest.permission.READ_EXTERNAL_STORAGE
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            context?.contentResolver?.openFileDescriptor(contentUri, "r")
                                ?.use { fileDescriptor ->
                                    val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
                                    val byteArray = inputStream.readBytes()
                                    val imageData = ImageDataFactory.create(byteArray)
                                    val image = Image(imageData)
                                    document.add(image)
                                }
                        } else {
                            // Request READ_EXTERNAL_STORAGE permission here
                        }
                    } catch (e: Exception) {
                        Log.e("xxxxxTEMPLATE", e.message.toString())
                        e.printStackTrace()
                    }
                }
            }
        }
    }


    fun personalInformation(document: Document) {

        var table = Table(1)
        table.setMarginTop(0f)

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {

                    var cellNameAndSurname = cellStyleSameCell(
                        it.name,
                        style.styleTemplate2ForName(),
                        it.surname?.toUpperCase(),
                        style.styleTemplate2ForSurname(),
                        TextAlignment.CENTER
                    )
                    table.addCell(cellNameAndSurname)

                    var cellJob = cellStyleSameCell(
                        val1 = it.job?.toUpperCase(),
                        style.styleTemplate2ForJob(),
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
    }

    fun personalInformationContact(table: Table) {
        val width = PageSize.A4.width * 0.3f
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
                                    styles1 = style.styleTemplate2ForText(),
                                    top = -12f
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
                                    styles1 = style.styleTemplate2ForText(),
                                ).setPaddingTop(-12f)
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
                                    styles1 = style.styleTemplate2ForText()
                                ).setPaddingTop(-15f)
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))

    }

    fun informationSocialMedia(table: Table) {
        val width = PageSize.A4.width * 0.3f

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
                                styles1 = style.styleTemplate2ForText10f(),
                            )
                            addCell(cell)
                        }

                        if (!it.linkedIn.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageLinkedin,
                                val1 = it.linkedIn,
                                styles1 = style.styleTemplate2ForText10f(),
                            ).setPaddingTop(-12f)
                            addCell(cell)
                        }

                        if (!it.webSite.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageWebSite,
                                val1 = it.webSite,
                                styles1 = style.styleTemplate2ForText10f()
                            ).setPaddingTop(-12f)
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                styles1 = style.styleTemplate2ForText(),
                                val2 = requireActivity.resources.getString(R.string.dots),
                                val3 = requireActivity.resources.getString(R.string.space),
                                val4 = it.level,
                                styles4 = style.styleTemplate2ForText(),
                                top = -9f,
                                bottom = -9f
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                    style.styleTemplate2ForText(),
                                    top = -9f,
                                    bottom = -9f
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                style.styleTemplate2ForTextBold(),
                                top = -6f,
                                bottom = -6f
                            )
                            addCell(cellUser)

                            var cellPosition = addValueCell(
                                it.positionName,
                                style.styleTemplate2ForText10f()?.setPaddingTop(-15f)
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
    }

    fun informationAboutMe(table: Table) {
        lifecycleOwner.observe(viewModel.communicationMLD) { communicationData ->
            if (!communicationData.isNullOrEmpty()) {
                communicationData.forEach {
                    with(table) {
                        if (!it.phone.isNullOrEmpty()) {
                            addCell(
                                addValueCell(
                                    it.aboutMe,
                                    style.styleTemplate2ForText()
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
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
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
                                    style.styleTemplate2ForTextLight()
                                )
                            )?.setMarginTop(-8f)

                            addCell(emptyCell())

                            addCell(
                                addValueCell(
                                    it.companyName,
                                    style.styleTemplate2ForText8fBold()?.setMarginTop(-18f)
                                )
                            )

                            var cellDate = Cell()
                            if (!it.finishDate.isNullOrEmpty()) {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.styleTemplate2ForTextMediumGray(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    styles2 = style.styleTemplate2ForTextMediumGray(),
                                    val3 = it.finishDate,
                                    styles3 = style.styleTemplate2ForTextMediumGray(),
                                    top = -8f
                                )
                            } else {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.styleTemplate2ForTextMediumGray(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    styles2 = style.styleTemplate2ForTextMediumGray(),
                                    val3 = requireActivity.resources.getString(R.string.continueEx),
                                    styles3 = style.styleTemplate2ForTextMediumGray(),
                                    top = -8f
                                )
                            }

                            addCell(cellDate)


                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cellInfo = Cell()

                            if (!it.infoAboutJob.isNullOrEmpty()) {
                                cellInfo = addValueSameCell(
                                    point,
                                    val1 = it.infoAboutJob,
                                    styles1 = style.styleTemplate2ForText8f(),
                                    top = -20f
                                )
                            } else {
                                addCell(emptyCell())
                            }


                            addCell(cellInfo)

                            addCell(emptyCell())

                        }
                    }
                }
            }
        }
    }


    // Education
    fun titleEducation(table: Table) {
        var title = requireActivity.resources.getString(R.string.Education)
        table.addCell(addValueCell(title, style.styleTemplate2ForTitle()))
        table.addCell(emptyCell(-20f))
        table.addCell(halfLine(100f))

    }

    fun informationEducation(table: Table) {
        lifecycleOwner.observe(viewModel.educationMLD) { education ->
            if (!education.isNullOrEmpty()) {
                education?.forEach {
                    with(table) {
                        if (!it.schoolName.isNullOrEmpty()) {

                            addCell(
                                addValueCell(
                                    it.schoolName,
                                    style.styleTemplate2ForTextLight()
                                )
                            )?.setMarginTop(-8f)

                            if (!it.gano.isNullOrEmpty()) {
                                addCell(
                                    addValueCell(
                                        it.gano,
                                        style.styleTemplate2ForText10f()
                                    )?.setMarginTop(-5f)
                                )
                            } else {
                                addCell(emptyCell())
                            }

                            addCell(
                                addValueCell(
                                    it.departmentName,
                                    style.styleTemplate2ForText8fBold()
                                )
                            )?.setMarginTop(-20f)

                            addCell(
                                addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.styleTemplate2ForTextMediumGray(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    styles2 = style.styleTemplate2ForTextMediumGray(),
                                    val3 = it.finishDate,
                                    styles3 = style.styleTemplate2ForTextMediumGray(),
                                    top = -20f
                                )
                            )
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

    fun halfLineLeft (table: Table, width : Float): Paragraph {
        return halfLine(width)
    }

}


     */

*/
}

