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
import com.itextpdf.layout.layout.LayoutPosition
import com.itextpdf.layout.property.Property
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import java.lang.System.setProperty

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

fun line(): Paragraph {
    val paragraph = Paragraph()
    paragraph.setBorderBottom(SolidBorder(0.5f))
    return paragraph
}

fun createLine(width: Float): Cell {
    val lineCell = Cell()
        .setBorderBottom(SolidBorder(0.5f))
        .setWidth(width) // Set the line width to match the table column
        .setBorder(Border.NO_BORDER); // Remove all borders

    return lineCell;
}


fun emptyCell(top : Float ?= 0f): Cell? {
    return Cell().add(Paragraph(" ")).setBorder(Border.NO_BORDER).setPaddingTop(top ?: 0f)
}

fun addText(value: String?, styles: Style? = Styles().styleText(), bottom: Float = 0f, top:Float = 0f): Paragraph? {
    val paragraph = Paragraph(value ?: "").addStyle(styles).setMarginBottom(bottom).setMarginTop(top)
    return paragraph
}

fun addValueCell(value: String? = null, styles: Style? = Styles().styleText(), top: Float ?= 0f, bottom: Float ?= 0f): Cell? {
    return Cell().add(Paragraph(value).addStyle(styles).setPaddingBottom(bottom ?: 0f).setPaddingTop((top ?: 0f))).setBorder(Border.NO_BORDER)
}

fun addValueSameCell(
    image: Image? = null,
    val1: String? = null,
    styles1: Style? = Styles().styleText(),
    val2: String? = null,
    styles2: Style? = Styles().styleText(),
    val3: String? = null,
    styles3: Style? = Styles().styleText(),
    val4: String? = null,
    styles4: Style? = Styles().styleText(),
    val5: String? = null,
    styles5: Style? = Styles().styleText(),
    bottom : Float?= 0f,
    top : Float?= 0f
): Cell {
    val cell = Cell()
    val paragraph = Paragraph()

    image?.let {
        paragraph.add(image)
        paragraph.add("  ")
    }

    if (!val1.isNullOrEmpty()) {
        paragraph.add(Paragraph(val1).addStyle(styles1))
    }

    if (!val2.isNullOrEmpty()) {
        paragraph.add(Paragraph(val2).addStyle(styles2))
    }

    if (!val3.isNullOrEmpty()) {
        paragraph.add(Paragraph(val3).addStyle(styles3))
    }

    if (!val4.isNullOrEmpty()) {
        paragraph.add(Paragraph(val4).addStyle(styles4))
    }

    if (!val5.isNullOrEmpty()) {
        paragraph.add(Paragraph(val5).addStyle(styles5))
    }

    cell.add(paragraph).setBorder(Border.NO_BORDER).setPaddingTop(top ?: 0f).setPaddingBottom(bottom ?: 0f)

    return cell
}

fun cellStyleSameCell(
    val1: String? = null,
    styles1: Style? = Styles().styleText(),
    val2: String? = null,
    styles2: Style? = Styles().styleText(),
    val3: String? = null,
    styles3: Style? = Styles().styleText(),
    alignment: TextAlignment? ,
): Cell? {

    val paragraph = Paragraph()
    if (!val1.isNullOrEmpty()) {
        paragraph.add(
            Paragraph(val1).addStyle(styles1))
    }
    if (!val2.isNullOrEmpty()) {
        paragraph.add(Paragraph(val2).addStyle(styles2))
    }

    if (!val3.isNullOrEmpty()) {
        paragraph.add(Paragraph(val3).addStyle(styles3))
    }

    // Metin boyutundan kaynaklı çok fazla boşluk bırakıyordu. Üst ile mesafesini azalttık.
    paragraph.setMarginBottom(-40f)

    val cell = Cell().add(paragraph)
    cell.setPadding(0f)
    cell.setTextAlignment(alignment)
    cell.setBorder(Border.NO_BORDER)
    return cell
}

/* chatGPT'ye ElementPropertyContainer.java classındaki
 public T setFixedPosition(float left, float bottom, float width) {
        setFixedPosition(left, bottom, UnitValue.createPointValue(width));
        return (T) (Object) this;
    }

Fonksiyonu extension yaptırdım.
 */

fun <T : Table> T.setFixedPositionTable(left: Float, top: Float, width: Float): T {
    setFixedPosition(left, top, UnitValue.createPointValue(width))
    return this
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





