package com.eb.cvmaker.ui._Create

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eb.cvmaker.R
import com.eb.cvmaker._paragraphStyle.Images
import com.eb.cvmaker._paragraphStyle.Styles
import com.eb.cvmaker.addText
import com.eb.cvmaker.addValueCell
import com.eb.cvmaker.addValueSameCell
import com.eb.cvmaker.emptyCell
import com.eb.cvmaker.line
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
    }

    // Communication
    fun informationCommunication(document: Document) {
        personalInformationNameSurname(document)
        personalInformationJob(document)
        personalInformationAboutMe(document)
        personalInformationCommunication(document)
    }

    fun personalInformationNameSurname(document: Document) {
        val infoParagraph = Paragraph()
        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(infoParagraph) {

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
                document.add(infoParagraph)
                document.add(line())
            }
        }
    }

    fun personalInformationJob(document: Document) {
        val infoParagraph = Paragraph()

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(infoParagraph) {
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
                document.add(infoParagraph)
                document.add(line())
            }
        }
    }

    fun personalInformationAboutMe(document: Document) {
        val infoParagraph = Paragraph()

        lifecycleOwner.observe(viewModel.communicationMLD) {
            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(infoParagraph) {
                        if (!it.aboutMe.isNullOrEmpty()) {

                            add(addText(it.aboutMe, style.template_1_Calibri_11f(), 5f, 5f))
                            add("\n")
                        }
                    }
                }
                document.add(infoParagraph)
                document.add(line())
            }
        }
    }

    fun personalInformationCommunication(document: Document) {

        var table = Table(3)
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
                                    styles1 = style.template_1_Calibri_11f(),
                                    top = 5f,
                                    bottom = 5f
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
                                    styles1 = style.template_1_Calibri_11f(),
                                    top = 5f,
                                    bottom = 5f
                                )
                            )
                        }

                        if (!it.address.isNullOrEmpty()) {
                            // Location
                            var imageLocation =
                                image.image(context, R.drawable.baseline_location_on_24)
                            addCell(
                                addValueSameCell(
                                    image = imageLocation,
                                    val1 = it.address,
                                    styles1 = style.template_1_Calibri_11f(),
                                    top = 5f,
                                    bottom = 5f
                                )
                            )
                        }
                        setWidth(UnitValue.createPercentValue(100f))
                    }
                }
                document.add(table)
                document.add(line())
            }
        }
    }

    // Social Media
    fun informationSocialMedia(document: Document) {
        var table = Table(3)
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
                                styles1 = style.template_1_Calibri_11f(),
                                top = 5f,
                                bottom = 5f
                            )
                            addCell(cell)
                        }

                        if (!it.linkedIn.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageLinkedin,
                                val1 = it.linkedIn,
                                styles1 = style.template_1_Calibri_11f(),
                                top = 5f,
                                bottom = 5f
                            )
                            addCell(cell)
                        }

                        if (!it.webSite.isNullOrEmpty()) {
                            var cell = addValueSameCell(
                                imageWebSite,
                                val1 = it.webSite,
                                styles1 = style.template_1_Calibri_11f(),
                                top = 5f,
                                bottom = 5f
                            )
                            addCell(cell)
                        }
                    }
                }

                table.setWidth(UnitValue.createPercentValue(100f))

                document.add(table)
                document.add(line())
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

        lifecycleOwner.observe(viewModel.languageMLD) { language ->
            if (!language.isNullOrEmpty()) {
                language?.forEach {
                    with(table) {
                        if (!it.languageName.isNullOrEmpty()) {

                            var cell = addValueSameCell(
                                val1 = it.languageName!!,
                                styles1 = style.template_1_Calibri_10f(),
                                val2 = requireActivity.resources.getString(R.string.dots),
                                val3 = it.level,
                                styles3 = style.template_1_Calibri_10f()
                            )

                            addCell(cell)
                        }
                    }
                }
                table.setWidth(UnitValue.createPercentValue(100f))

                document.add(titleLanguage())
                document.add(table)
                document.add(line())
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
        var table = Table(3)

        lifecycleOwner.observe(viewModel.educationMLD) { education ->
            if (!education.isNullOrEmpty()) {
                education?.forEach {
                    with(table) {
                        if (!it.schoolName.isNullOrEmpty()) {

                            addCell(
                                addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.template_1_Calibri_11f(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    val3 = it.finishDate,
                                    styles3 = style.template_1_Calibri_11f()
                                )
                            )

                            addCell(addValueCell(it.schoolName, style.template_1_CalibriBold_11f()))

                            if (!it.gano.isNullOrEmpty()) {
                                addCell(addValueCell(it.gano, style.template_1_Calibri_10f()))
                            } else {
                                addCell(emptyCell())
                            }

                            addCell(emptyCell())

                            addCell(
                                addValueCell(
                                    it.departmentName,
                                    style.template_1_CalibriItalic_10f()
                                )?.setPaddingTop(-10f)
                            )
                            addCell(emptyCell())

                        }
                    }
                }
                table.setWidth(UnitValue.createPercentValue(100f))

                document.add(titleEducation())
                document.add(table)
                document.add(line())
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

                document.add(titleAbilities())
                document.add(paragraph)
                document.add(line())
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
        var table = Table(2)

        lifecycleOwner.observe(viewModel.experienceMLD) { experience ->
            if (!experience.isNullOrEmpty()) {
                experience?.forEach {
                    with(table) {
                        if (!it.positionName.isNullOrEmpty()) {

                            var cellDate = Cell()
                            if (!it.finishDate.isNullOrEmpty()) {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.template_1_Calibri_11f(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    val3 = it.finishDate,
                                    styles3 = style.template_1_Calibri_11f()
                                )
                            } else {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    styles1 = style.template_1_Calibri_11f(),
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    val3 = requireActivity.resources.getString(R.string.continueEx),
                                    styles3 = style.template_1_Calibri_11f(),
                                )
                            }

                            addCell(cellDate)

                            addCell(
                                addValueCell(
                                    it.positionName,
                                    style.template_1_CalibriBold_11f()
                                )
                            )
                            addCell(emptyCell())

                            addCell(
                                addValueCell(
                                    it.companyName,
                                    style.template_1_Calibri_11f()
                                )?.setPaddingTop(-10f)
                            )
                            addCell(emptyCell())

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cellInfo = Cell()

                            if (!it.infoAboutJob.isNullOrEmpty()) {
                                cellInfo = addValueSameCell(
                                    point,
                                    val1 = it.infoAboutJob,
                                    styles1 = style.template_1_Calibri_10f(),
                                    top = -7f
                                )
                            } else {
                                addCell(emptyCell())
                            }
                            addCell(cellInfo)
                        }
                    }
                }
                table.setWidth(UnitValue.createPercentValue(100f))

                document.add(titleExperience())
                document.add(table)
                document.add(line())
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
                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cell = addValueSameCell(
                                image = point,
                                val1 = it.name!!,
                                styles1 = style.template_1_Calibri_11f(),
                                val2 = requireActivity.resources.getString(R.string.space),
                                val3 = it.surname,
                                styles3 = style.template_1_Calibri_11f()
                            )
                            addCell(cell)

                            if (!it.email.isNullOrEmpty()) {
                                addCell(
                                    addValueCell(
                                        it.positionName,
                                        style.template_1_Calibri_11f()
                                    )
                                )
                            } else {
                                addCell(emptyCell())
                            }

                            if (!it.email.isNullOrEmpty()) {
                                addCell(addValueCell(it.email, style.template_1_Calibri_11f()))
                            } else {
                                addCell(emptyCell())
                            }

                            if (!it.email.isNullOrEmpty()) {
                                addCell(addValueCell(it.phone, style.template_1_Calibri_11f()))
                            } else {
                                addCell(emptyCell())
                            }
                        }
                    }

                    setWidth(UnitValue.createPercentValue(100f))

                    document.add(titleReferences())
                    document.add(table)
                    document.add(line())
                }
            }
        }
    }

}
