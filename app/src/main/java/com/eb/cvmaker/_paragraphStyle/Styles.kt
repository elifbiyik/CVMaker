package com.eb.cvmaker._paragraphStyle

import com.itextpdf.io.font.FontConstants
import com.itextpdf.io.font.FontNames
import com.itextpdf.io.font.FontProgramFactory
import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.layout.Style
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.text.Font

class Styles {

   val pdfFontMedium = PdfFontFactory.createFont("assets/Roboto_Mono/static/RobotoMono-Medium.ttf", PdfEncodings.IDENTITY_H, true)
   val pdfFontLight = PdfFontFactory.createFont("assets/Roboto_Mono/static/RobotoMono-Light.ttf", PdfEncodings.IDENTITY_H, true)

    fun styleForNameAndSurname(): Style? {
        return Style()
            .setFontColor(DeviceRgb(160, 160, 160)) // Gri renk
            .setFont(pdfFontMedium)
            .setFontSize(40f) // punto büyüklüğü
            .setBold() // Kalın stil
    }

    fun styleForTitle(): Style? {
        return Style()
            .setFontColor(DeviceRgb(160, 160, 160)) // Gri
            .setFontSize(20f) // punto büyüklüğü
            .setBold() // Kalın stil
    }

    fun styleForJobTitle(): Style? {
        return Style()
            .setFontColor(DeviceRgb(96, 96, 96)) // Koyu Gri
            .setFont(pdfFontLight)
            .setFontSize(20f) // punto büyüklüğü
            .setTextAlignment(TextAlignment.JUSTIFIED) // hem sol hem de sağ kenar boşluklarına hizalayarak metni eşit şekilde
    }

    fun styleForText(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0)) // Siyah
            .setFontSize(12f) // punto büyüklüğü
    }

    fun styleBold15f() : Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0)) // Siyah
            .setFontSize(15f) // punto büyüklüğü
            .setBold() // Kalın stil
    }

    fun styleBold12f() : Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0)) // Siyah
            .setFontSize(12f) // punto büyüklüğü
            .setBold() // Kalın stil
    }

    fun styleItalic12f() : Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0)) // Siyah
            .setFontSize(12f) // punto büyüklüğü
            .setItalic()
    }

    fun styleItalic10f() : Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0)) // Siyah
            .setFontSize(10f) // punto büyüklüğü
            .setItalic()
    }
}