package com.eb.cvmaker

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.eb.cvmaker._paragraphStyle.Styles
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.itextpdf.layout.Style
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue

fun Fragment.replace(fragment: Fragment) {

    requireActivity().supportFragmentManager.beginTransaction()
        .replace(R.id.constraint, fragment)
        .addToBackStack(null)
        .commit()

}

fun <T : Any?, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer<T> { t -> body(t) })
}

fun exception(e: Exception, repository: String) {
    Log.d("ERROR FOR ROOM", " Repository : $repository - Error : $e.message.toString()")
}

fun message(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.ImageLoad(url: Uri) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .into(this)
}

fun title(infoParagraph: Paragraph, title: String, styles: Style?): Paragraph {
    with(infoParagraph) {
        add(Text(title).addStyle(styles))
        add("\n")
    }
    return infoParagraph
}

fun createLineParagraph(): Paragraph {
    val paragraph = Paragraph()
    paragraph.setBorderBottom(SolidBorder(0.5f))
    return paragraph
}

fun createLineCell(width: Float): Cell {
    val lineCell = Cell()
        .setBorderBottom(SolidBorder(0.5f))
        .setWidth(width)
        .setBorder(Border.NO_BORDER)

    return lineCell;
}


fun emptyCell(top: Float? = 0f): Cell? {
    return Cell().add(Paragraph(" ")).setBorder(Border.NO_BORDER).setPaddingTop(top ?: 0f)
}

fun addText(
    value: String?,
    styles: Style? = Styles().styleText(),
    bottom: Float = 0f,
    top: Float = 0f
): Paragraph? {
    val paragraph =
        Paragraph(value ?: "").addStyle(styles).setMarginBottom(bottom).setMarginTop(top)
    return paragraph
}

fun addValueCell(
    value: String? = null,
    styles: Style? = Styles().styleText(),
    top: Float? = 0f,
    bottom: Float? = 0f,
    left: Float? = 0f
): Cell? {
    return Cell().add(
        Paragraph(value).addStyle(styles).setPaddingBottom(bottom ?: 0f).setPaddingTop(top ?: 0f)
            .setPaddingLeft(left ?: 0f)
    ).setBorder(Border.NO_BORDER)
}

fun addValueSameCell(
    image: Image? = null,
    values: List<Pair<String?, Style?>> = emptyList(),
    paddingBottom: Float = 0f,
    paddingTop: Float = 0f,
    paddingLeft: Float = 0f,
    marginBottomParagraph: Float = 0f,
    alignment: TextAlignment = TextAlignment.LEFT
): Cell {
    val cell = Cell()
    val paragraph = Paragraph()

    image?.let {
        paragraph.add(it)
        paragraph.add("  ")
    }

    values.forEach { (value, style) ->
        paragraph.add(value?.asParagraph()?.addStyle(style))
    }

    cell.add(paragraph.setMarginBottom(marginBottomParagraph))
        .setPaddingTop(paddingTop)
        .setPaddingBottom(paddingBottom)
        .setPaddingLeft(paddingLeft)
        .setTextAlignment(alignment)
        .setBorder(Border.NO_BORDER)

    return cell
}

// String'i alır ve onu bir Paragraf nesnesine dönüştürür
fun String.asParagraph(): Paragraph {
    return Paragraph(this)
}

fun String?.formatPhone(): String? {
    return this.takeIf { it?.length == 13 || it?.length == 17 }?.let {
        try {
            val phoneUtil = PhoneNumberUtil.getInstance()
            val phoneNumber = phoneUtil.parse(it, "TR")
            "+90 " + phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
                .removePrefix("0")
        } catch (e: Exception) {
            it // Formatlamada bir hata olursa, orijinal değeri döndür
        }
    }
}

