package com.eb.cvmaker.ui._Create

import android.content.Context
import android.util.Log
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
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.util.Locale
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
    private val viewModel: ChooseTemplateViewModel,
    var requireActivity: FragmentActivity,
    var context: Context,
) {

    var style = Styles()
    var image = Images()

    // lifecycleOwner fragment'ın yaşam döngüsü olaylarını gözlemlemek için kullanılır.
    val lifecycleOwner = requireActivity as LifecycleOwner // Assuming you're in a fragment

    // Bunu ınject yapabiliz.


    fun generateCV(document: Document) {

        informationCommunication(document)
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

    // Locale... -> Türkçe karakterlerin yazmasını sağlıyor
    fun personalInformationNameSurname(document: Document) {
        val infoParagraph = Paragraph()

        lifecycleOwner.observe(viewModel.communicationMLD) {

            if (!it.isNullOrEmpty()) {
                it?.forEach {
                    with(infoParagraph) {

                        var nameUpper = it.name?.toUpperCase()
                        var surnameUpper = it.surname?.toUpperCase()

                        Log.d("Eliffffff", nameUpper.toString())

                        add(addText(nameUpper, style.styleForNameAndSurname())).setTextAlignment(TextAlignment.CENTER)
                        add("  ")
                        add(addText(surnameUpper, style.styleForNameAndSurname())).setTextAlignment(TextAlignment.CENTER)
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
                            add(addText(jobUpper, style.styleForJobTitle())).setTextAlignment(
                                TextAlignment.CENTER
                            )
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

                            add(Text(it.aboutMe)).addStyle(style.styleForText())
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
                            addCell(addValueSameCell(image = imagePhone, val1 = it.phone))
                        }

                        // Mail
                        if (!it.email.isNullOrEmpty()) {
                            var imageMail = image.image(context, R.drawable.baseline_email_24)
                            addCell(addValueSameCell(image = imageMail, val1 = it.email))
                        }

                        if (!it.address.isNullOrEmpty()) {
                            // Location
                            var imageLocation =
                                image.image(context, R.drawable.baseline_location_on_24)
                            addCell(addValueSameCell(image = imageLocation, val1 = it.address))
                        }

                        setBorder(Border.NO_BORDER)
                        setWidth(UnitValue.createPercentValue(100f))
                    }
                }
                document.add(table)
                document.add(line())
            }
        }
    }


    // Language
    fun titleLanguage(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.Languages)
        return title(infoParagraph, title)
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
                                val2 = requireActivity.resources.getString(R.string.dots),
                                val3 = it.level
                            )

                            addCell(cell).setBorder(Border.NO_BORDER)
                        }
                    }
                }
                table.setBorder(Border.NO_BORDER)
                table.setWidth(UnitValue.createPercentValue(100f)) // Tablonun genişliği ayarlanıyor telefona oranlayıp

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
        return title(infoParagraph, title)
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
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    val3 = it.finishDate
                                )
                            )

                            addCell(addValueCell(it.schoolName, style.styleBold15f()))

                            if (!it.gano.isNullOrEmpty()) {
                                addCell(addValueCell(it.gano))
                            } else {
                                addCell(emptyCell())
                            }

                            addCell(emptyCell())

                            addCell(addValueCell(it.departmentName, style.styleItalic12f()))
                            addCell(emptyCell())

                        }
                    }
                }
                table.setBorder(Border.NO_BORDER)
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
        return title(infoParagraph, title)
    }

    fun informationAbilities(document: Document) {

        var table = Table(6)
        lifecycleOwner.observe(viewModel.abilitiesMLD) { abilities ->
            if (!abilities.isNullOrEmpty()) {
                abilities?.forEach {
                    var point = image.image(context, R.drawable.baseline_point_24)
                    if (!it.abilitiesName.isNullOrEmpty()) {
                        table.addCell(addValueSameCell(point, val1 = it.abilitiesName!!))
                    }
                }
                table.setBorder(Border.NO_BORDER)
                table.setWidth(UnitValue.createPercentValue(100f))

                document.add(titleAbilities())
                document.add(table)
                document.add(line())
            }
        }
    }


    // Experience
    fun titleExperience(): Paragraph {
        val infoParagraph = Paragraph()
        var title = requireActivity.resources.getString(R.string.Experience)
        return title(infoParagraph, title)
    }

    fun informationExperience(document: Document) {
        var table = Table(3)

        lifecycleOwner.observe(viewModel.experienceMLD) { experience ->
            if (!experience.isNullOrEmpty()) {
                experience?.forEach {
                    with(table) {
                        if (!it.positionName.isNullOrEmpty()) {

                            var cellDate = Cell()
                            if (!it.finishDate.isNullOrEmpty()) {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    val3 = it.finishDate
                                )
                            } else {
                                cellDate = addValueSameCell(
                                    val1 = it.startDate,
                                    val2 = requireActivity.resources.getString(R.string.line),
                                    val3 = requireActivity.resources.getString(R.string.continueEx)
                                )
                            }

                            addCell(cellDate).setBorder(Border.NO_BORDER)

                            addCell(addValueCell(it.positionName, style.styleBold15f()))
                            addCell(addValueCell(it.companyName, style.styleForText()))

                            addCell(emptyCell())

                            var point = image.image(context, R.drawable.baseline_point_24)
                            var cellInfo = Cell()

                            if (!it.infoAboutJob.isNullOrEmpty()) {
                                cellInfo = addValueSameCell(
                                    point,
                                    val1 = it.infoAboutJob,
                                    styles1 = style.styleItalic10f()
                                )
                            } else {
                                addCell(emptyCell())
                            }


                            addCell(cellInfo).setBorder(Border.NO_BORDER)

                            addCell(emptyCell())

                        }
                    }
                }
                table.setBorder(Border.NO_BORDER)
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
        return title(infoParagraph, title)
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
                                styles1 = style.styleBold12f(),
                                val2 = it.surname,
                                styles2 = style.styleBold12f()
                            )
                            addCell(cell).setBorder(Border.NO_BORDER)

                            addCell(addValueCell(it.positionName))

                            if (!it.email.isNullOrEmpty()) {
                                addCell(addValueCell(it.email))
                            }
                            addCell(addValueCell(it.phone))
                        }
                    }

                    setBorder(Border.NO_BORDER)
                    setWidth(UnitValue.createPercentValue(100f))

                    document.add(titleReferences())
                    document.add(table)
                    document.add(line())
                }
            }
        }
    }


}
