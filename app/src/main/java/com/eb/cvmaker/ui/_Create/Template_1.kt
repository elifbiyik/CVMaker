package com.eb.cvmaker.ui._Create

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eb.cvmaker.R
import com.eb.cvmaker._paragraphStyle.Images
import com.eb.cvmaker._paragraphStyle.Styles
import com.eb.cvmaker.addText
import com.eb.cvmaker.addValueSameCell
import com.eb.cvmaker.createLineParagraph
import com.eb.cvmaker.emptyCell
import com.eb.cvmaker.observe
import com.eb.cvmaker.title
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import javax.inject.Inject


////https://github.com/itext/itext-java/tree/develop/layout/src/main/java/com/itextpdf/layout/borders
/*
           addCell(Cell().add(Paragraph(it.languageName)).setBorder(Border.NO_BORDER))
           addCell(Cell().add(Paragraph(":")).setBorder(Border.NO_BORDER))
           addCell(Cell().add(Paragraph(it.level)).setBorder(Border.NO_BORDER))

         Böyle yazınca borderlar gitmiyor. Bordersız yukardaki gibi oluyor sadece
         addCell(it.languageName).setBorder(Border.NO_BORDER))
         addCell(":").setBorder(Border.NO_BORDER))
         addCell(it.level).setBorder(Border.NO_BORDER))
 */


class Template_1 @Inject constructor(
    private val viewModel: ChooseTemplateVM,
    var requireActivity: FragmentActivity,
    var context: Context,
) {

    var style = Styles()
    var image = Images()

    // lifecycleOwner fragment'ın yaşam döngüsü olaylarını gözlemlemek için kullanılır.
    val lifecycleOwner = requireActivity as LifecycleOwner // Assuming you're in a fragment


    fun generateCV(document: Document) {

        informationCommunication(document)
        informationSocialMedia(document)
        informationLanguage(document)
        informationEducation(document)
        informationAbilities(document)
        informationExperience(document)
        informationReferences(document)
        informationCertificate(document)
    }

    // Communication
    fun informationCommunication(document: Document) {
        personalInformationNameSurname(document)
        personalInformationJob(document)
        personalInformationAboutMe(document)
        personalInformationCommunication(document)
    }

    fun personalInformationNameSurname(document: Document) {
        val paragraph = Paragraph()
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(paragraph) {

                        var nameUpper = it.name?.toUpperCase()
                        var surnameUpper = it.surname?.toUpperCase()

                        add(
                            addText(
                                nameUpper,
                                style.template_1_Name(),
                                -10f,
                                -10f
                            )
                        ).setTextAlignment(TextAlignment.CENTER)
                        add("  ")
                        add(
                            addText(
                                surnameUpper,
                                style.template_1_Surname(),
                                -10f,
                                -10f
                            )
                        ).setTextAlignment(TextAlignment.CENTER)
                        add("\n")
                    }
                }
                document.add(paragraph)
                if (!paragraph.isEmpty()) {
                    document.add(createLineParagraph())
                }
            }
        }
    }

    fun personalInformationJob(document: Document) {
        val paragraph = Paragraph()

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(paragraph) {
                        if (!it.job.isNullOrEmpty()) {

                            var jobUpper = it.job?.toUpperCase()
                            add(
                                addText(
                                    jobUpper,
                                    style.template_1_Job(),
                                    -5f,
                                    -5f
                                )
                            ).setTextAlignment(TextAlignment.CENTER)
                            add("\n")
                        }
                    }
                }
                document.add(paragraph)
                if (!paragraph.isEmpty()) {
                    document.add(createLineParagraph())
                }
            }
        }
    }

    fun personalInformationAboutMe(document: Document) {
        val paragraph = Paragraph()

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(paragraph) {
                        if (!it.aboutMe.isNullOrEmpty()) {

                            add(addText(it.aboutMe, style.template_1_Calibri_11f(), 5f, 5f))
                            add("\n")
                        }
                    }
                }
                document.add(paragraph)

                if (!paragraph.isEmpty()) {
                    document.add(createLineParagraph())
                }
            }
        }
    }

    fun personalInformationCommunication(document: Document) {

        var table = Table(3)
            .setWidth(UnitValue.createPercentValue(100f))

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(table) {
                        // Phone
                        if (!it.phone.isNullOrEmpty()) {

                            val values = listOf(
                                it.phone to style.template_1_Calibri_11f(),
                            )

                            var imagePhone = image.image(context, R.drawable.baseline_phone_24)
                            var phone = addValueSameCell(
                                image = imagePhone,
                                values = values,
                                paddingTop = 5f,
                                paddingBottom = 5f
                            )
                            addCell(phone)
                        }

                        // Mail
                        if (!it.email.isNullOrEmpty()) {
                            var imageMail = image.image(context, R.drawable.baseline_email_24)
                            val values = listOf(
                                it.email to style.template_1_Calibri_11f(),
                            )
                            var email = addValueSameCell(
                                image = imageMail,
                                values = values,
                                paddingTop = 5f,
                                paddingBottom = 5f
                            )
                            addCell(email)
                        }

                        if (!it.address.isNullOrEmpty()) {
                            // Location
                            var imageLocation =
                                image.image(context, R.drawable.baseline_location_on_24)

                            val values = listOf(
                                it.address to style.template_1_Calibri_11f()
                            )

                            var address = addValueSameCell(
                                image = imageLocation,
                                values = values,
                                paddingTop = 5f,
                                paddingBottom = 5f
                            )
                            addCell(address)
                        }
                    }
                }
                document.add(table)
                if (!table.isEmpty()) {
                    document.add(createLineParagraph())
                }
            }
        }
    }

    // Social Media
    fun informationSocialMedia(document: Document) {
        var table = Table(3).setWidth(UnitValue.createPercentValue(100f))
        val imageGithub = image.image(context, R.drawable.github_)
        val imageLinkedin = image.image(context, R.drawable.linkedin_)
        val imageWebSite = image.image(context, R.drawable.website_)

        lifecycleOwner.observe(viewModel.socialMediaMLD) { socialMedia ->
            if (!socialMedia.isNullOrEmpty()) {
                socialMedia.forEach {
                    with(table) {
                        if (!it.github.isNullOrEmpty()) {

                            var values = listOf(
                                it.github to style.template_1_Calibri_11f()
                            )
                            var cell = addValueSameCell(
                                imageGithub,
                                values = values,
                                paddingTop = 5f,
                                paddingBottom = 5f
                            )
                            addCell(cell)
                        }

                        if (!it.linkedIn.isNullOrEmpty()) {

                            var values = listOf(
                                it.linkedIn to style.template_1_Calibri_11f()
                            )

                            var cell = addValueSameCell(
                                imageLinkedin,
                                values = values,
                                paddingTop = 5f,
                                paddingBottom = 5f
                            )
                            addCell(cell)
                        }

                        if (!it.webSite.isNullOrEmpty()) {

                            var values = listOf(
                                it.webSite to style.template_1_Calibri_11f()
                            )

                            var cell = addValueSameCell(
                                imageWebSite,
                                values = values,
                                paddingTop = 5f,
                                paddingBottom = 5f
                            )
                            addCell(cell)
                        }
                    }
                }
                document.add(table)
                if (!table.isEmpty()) {
                    document.add(createLineParagraph())
                }
            }
        }
    }

    // Language
    fun titleLanguage(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.Languages)
        return title(infoParagraph, title, style.template_1_Title())
    }

    fun informationLanguage(document: Document) {
        var table = Table(4)
            .setWidth(UnitValue.createPercentValue(100f))

        lifecycleOwner.observe(viewModel.languageMLD) { language ->
            if (!language.isNullOrEmpty()) {
                language?.forEach {
                    with(table) {
                        if (!it.languageName.isNullOrEmpty()) {

                            var values = listOf(
                                it.languageName to style.template_1_Calibri_10f(),
                                requireActivity.resources.getString(R.string.dots) to style.template_1_Calibri_10f(),
                                it.level to style.template_1_Calibri_10f()
                            )

                            var cell = addValueSameCell(values = values)
                            addCell(cell)
                        }
                    }
                }

                if (!table.isEmpty()) {
                    document.add(titleLanguage())
                    document.add(table)
                    document.add(createLineParagraph())
                }
            }
        }
    }

    // Education
    fun titleEducation(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.Education)
        return title(infoParagraph, title, style.template_1_Title())
    }

    fun informationEducation(document: Document) {
        var table = Table(3).setWidth(UnitValue.createPercentValue(100f))

        lifecycleOwner.observe(viewModel.educationMLD) { education ->
            if (!education.isNullOrEmpty()) {
                education?.forEach {
                    with(table) {
                        if (!it.schoolName.isNullOrEmpty()) {

                            var valuesDate = listOf(
                                it.startDate to style.template_1_Calibri_11f(),
                                requireActivity.resources.getString(R.string.line) to style.template_1_Calibri_11f(),
                                it.finishDate to style.template_1_Calibri_11f()
                            )
                            var valuesSchool = listOf(
                                it.schoolName to style.template_1_CalibriBold_11f()
                            )

                            var date = addValueSameCell(values = valuesDate)
                            addCell(date)

                            var schoolName = addValueSameCell(values = valuesSchool)
                            addCell(schoolName)
                        } else {
                            addCell(emptyCell())
                            addCell(emptyCell())
                        }

                        if (!it.gano.isNullOrEmpty()) {
                            var values = listOf(
                                it.gano to style.template_1_Calibri_10f()
                            )
                            var gano = addValueSameCell(values = values)
                            addCell(gano)
                        } else {
                            addCell(emptyCell())
                        }

                        addCell(emptyCell())

                        if (!it.departmentName.isNullOrEmpty()) {
                            var values = listOf(
                                it.departmentName to style.template_1_CalibriItalic_10f()
                            )
                            var departmentName = addValueSameCell(
                                values = values,
                                paddingTop = -10f
                            )
                            addCell(departmentName)
                        } else {
                            addCell(emptyCell())
                        }

                        addCell(emptyCell())
                    }
                }
            }

            if (!table.isEmpty()) {
                document.add(titleEducation())
                document.add(table)
                document.add(createLineParagraph())
            }
        }
    }

    // Abilities
    fun titleAbilities(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.Abilities)
        return title(infoParagraph, title, style.template_1_Title())
    }

    fun informationAbilities(document: Document) {

        var paragraph = Paragraph()
        lifecycleOwner.observe(viewModel.abilitiesMLD) { abilities ->
            if (!abilities.isNullOrEmpty()) {
                abilities?.forEach {
                    var point = image.image(context, R.drawable.baseline_point_24)
                    if (!it.abilitiesName.isNullOrEmpty()) {
                        paragraph.add(" ")
                        paragraph.add(point)
                        paragraph.add(" ")
                        paragraph.add(Text(it.abilitiesName).addStyle(style.template_1_Calibri_10f()))
                        paragraph.add("  ")
                    }
                }

                if (!paragraph.isEmpty()) {
                    document.add(titleAbilities())
                    document.add(paragraph)
                    document.add(createLineParagraph())
                }
            }
        }
    }

    // Experience
    fun titleExperience(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.Experience)
        return title(infoParagraph, title, style.template_1_Title())
    }

    fun informationExperience(document: Document) {
        var table = Table(2).setWidth(UnitValue.createPercentValue(100f))

        lifecycleOwner.observe(viewModel.experienceMLD) { experience ->
            if (!experience.isNullOrEmpty()) {
                experience?.forEach {
                    with(table) {

                        if (!it.positionName.isNullOrEmpty()) {
                            var date = Cell()

                            var values = listOf(
                                it.startDate to style.template_1_Calibri_11f(),
                                requireActivity.resources.getString(R.string.line) to style.template_1_Calibri_11f(),
                                if (!it.finishDate.isNullOrEmpty()) {
                                    it.finishDate to style.template_1_Calibri_11f()
                                } else {
                                    requireActivity.resources.getString(R.string.present) to style.template_1_Calibri_11f()
                                }
                            )

                            date = addValueSameCell(values = values).setWidth(UnitValue.createPercentValue(30f))
                            addCell(date)

                            val valuesName = listOf(
                                it.positionName to style.template_1_CalibriBold_11f()
                            )

                            var positionName = addValueSameCell(values = valuesName)
                            addCell(positionName)
                        } else {
                            addCell(emptyCell())
                            addCell(emptyCell())
                        }

                        addCell(emptyCell()) // Date'in altı boş olucak

                        if (!it.companyName.isNullOrEmpty()) {
                            val values = listOf(
                                it.companyName to style.template_1_Calibri_Bold_9f()
                            )

                            var companyName = addValueSameCell(
                                values = values,
                                paddingTop = -10f
                            )
                            addCell(companyName)
                        } else {
                            addCell(emptyCell())
                        }

                        addCell(emptyCell())

                        if (!it.infoAboutJob.isNullOrEmpty()) {
                            val values = listOf(
                                it.infoAboutJob to style.template_1_Calibri_9f()
                            )
                            var infoJob = addValueSameCell(
                                values = values,
                                paddingTop = -7f
                            )
                            addCell(infoJob)
                        } else {
                            addCell(emptyCell())
                        }
                    }
                }
            }

            if (!table.isEmpty()) {
                document.add(titleExperience())
                document.add(table)
                document.add(createLineParagraph())
            }
        }
    }

    // References
    fun titleReferences(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.References)
        return title(infoParagraph, title, style.template_1_Title())
    }

    fun informationReferences(document: Document) {
        var table = Table(4)

        lifecycleOwner.observe(viewModel.referencesMLD) { abilities ->
            with(table) {
                if (!abilities.isNullOrEmpty()) {
                    abilities?.forEach {
                        if (!it.name.isNullOrEmpty()) {

                            val values = listOf(
                                it.name to style.template_1_Calibri_11f(),
                                requireActivity.resources.getString(R.string.space) to style.template_1_Calibri_11f(),
                                it.surname to style.template_1_Calibri_11f(),
                            )

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cell = addValueSameCell(
                                image = point,
                                values = values
                            )
                            addCell(cell)

                            if (!it.positionName.isNullOrEmpty()) {
                                val values = listOf(
                                    it.positionName to style.template_1_Calibri_11f(),
                               )
                                var positionName = addValueSameCell(values = values)
                                addCell(positionName)
                            } else {
                                addCell(emptyCell())
                            }

                            if (!it.email.isNullOrEmpty()) {
                                val values = listOf(
                                    it.email to style.template_1_Calibri_11f(),
                                )
                                var email = addValueSameCell(values = values)
                                addCell(email)
                            } else {
                                addCell(emptyCell())
                            }

                            if (!it.phone.isNullOrEmpty()) {
                                val values = listOf(
                                    it.phone to style.template_1_Calibri_11f(),
                                )
                                var phone = addValueSameCell(values = values)
                                addCell(phone)
                            } else {
                                addCell(emptyCell())
                            }
                        }
                    }

                    setWidth(UnitValue.createPercentValue(100f))

                    if (!table.isEmpty()) {
                        document.add(titleReferences())
                        document.add(table)
                        document.add(createLineParagraph())
                    }
                }
            }
        }
    }

    // Certificates
    fun titleCertificates(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.Certificates)
        return title(infoParagraph, title, style.template_1_Title())
    }

    fun informationCertificate(document: Document) {
        var table = Table(2).setWidth(UnitValue.createPercentValue(100f))

        lifecycleOwner.observe(viewModel.certificateMLD) { experience ->
            if (!experience.isNullOrEmpty()) {
                experience?.forEach {
                    with(table) {

                        if (!it.certificateProjectOrAwardName.isNullOrEmpty()) {
                            var date = Cell()

                            var values = listOf(
                                it.startDate to style.template_1_Calibri_11f(),
                                requireActivity.resources.getString(R.string.line) to style.template_1_Calibri_11f(),
                                if (!it.finishDate.isNullOrEmpty()) {
                                    it.finishDate to style.template_1_Calibri_11f()
                                } else {
                                    requireActivity.resources.getString(R.string.present) to style.template_1_Calibri_11f()
                                }
                            )

                            date = addValueSameCell(values = values).setWidth(UnitValue.createPercentValue(30f))
                            addCell(date)

                            val valuesName = listOf(
                                it.certificateProjectOrAwardName  to style.template_1_CalibriBold_11f()
                            )

                            var certificateName = addValueSameCell(values = valuesName)
                            addCell(certificateName)
                        } else {
                            addCell(emptyCell())
                            addCell(emptyCell())
                        }

                        addCell(emptyCell()) // Date'in altı boş olucak

                        if (!it.educationPlace.isNullOrEmpty()) {
                            val values = listOf(
                                it.educationPlace to style.template_1_Calibri_Bold_9f()
                            )

                            var companyName = addValueSameCell(
                                values = values,
                                paddingTop = -10f
                            )
                            addCell(companyName)
                        } else {
                            addCell(emptyCell())
                        }

                        addCell(emptyCell())

                        if (!it.aboutCertificate.isNullOrEmpty()) {
                            val values = listOf(
                                it.aboutCertificate to style.template_1_Calibri_9f()
                            )
                            var infoJob = addValueSameCell(
                                values = values,
                                paddingTop = -7f
                            )
                            addCell(infoJob)
                        } else {
                            addCell(emptyCell())
                        }
                    }
                }
            }

            if (!table.isEmpty()) {
                document.add(titleCertificates())
                document.add(table)
                document.add(createLineParagraph())
            }
        }
    }

}
