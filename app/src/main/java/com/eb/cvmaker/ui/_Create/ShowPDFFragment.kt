package com.eb.cvmaker.ui._Create

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentShowPdfBinding
import com.eb.cvmaker.message
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException

@AndroidEntryPoint
class ShowPDFFragment : Fragment() {

    private lateinit var binding: FragmentShowPdfBinding
    var FILENAME = "CV.pdf"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShowPdfBinding.inflate(inflater, container, false)

        var pdfFilePath = getPdfPathSharedPreferances()

        binding.save.setOnClickListener {
            pdfFilePath?.let { it1 -> savePDF(it1) }
        }

        binding.share.setOnClickListener {
            pdfFilePath?.let { it1 -> sharePDF(it1) }
        }

        pdfFilePath?.let {
            val file = File(it)
            if (file.exists()) {
                loadPdfFromPath(it)
            } else {
                message(requireContext(), requireActivity().resources.getString(R.string.messageForCouldntCreate))
            }
        }

        return binding.root
    }

    fun getPdfPathSharedPreferances(): String? {
        var sharedPreferences =
            context?.getSharedPreferences("PDFFilePath", AppCompatActivity.MODE_PRIVATE)
        var pdfPath = sharedPreferences?.getString("pdfFilePath", "")
        return pdfPath
    }

    fun loadPdfFromPath(filePath: String) {
        binding.PdfViewer.fromFile(File(filePath))
            .defaultPage(0)
            .enableSwipe(true) // kaydırmayı kullanarak değişen sayfaları engellemeye izin verir
            .swipeHorizontal(false)
            .enableAntialiasing(true) // düşük çözünürlüklü ekranlarda görüntü oluşturmayı biraz iyileştirin
            .load() // PDF dosyasını yükler ve görüntüler.
    }

    fun savePDF(filePath: String) {
        val sourceFile = File(filePath)

        // Kaydetme yapılacak dizin belirlenir.
        val downloadsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        // Kaydedilecek dosyanın adı belirlenir.
        val destinationFile = File(downloadsDirectory, FILENAME)

        try {
            // PDF dosyasını kopyala.
            // overwrite =  varsa hedef dosyanın üzerine yazılmasına izin verir
            sourceFile.copyTo(destinationFile, overwrite = true)
            // Başarıyla kaydedildiğine dair bir mesaj gösterilebilir.
            message(requireContext(), requireActivity().resources.getString(R.string.messageForDownload))
        } catch (e: IOException) {
            // Eğer bir hata olursa, kullanıcıya uygun bir hata mesajı gösterilebilir.
            e.printStackTrace()
            message(requireContext(), requireActivity().resources.getString(R.string.messageForFailed))
        }
    }

    fun sharePDF(filePath: String) {
        val pdfFile = File(filePath)
        val uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            pdfFile
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "application/pdf"
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        startActivity(Intent.createChooser(shareIntent,  requireActivity().resources.getString(R.string.sharePDF)))
    }

}