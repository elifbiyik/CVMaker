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
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eb.cvmaker.R
import com.eb.cvmaker._paragraphStyle.Images
import com.eb.cvmaker._paragraphStyle.Styles
import com.eb.cvmaker.addValueCell
import com.eb.cvmaker.addValueSameCell
import com.eb.cvmaker.createLineCell
import com.eb.cvmaker.observe
import com.eb.cvmaker.ui.SharedPreferencesManager
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

class Template_2 @Inject constructor(
    private val viewModel: ChooseTemplateVM,
    var requireActivity: FragmentActivity,
    var context: Context,
) {

    private val sharedPreferencesManager: SharedPreferencesManager? = null

    val widthLeft = PageSize.A4.width * 0.30f
    val widthSpace = PageSize.A4.width * 0.03f // 2 tablo arası mesafe
    val widthRight = PageSize.A4.width * 0.52f

    var style = Styles()
    var image = Images()

    // lifecycleOwner fragment'ın yaşam döngüsü olaylarını gözlemlemek için kullanılır.
    val lifecycleOwner = requireActivity as LifecycleOwner

    fun generateCV(document: Document) {

        background(document)
        personalInformation(document)
        profile(document)

        val tableLeft = Table(1)
            .setTextAlignment(TextAlignment.LEFT)
            .setHeight(UnitValue.createPercentValue(45f))

        val tableRight = Table(1)
            .setPaddingLeft(widthSpace)
            .setTextAlignment(TextAlignment.LEFT)
            .setHeight(UnitValue.createPercentValue(60f))
            .setMarginRight(18f)
        //   .setFixedPosition(widthSpaceForRight, bottom, widthRight)

        informationCommunicationLeft(tableLeft)
        informationCommunicationRight(tableRight)

        val tableContainer = Table(2)
            .setMarginTop(75f)
            .setHeight(UnitValue.createPercentValue(100f))
            .setWidth(PageSize.A4.width)
            .setMarginLeft(-18f)

        val firstColumn = Cell().add(tableLeft)
            .setWidth(widthLeft)
            .setBorder(Border.NO_BORDER)

        val secondColumn = Cell()
            .add(tableRight)
            .setWidth(widthRight)
            .setBorder(Border.NO_BORDER)


        tableContainer.addCell(firstColumn)
        tableContainer.addCell(secondColumn)

        document.add(tableContainer)
    }

    fun informationCommunicationLeft(tableLeft: Table) {
        personalInformationContact(tableLeft)
        informationSocialMedia(tableLeft)
        informationLanguages(tableLeft)
        informationAbilities(tableLeft)
        informationReferences(tableLeft)
    }

    fun informationCommunicationRight(tableRight: Table) {

        informationAboutMe(tableRight)
        informationExperience(tableRight)
        informationEducation(tableRight)
        informationCertificates(tableRight)
    }

    fun background(document: Document) {
        val A4_WIDTH_MM = 210f
        val A4_HEIGHT_MM = 297f
        val DPI = 72 // veya 96, kullanılan DPI'ye bağlı olarak değiştirin
        // DPI, genellikle bir ekran veya bir yazıcı gibi bir cihazın çözünürlüğünü ifade eder.

// Drawableden resmi alıyor.
        val d1 = context.getDrawable(R.drawable.template2_background)
        val bitmapOriginal = (d1 as BitmapDrawable).bitmap

        val widthPx = (A4_WIDTH_MM / 25.4f * DPI).toInt()
        val heightPx = (A4_HEIGHT_MM / 25.4f * DPI).toInt()
        val bitmapResized = Bitmap.createScaledBitmap(bitmapOriginal, widthPx, heightPx, false)

// Resmi PDF'e ekle.
        val stream1 = ByteArrayOutputStream()
        bitmapResized.compress(
            Bitmap.CompressFormat.JPEG,
            100,
            stream1
        ) // PDF dosyasına eklenmek üzere JPEG formatına dönüştürülür.
        val byte = stream1.toByteArray()

        val imageData1 = ImageDataFactory.create(byte)
        val image1 = Image(imageData1)

        document.add(image1.setFixedPosition(0f, 0f))

    }

    fun profile(document: Document) {
        var filePath = sharedPreferencesManager?.getUserImage()

        if (filePath != null) {
            try {
                val file = File(filePath)
                val inputStream = FileInputStream(file)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                var circleBitmap = cropToCircle(bitmap)

                val outputStream = ByteArrayOutputStream()
                circleBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                val imageData: ByteArray = outputStream.toByteArray()

                val image = Image(ImageDataFactory.create(imageData))

                image.scaleToFit(120f, 120f)
                image.setFixedPosition(30f, 710f)

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

    fun personalInformation(document: Document) {

        var table = Table(1)
            .setWidth(UnitValue.createPercentValue(100f))
            .setMarginTop(-20f)
            .setMarginLeft(110f)

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {

                    val valuesName = listOf(
                        it.name to style.template_2_Name_Surname(),
                        requireActivity.resources.getString(R.string.space) to style.template_2_Name_Surname(),
                        it.surname?.toUpperCase() to style.template_2_Name_Surname(),
                    )

                    val valuesJob =
                        if (!it.job.isNullOrEmpty()) {
                            listOf(it.job?.toUpperCase() to style.template_2_Job())
                        } else {
                            listOf()
                        }

                    var cellNameAndSurname = addValueSameCell(
                        values = valuesName,
                        marginBottomParagraph = -35f,
                        alignment = TextAlignment.CENTER
                    )
                    table.addCell(cellNameAndSurname)

                    var cellJob = addValueSameCell(
                        values = valuesJob,
                        marginBottomParagraph = -35f,
                        alignment = TextAlignment.CENTER
                    )
                    table.addCell(cellJob)
                }
                document.add(table)
            }
        }
    }

    // Communication
    fun personalInformationContact(table: Table) {
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {

                    if (!it.phone.isNullOrEmpty() || !it.job.isNullOrEmpty() || !it.email.isNullOrEmpty()) {
                        var title = requireActivity.resources.getString(R.string.contact)
                        titleInformation(table, title, widthLeft)
                    }

                    val valuesPhone = listOf(
                        it.phone to style.template_2_Arial_11f(),
                    )

                    val valuesEmail = listOf(
                        it.email to style.template_2_Arial_11f(),
                    )

                    val valuesAddress = listOf(
                        it.address to style.template_2_Arial_11f(),
                    )

                    with(table) {
                        // Phone
                        if (!it.phone.isNullOrEmpty()) {
                            var imagePhone = image.image(context, R.drawable.baseline_phone_24)
                            var phone = addValueSameCell(
                                image = imagePhone,
                                values = valuesPhone,
                                paddingTop = 10f
                            )
                            addCell(phone)
                        }

                        // Mail
                        if (!it.email.isNullOrEmpty()) {
                            var imageMail = image.image(context, R.drawable.baseline_email_24)
                            var mail = addValueSameCell(
                                image = imageMail,
                                values = valuesEmail
                            )
                            addCell(mail)
                        }

                        // Location
                        if (!it.address.isNullOrEmpty()) {
                            var imageLocation =
                                image.image(context, R.drawable.baseline_location_on_24)
                            var address = addValueSameCell(
                                image = imageLocation,
                                values = valuesAddress
                            )
                            addCell(address)
                        }
                    }
                }
            }
        }
    }

    // Social Media
    fun informationSocialMedia(table: Table) {
        val imageGithub = image.image(context, R.drawable.github_)
        val imageLinkedin = image.image(context, R.drawable.linkedin_)
        val imageWebSite = image.image(context, R.drawable.website_)

        lifecycleOwner.observe(viewModel.socialMediaMLD) { socialMedia ->
            if (!socialMedia.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.SocialMedia)
                titleInformation(table, title, widthLeft)

                socialMedia.forEach {
                    val valuesGithub = listOf(
                        it.github to style.template_2_Arial_105f(),
                    )
                    val valuesLinkedIn = listOf(
                        it.linkedIn to style.template_2_Arial_105f(),
                    )
                    val valuesWebSite = listOf(
                        it.webSite to style.template_2_Arial_105f(),
                    )

                    with(table) {
                        if (!it.github.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageGithub,
                                values = valuesGithub,
                                paddingTop = 10f
                            )
                            addCell(cell)
                        }

                        if (!it.linkedIn.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageLinkedin,
                                values = valuesLinkedIn
                            )
                            addCell(cell)
                        }

                        if (!it.webSite.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageWebSite,
                                values = valuesWebSite
                            )
                            addCell(cell)
                        }
                    }
                }
            }
        }
    }

    // Language
    fun informationLanguages(table: Table) {

        lifecycleOwner.observe(viewModel.languageMLD) { socialMedia ->
            if (!socialMedia.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.Languages)
                titleInformation(table, title, widthLeft)

                socialMedia.forEach {
                    val valuesLanguageAndLevel = listOf(
                        it.languageName to style.template_2_Arial_11f(),
                        requireActivity.resources.getString(R.string.dots) to style.template_2_Arial_11f(),
                        requireActivity.resources.getString(R.string.space) to style.template_2_Arial_11f(),
                        it.level to style.template_2_Arial_11f(),
                    )

                    with(table) {
                        if (!it.languageName.isNullOrEmpty()) {

                            var cell = addValueSameCell(
                                values = valuesLanguageAndLevel
                            )
                            addCell(cell)
                        }
                    }
                }
            }
        }
    }

    // Skills
    fun informationAbilities(table: Table) {
        lifecycleOwner.observe(viewModel.abilitiesMLD) { skills ->
            if (!skills.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.skills)
                titleInformation(table, title, widthLeft)


                skills.forEach {
                    val valuesAbilities = listOf(
                        it.abilitiesName to style.template_2_Arial_11f(),
                    )

                    with(table) {
                        if (!it.abilitiesName.isNullOrEmpty()) {

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cell = addValueSameCell(
                                image = point,
                                values = valuesAbilities
                            )
                            addCell(cell)
                        }
                    }
                }
            }
        }
    }

    // References
    fun informationReferences(table: Table) {
        lifecycleOwner.observe(viewModel.referencesMLD) { reference ->
            if (!reference.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.References)
                titleInformation(table, title, widthLeft)

                reference.forEach {
                    with(table) {
                        if (!it.name.isNullOrEmpty()) {

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var user = it.name + " " + it.surname

                            val valuesReferences = listOf(
                                user to style.template_2_Arial_11f(),
                            )

                            var cellUser = addValueSameCell(
                                image = point,
                                values = valuesReferences
                            )
                            addCell(cellUser)
                        }

                        if (!it.positionName.isNullOrEmpty()) {

                            val valuesReferencesPosition = listOf(
                                it.positionName to style.template_2_Arial_Gray_10f(),
                            )

                            var cellPosition = addValueSameCell(
                                values = valuesReferencesPosition,
                                paddingTop = -5f,
                                paddingLeft = 5f
                            )
                            addCell(cellPosition)
                        }

                        if (!it.email.isNullOrEmpty()) {

                            val valuesReferencesEmail = listOf(
                                it.email to style.template_2_Arial_Gray_10f(),
                            )
                            var cellEmail = addValueSameCell(
                                values = valuesReferencesEmail,
                                paddingTop = -5f,
                                paddingLeft = 5f

                            )
                            addCell(cellEmail)
                        }

                        if (!it.phone.isNullOrEmpty()) {

                            val valuesReferencesPhone = listOf(
                                it.phone to style.template_2_Arial_Gray_10f(),
                            )
                            var cellPhone = addValueSameCell(
                                values = valuesReferencesPhone,
                                paddingTop = -5f,
                                paddingLeft = 5f
                            )
                            addCell(cellPhone)


                        }
                    }
                }
            }
        }
    }

    // About Me
    fun informationAboutMe(table: Table) {
        lifecycleOwner.observe(viewModel.communicationMLD) { communicationData ->
            if (!communicationData.isNullOrEmpty()) {
                communicationData.forEach {
                    with(table) {
                        if (!it.aboutMe.isNullOrEmpty()) {

                            var title = requireActivity.resources.getString(R.string.summary)
                            titleInformation(table, title, widthRight)

                            val valuesAboutMe = listOf(
                                it.aboutMe to style.template_2_Arial_11f(),
                            )

                            var aboutMe = addValueSameCell(
                                values = valuesAboutMe,
                                paddingBottom = 5f
                            )
                            addCell(aboutMe)
                        }
                    }
                }
            }
        }
    }

    // Experience
    fun informationExperience(table: Table) {
        lifecycleOwner.observe(viewModel.experienceMLD) { experience ->
            if (!experience.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.Experience)
                titleInformation(table, title, widthRight)

                experience?.forEach {
                    with(table) {

                        if (!it.positionName.isNullOrEmpty()) {

                            val valuesPosition = listOf(
                                it.positionName to style.template_2_Heebo_Medium_14f(),
                            )

                            var positionName = addValueSameCell(
                                values = valuesPosition,
                            )
                            addCell(positionName)
                        }
                        if (!it.companyName.isNullOrEmpty()) {
                            var companyNameAndDate = Cell()

                            val valuesDate = listOf(
                                it.companyName to style.template_2_Arial_Bold_11f(),
                                requireActivity.resources.getString(R.string.verticaLine) to style.template_2_Arial_Gray_11f(),
                                it.startDate to style.template_2_Arial_Gray_11f(),
                                requireActivity.resources.getString(R.string.line) to style.template_2_Arial_Gray_11f(),
                                if (!it.finishDate.isNullOrEmpty()) {
                                    it.finishDate to style.template_2_Arial_Gray_11f()
                                } else {
                                    requireActivity.resources.getString(R.string.present) to style.template_2_Arial_Gray_11f()
                                }
                            )

                            companyNameAndDate = addValueSameCell(
                                values = valuesDate,
                                paddingTop = -10f,
                                paddingLeft = 2f
                            )

                            addCell(companyNameAndDate)
                        }

                        if (!it.infoAboutJob.isNullOrEmpty()) {
                            val valuesInfo = listOf(
                                it.infoAboutJob to style.template_2_Arial_905f(),
                            )

                            var cellInfo = addValueSameCell(
                                values = valuesInfo
                            )
                            addCell(cellInfo)
                        }
                    }
                }
            }
        }
    }

    // Education
    fun informationEducation(table: Table) {
        lifecycleOwner.observe(viewModel.educationMLD) { education ->
            if (!education.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.Education)
                titleInformation(table, title, widthRight)

                education?.forEach {
                    with(table) {
                        if (!it.schoolName.isNullOrEmpty()) {
                            var schoolNameAndGano = Cell()
                            if (!it.gano.isNullOrEmpty()) {

                                val valuesSchool = listOf(
                                    it.schoolName to style.template_2_Heebo_Medium_14f(),
                                    requireActivity.resources.getString(R.string.verticaLine) to style.template_2_Arial_Gray_105f(),
                                    it.gano to style.template_2_Arial_Gray_10f(),
                                )
                                schoolNameAndGano = addValueSameCell(
                                    values = valuesSchool
                                )
                            } else {
                                val valuesSchool = listOf(
                                    it.schoolName to style.template_2_Heebo_Medium_14f()
                                )
                                schoolNameAndGano = addValueSameCell(
                                    values = valuesSchool
                                )
                            }

                            addCell(schoolNameAndGano)
                        }

                        if (!it.departmentName.isNullOrEmpty()) {
                            val valuesDepartment = listOf(
                                it.departmentName to style.template_2_Arial_Bold_105f(),
                                requireActivity.resources.getString(R.string.verticaLine) to style.template_2_Arial_Gray_105f(),
                                it.startDate to style.template_2_Arial_Gray_105f(),
                                requireActivity.resources.getString(R.string.line) to style.template_2_Arial_Gray_105f(),
                                it.finishDate to style.template_2_Arial_Gray_105f(),
                            )


                            var departmentNameAndDate = addValueSameCell(
                                values = valuesDepartment,
                                paddingTop = -10f,
                                paddingLeft = 2f
                            )
                            addCell(departmentNameAndDate)
                        }
                    }
                }
            }
        }
    }

    // Education
    fun informationCertificates(table: Table) {
        lifecycleOwner.observe(viewModel.certificateMLD) { certificates ->
            if (!certificates.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.Certificates)
                titleInformation(table, title, lineWidht = widthRight)

                certificates?.forEach {
                    with(table) {
                        if (!it.certificateProjectOrAwardName.isNullOrEmpty()) {
                            val valuesCertificates = listOf(
                                it.certificateProjectOrAwardName to style.template_2_Heebo_Medium_14f(),
                            )

                            var certificateName = addValueSameCell(
                                values = valuesCertificates,
                                paddingTop = -5f
                            )
                            addCell(certificateName)
                        }

                        if (!it.educationPlace.isNullOrEmpty()) {
                            val valuesCertificates = listOf(
                                it.educationPlace to style.template_2_Arial_Bold_11f(),
                                requireActivity.resources.getString(R.string.verticaLine) to style.template_2_Arial_Gray_11f(),
                                it.startDate to style.template_2_Arial_Gray_11f(),
                                requireActivity.resources.getString(R.string.line) to style.template_2_Arial_Gray_11f(),
                                it.finishDate to style.template_2_Arial_Gray_11f(),
                            )

                            var educationPlaceAndDate = addValueSameCell(
                                values = valuesCertificates,
                                paddingTop = -10f
                            )
                            addCell(educationPlaceAndDate)
                        }

                        if (!it.aboutCertificate.isNullOrEmpty()) {
                            val valuesAboutCertificates = listOf(
                                it.aboutCertificate to style.template_2_Arial_905f()
                            )

                            var cellInfo = addValueSameCell(values = valuesAboutCertificates)
                            addCell(cellInfo)
                        }
                    }
                }
            }
        }
    }

    fun titleInformation(
        table: Table,
        title: String,
        lineWidht: Float,
    ) {
        table.addCell(
            addValueCell(
                title,
                style.template_2_Heebo_Medium_16f(),
                bottom = -10f,
            )
        )
        table.addCell(createLineCell(lineWidht))
    }
}