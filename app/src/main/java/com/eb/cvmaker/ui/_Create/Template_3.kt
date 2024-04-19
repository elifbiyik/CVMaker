package com.eb.cvmaker.ui._Create

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

class Template_3 @Inject constructor(
    private val viewModel: ChooseTemplateVM,
    var requireActivity: FragmentActivity,
    var context: Context,
) {

    val widthLeft = PageSize.A4.width * 0.35f
    val widthSpace = PageSize.A4.width * 0.03f // 2 tablo arası mesafe
    val widthRight = PageSize.A4.width * 0.52f

    var style = Styles()
    var image = Images()

    // lifecycleOwner fragment'ın yaşam döngüsü olaylarını gözlemlemek için kullanılır.
    val lifecycleOwner = requireActivity as LifecycleOwner

    fun generateCV(document: Document) {

        background(document)

        val tableLeft = Table(1)
            .setTextAlignment(TextAlignment.LEFT)
            .setWidth(widthLeft)


        val tableRight = Table(1)
            .setPaddingLeft(widthSpace)
            .setTextAlignment(TextAlignment.LEFT)

        informationCommunicationLeft(tableLeft)
        informationCommunicationRight(tableRight)

        val tableContainer = Table(2)
            .setMarginTop(15f)
            .setMarginBottom(10f)
            .setHeight(UnitValue.createPercentValue(100f))
            .setWidth(PageSize.A4.width)
            .setMarginLeft(-10f)

        val firstColumn = Cell()
            .add(tableLeft)
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
        profile(tableLeft)
        informationAboutMe(tableLeft)
        personalInformationContact(tableLeft)
        informationSocialMedia(tableLeft)
        informationAbilities(tableLeft)
        //    informationReferences(tableLeft)
    }

    fun informationCommunicationRight(tableRight: Table) {
        personalInformation(tableRight)
        informationExperience(tableRight)
        informationEducation(tableRight)
        informationLanguages(tableRight)
        informationCertificates(tableRight)
    }

    fun background(document: Document) {
        val A4_WIDTH_MM = 210f
        val A4_HEIGHT_MM = 297f
        val DPI = 72 // veya 96, kullanılan DPI'ye bağlı olarak değiştirin
        // DPI, genellikle bir ekran veya bir yazıcı gibi bir cihazın çözünürlüğünü ifade eder.

// Drawableden resmi alıyor.
        val d1 = context.getDrawable(R.drawable.template3_background)
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

    fun profile(table: Table) {
        var imagePath = getLocaleSharedPreferances()

        if (imagePath != null) {
            try {
                val file = File(imagePath)
                val inputStream = FileInputStream(file)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                val imageData: ByteArray = outputStream.toByteArray()

                val image = Image(ImageDataFactory.create(imageData))

                image.scaleToFit(190f, 220f)
                image.setFixedPosition(30f, 580f)

                var cell = Cell().add(image).setBorder(Border.NO_BORDER)
                table.addCell(cell)

            } catch (e: Exception) {
                Log.e("Template_3", "Resim yüklemede hata:", e)
            }
        } else {
            Log.e("Template_3", "Resim path'i bulunamadı")
            message(context, requireActivity.resources.getString(R.string.imagePath))
        }
    }

    fun getLocaleSharedPreferances(): String? {
        var sharedPreferences =
            context?.getSharedPreferences("UserImagePath", AppCompatActivity.MODE_PRIVATE)
        var imagePath = sharedPreferences?.getString("userImage", "")
        return imagePath
    }

    // About Me
    fun informationAboutMe(table: Table) {
        lifecycleOwner.observe(viewModel.communicationMLD) { communicationData ->
            if (!communicationData.isNullOrEmpty()) {
                communicationData.forEach {
                    with(table) {
                        if (!it.aboutMe.isNullOrEmpty()) {

                            var title = requireActivity.resources.getString(R.string.summary)
                            titleInformation(table, title, 220f)

                            val valuesAboutMe = listOf(
                                it.aboutMe to style.template_3_Arial_12f(),
                            )

                            var aboutMe = addValueSameCell(
                                values = valuesAboutMe,
                                paddingBottom = 5f,
                                paddingTop = -5f,
                            )
                            addCell(aboutMe)
                        }
                    }
                }
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
                        titleInformation(table, title)
                    }

                    val valuesPhone = listOf(
                        it.phone to style.template_3_Arial_Gray_12f(),
                    )

                    val valuesEmail = listOf(
                        it.email to style.template_3_Arial_Gray_12f(),
                    )

                    val valuesAddress = listOf(
                        it.address to style.template_3_Arial_Gray_12f(),
                    )

                    with(table) {
                        // Phone
                        if (!it.phone.isNullOrEmpty()) {
                            var imagePhone = image.image(context, R.drawable.baseline_phone_24)
                            var phone = addValueSameCell(
                                image = imagePhone,
                                values = valuesPhone,
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
                titleInformation(table, title)

                socialMedia.forEach {
                    val valuesGithub = listOf(
                        it.github to style.template_3_Arial_Gray_10f(),
                    )
                    val valuesLinkedIn = listOf(
                        it.linkedIn to style.template_3_Arial_Gray_10f(),
                    )
                    val valuesWebSite = listOf(
                        it.webSite to style.template_3_Arial_Gray_10f(),
                    )

                    with(table) {
                        if (!it.github.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageGithub,
                                values = valuesGithub,
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

    // Skills
    fun informationAbilities(table: Table) {
        lifecycleOwner.observe(viewModel.abilitiesMLD) { skills ->
            if (!skills.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.skills)
                titleInformation(table, title)

                skills.forEach {
                    val valuesAbilities = listOf(
                        it.abilitiesName to style.template_3_Arial_10f(),
                    )

                    with(table) {
                        if (!it.abilitiesName.isNullOrEmpty()) {

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cell = addValueSameCell(
                                image = point,
                                values = valuesAbilities,
                                marginBottomParagraph = -6f
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
                titleInformation(table, title)

                reference.forEach {
                    with(table) {
                        if (!it.name.isNullOrEmpty()) {

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var user = it.name + " " + it.surname

                            val valuesReferences = listOf(
                                user to style.template_3_Arial_10f(),
                            )

                            var cellUser = addValueSameCell(
                                image = point,
                                values = valuesReferences
                            )
                            addCell(cellUser)
                        }

                        if (!it.positionName.isNullOrEmpty()) {

                            val valuesReferencesPosition = listOf(
                                it.positionName to style.template_3_Arial_Gray_10f(),
                            )

                            var cellPosition = addValueSameCell(
                                values = valuesReferencesPosition,
                                paddingTop = -8f,
                                paddingLeft = 10f
                            )
                            addCell(cellPosition)
                        }

                        if (!it.email.isNullOrEmpty()) {

                            val valuesReferencesEmail = listOf(
                                it.email to style.template_3_Arial_Gray_10f(),
                            )
                            var cellEmail = addValueSameCell(
                                values = valuesReferencesEmail,
                                paddingTop = -8f,
                                paddingLeft = 10f

                            )
                            addCell(cellEmail)
                        }

                        if (!it.phone.isNullOrEmpty()) {

                            val valuesReferencesPhone = listOf(
                                it.phone to style.template_3_Arial_Gray_10f(),
                            )
                            var cellPhone = addValueSameCell(
                                values = valuesReferencesPhone,
                                paddingTop = -8f,
                                paddingLeft = 10f
                            )
                            addCell(cellPhone)
                        }
                    }
                }
            }
        }
    }


    fun personalInformation(table: Table) {

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {

                    val valuesName = listOf(
                        it.name?.toUpperCase() to style.template_3_Arial_28f(),
                        requireActivity.resources.getString(R.string.space) to style.template_3_Arial_28f(),
                        it.surname?.toUpperCase() to style.template_3_Arial_28f(),
                    )

                    val valuesJob =
                        if (!it.job.isNullOrEmpty()) {
                            listOf(it.job?.toUpperCase() to style.template_3_Arial_14f())
                        } else {
                            listOf()
                        }

                    var cellNameAndSurname = addValueSameCell(
                        values = valuesName,
                        paddingTop = 10f
                    )
                    table.addCell(cellNameAndSurname)

                    var cellJob = addValueSameCell(
                        values = valuesJob,
                        paddingBottom = 15f
                    )
                    table.addCell(cellJob)
                }
            }
        }
    }

    // Experience
    fun informationExperience(table: Table) {
        lifecycleOwner.observe(viewModel.experienceMLD) { experience ->
            if (!experience.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.Experience)
                titleInformation(table, title)

                experience?.forEach {
                    with(table) {

                        if (!it.positionName.isNullOrEmpty()) {

                            val valuesPosition = listOf(
                                it.positionName?.toUpperCase() to style.template_3_Arial_Bold_Gray_12f(),
                            )

                            var positionName = addValueSameCell(
                                values = valuesPosition,
                                paddingLeft = 2f,
                                paddingTop = 5f
                            )
                            addCell(positionName)
                        }
                        if (!it.companyName.isNullOrEmpty()) {
                            var companyNameAndDate = Cell()

                            val valuesDate = listOf(
                                it.companyName to style.template_3_Arial_Gray_12f(),
                                requireActivity.resources.getString(R.string.verticaLine) to style.template_3_Arial_Gray_10f(),
                                it.startDate to style.template_3_Arial_Gray_10f(),
                                requireActivity.resources.getString(R.string.line) to style.template_3_Arial_Gray_10f(),
                                if (!it.finishDate.isNullOrEmpty()) {
                                    it.finishDate to style.template_3_Arial_Gray_10f()
                                } else {
                                    requireActivity.resources.getString(R.string.present) to style.template_3_Arial_Gray_10f()
                                }
                            )

                            companyNameAndDate = addValueSameCell(
                                values = valuesDate,
                                paddingLeft = 2f,
                                paddingTop = -10f
                            )

                            addCell(companyNameAndDate)
                        }

                        if (!it.infoAboutJob.isNullOrEmpty()) {
                            val valuesInfo = listOf(
                                it.infoAboutJob to style.template_3_Arial_11f(),
                            )

                            var cellInfo = addValueSameCell(
                                values = valuesInfo,
                                paddingLeft = 2f,
                                paddingTop = -6f,
                                paddingBottom = 5f
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
                titleInformation(table, title)

                education?.forEach {
                    with(table) {
                        if (!it.schoolName.isNullOrEmpty()) {
                            var schoolNameAndGano = Cell()
                            if (!it.gano.isNullOrEmpty()) {

                                val valuesSchool = listOf(
                                    it.schoolName?.toUpperCase() to style.template_3_Arial_Bold_Gray_12f(),
                                    requireActivity.resources.getString(R.string.verticaLine) to style.template_3_Arial_Gray_10f(),
                                    it.gano to style.template_3_Arial_Gray_10f(),
                                )
                                schoolNameAndGano = addValueSameCell(
                                    values = valuesSchool,
                                    paddingLeft = 2f,
                                )
                            } else {
                                val valuesSchool = listOf(
                                    it.schoolName to style.template_3_Arial_Bold_Gray_12f()
                                )
                                schoolNameAndGano = addValueSameCell(
                                    values = valuesSchool,
                                    paddingLeft = 2f,
                                )
                            }

                            addCell(schoolNameAndGano)
                        }

                        if (!it.departmentName.isNullOrEmpty()) {
                            val valuesDepartment = listOf(
                                it.departmentName to style.template_3_Arial_Gray_10f(),
                                requireActivity.resources.getString(R.string.verticaLine) to style.template_3_Arial_Gray_10f(),
                                it.startDate to style.template_3_Arial_Gray_10f(),
                                requireActivity.resources.getString(R.string.line) to style.template_3_Arial_Gray_10f(),
                                it.finishDate to style.template_3_Arial_Gray_10f(),
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

    // Language
    fun informationLanguages(table: Table) {

        lifecycleOwner.observe(viewModel.languageMLD) { socialMedia ->
            if (!socialMedia.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.Languages)
                titleInformation(table, title)

                socialMedia.forEach {
                    val valuesLanguageAndLevel = listOf(
                        it.languageName to style.template_3_Arial_10f(),
                        requireActivity.resources.getString(R.string.dots) to style.template_3_Arial_10f(),
                        requireActivity.resources.getString(R.string.space) to style.template_3_Arial_10f(),
                        it.level to style.template_3_Arial_10f(),
                    )

                    with(table) {
                        if (!it.languageName.isNullOrEmpty()) {

                            var cell = addValueSameCell(
                                values = valuesLanguageAndLevel,
                                paddingLeft = 2f,
                            )
                            addCell(cell)
                        }
                    }
                }
            }
        }
    }

    // Certificates
    fun informationCertificates(table: Table) {
        lifecycleOwner.observe(viewModel.certificateMLD) { certificates ->
            if (!certificates.isNullOrEmpty()) {

                var title = requireActivity.resources.getString(R.string.Certificates)
                titleInformation(table, title)

                certificates?.forEach {
                    with(table) {
                        if (!it.certificateProjectOrAwardName.isNullOrEmpty()) {
                            val valuesCertificates = listOf(
                                it.certificateProjectOrAwardName?.toUpperCase() to style.template_3_Arial_Bold_Gray_12f(),
                            )

                            var certificateName = addValueSameCell(
                                values = valuesCertificates,
                                paddingTop = -5f,
                                paddingLeft = 2f
                            )
                            addCell(certificateName)
                        }

                        if (!it.educationPlace.isNullOrEmpty()) {
                            val valuesCertificates = listOf(
                                it.educationPlace to style.template_3_Arial_Gray_12f(),
                                requireActivity.resources.getString(R.string.verticaLine) to style.template_3_Arial_Gray_12f(),
                                it.startDate to style.template_3_Arial_Gray_12f(),
                                requireActivity.resources.getString(R.string.line) to style.template_3_Arial_Gray_12f(),
                                it.finishDate to style.template_3_Arial_Gray_12f(),
                            )

                            var educationPlaceAndDate = addValueSameCell(
                                values = valuesCertificates,
                                paddingTop = -10f,
                                paddingLeft = 2f
                            )
                            addCell(educationPlaceAndDate)
                        }

                        if (!it.aboutCertificate.isNullOrEmpty()) {
                            val valuesAboutCertificates = listOf(
                                it.aboutCertificate to style.template_3_Arial_10f()
                            )

                            var cellInfo = addValueSameCell(
                                values = valuesAboutCertificates,
                                paddingLeft = 2f,
                                paddingTop = -6f,
                                paddingBottom = 5f
                            )
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
        marginTop: Float? = 10f,
        paddingBottom: Float? = 2f
    ) {
        table.addCell(
            addValueCell(
                title,
                style.template_3_Arial_16f(),
                marginTop = marginTop,
                marginBottom = paddingBottom

            )
        )
        //     table.addCell(createLineCell(lineWidht))
    }
}