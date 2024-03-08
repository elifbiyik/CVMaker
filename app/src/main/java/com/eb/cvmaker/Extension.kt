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
import com.itextpdf.layout.Style
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.property.TextAlignment
import java.lang.Exception
import java.util.Locale

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

fun title(infoParagraph: Paragraph, title: String): Paragraph {
    with(infoParagraph) {
        add(Text(title).addStyle(Styles().styleForTitle()))
        add("\n")
    }
    return infoParagraph
}

fun line(): Paragraph {
    val paragraph = Paragraph()
    paragraph.setBorderBottom(SolidBorder(0.5f))
    return paragraph
}

fun emptyCell(): Cell? {
    return Cell().add(Paragraph("")).setBorder(Border.NO_BORDER)
}

fun addText (value: String?, styles: Style? = Styles().styleForText()): Text? {
    var x = Text(value ?: "").addStyle(styles)
    Log.d("ELİFFFFFX", x.text .toString())
    return x
}

fun addValueCell(value: String? = null, styles: Style? = Styles().styleForText()): Cell? {
    return Cell().add(Paragraph(value).addStyle(styles))
        .setBorder(Border.NO_BORDER)
}

fun addValueSameCell(
    image: Image? = null,
    val1: String? = null,
    styles1: Style? = Styles().styleForText(),
    val2: String? = null,
    styles2: Style? = Styles().styleForText(),
    val3: String? = null,
    styles3: Style? = Styles().styleForText(),
    val4: String? = null,
    styles4: Style? = Styles().styleForText()
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


    cell.add(paragraph).setBorder(Border.NO_BORDER)

    return cell
}
