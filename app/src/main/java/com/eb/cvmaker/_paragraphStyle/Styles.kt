package com.eb.cvmaker._paragraphStyle

import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.layout.Style

class Styles {

    val pdfFontCalibri = PdfFontFactory.createFont("assets/calibri-font-family/calibri-regular.ttf", PdfEncodings.IDENTITY_H, true)
    val pdfFontCalibriBold = PdfFontFactory.createFont("assets/calibri-font-family/calibri-bold.ttf", PdfEncodings.IDENTITY_H, true)
    val pdfFontCalibriItalic = PdfFontFactory.createFont("assets/calibri-font-family/calibri-italic.ttf", PdfEncodings.IDENTITY_H, true)

    val pdfFontArial = PdfFontFactory.createFont("assets/arial-font/arial.ttf", PdfEncodings.IDENTITY_H, true)

    val pdfFontHeeboLight = PdfFontFactory.createFont("assets/Heebo/static/Heebo-Light.ttf", PdfEncodings.IDENTITY_H, true)
    val pdfFontHeeboThin = PdfFontFactory.createFont("assets/Heebo/static/Heebo-Thin.ttf", PdfEncodings.IDENTITY_H, true)
    val pdfFontHeeboMedium = PdfFontFactory.createFont("assets/Heebo/static/Heebo-Medium.ttf", PdfEncodings.IDENTITY_H, true)


    fun styleText(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(12f)
    }

    fun template_1_Name(): Style? {
        return Style()
            .setFontColor(DeviceRgb(127, 127, 127))
            .setFontSize(30f)
            .setFont(pdfFontCalibri)
    }

    fun template_1_Surname(): Style? {
        return Style()
            .setFontColor(DeviceRgb(127, 127, 127))
            .setFontSize(30f)
            .setFont(pdfFontCalibriBold)
    }

    fun template_1_Job(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 17, 50))
            .setFontSize(12f)
            .setFont(pdfFontCalibri)
    }

    fun template_1_Title(): Style? {
        return Style()
            .setFontColor(DeviceRgb(127, 127, 127))
            .setFontSize(15f)
            .setFont(pdfFontCalibriBold)
    }

    fun template_1_Calibri_11f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(11f)
            .setFont(pdfFontCalibri)
    }

    fun template_1_Calibri_10f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(10f)
            .setFont(pdfFontCalibri)
    }

    fun template_1_CalibriItalic_10f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(10f)
            .setFont(pdfFontCalibriItalic)
    }

    fun template_1_Calibri_Bold_9f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(9f)
            .setFont(pdfFontCalibri)
            .setBold()
    }

    fun template_1_CalibriBold_11f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(11f)
            .setFont(pdfFontCalibriBold)
    }

    fun template_1_Calibri_9f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(9f)
            .setFont(pdfFontCalibri)
    }

    fun template_2_Name_Surname(): Style? {
        return Style()
            .setFontColor(DeviceRgb(255, 255, 255))
            .setFontSize(36f)
            .setFont(pdfFontHeeboLight)
    }

    fun template_2_Job(): Style? {
        return Style()
            .setFontColor(DeviceRgb(255, 255, 255))
            .setFontSize(18f)
            .setFont(pdfFontHeeboThin)
    }

    fun template_2_Heebo_Medium_16f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(16f)
            .setFont(pdfFontHeeboMedium)
    }

    fun template_2_Heebo_Medium_14f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(14f)
            .setFont(pdfFontHeeboMedium)
    }

    fun template_2_Arial_11f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(11f)
            .setFont(pdfFontArial)
    }

    fun template_2_Arial_Bold_11f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(11f)
            .setFont(pdfFontArial)
            .setBold()
    }

    fun template_2_Arial_Gray_11f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(126, 126, 126))
            .setFontSize(11f)
            .setFont(pdfFontArial)
    }

    fun template_2_Arial_905f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(9.5f)
            .setFont(pdfFontArial)
    }

    fun template_2_Arial_10f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(10f)
            .setFont(pdfFontArial)
    }

    fun template_2_Arial_105f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(10.5f)
            .setFont(pdfFontArial)
    }

    fun template_2_Arial_Bold_105f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(10.5f)
            .setFont(pdfFontArial)
            .setBold()
    }

    fun template_2_Arial_Gray_105f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(126, 126, 126))
            .setFontSize(10.5f)
            .setFont(pdfFontArial)
    }

    fun template_2_Arial_Gray_10f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(126, 126, 126))
            .setFontSize(10f)
            .setFont(pdfFontArial)
    }


    fun template_3_Arial_28f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(28f)
            .setFont(pdfFontArial)
            .setBold()
    }

    fun template_3_Arial_14f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(63, 63, 63))
            .setFontSize(14f)
            .setFont(pdfFontArial)
            .setItalic()
            .setBold()
    }


    fun template_3_Arial_16f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(16f)
            .setFont(pdfFontArial)
            .setBold()
    }

    fun template_3_Arial_Bold_Gray_12f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(63, 63, 63))
            .setFontSize(12f)
            .setFont(pdfFontArial)
            .setBold()
    }

    fun template_3_Arial_Gray_12f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(63, 63, 63))
            .setFontSize(12f)
            .setFont(pdfFontArial)
    }

    fun template_3_Arial_12f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(12f)
            .setFont(pdfFontArial)
    }

    fun template_3_Arial_Gray_10f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(63, 63, 63))
            .setFontSize(10f)
            .setFont(pdfFontArial)
    }

    fun template_3_Arial_10f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(10f)
            .setFont(pdfFontArial)
    }

    fun template_3_Arial_11f(): Style? {
        return Style()
            .setFontColor(DeviceRgb(0, 0, 0))
            .setFontSize(11f)
            .setFont(pdfFontArial)
    }




}
