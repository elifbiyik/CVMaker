package com.eb.cvmaker.ui._Create

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eb.cvmaker.databinding.FragmentShowPdfBinding
import com.eb.cvmaker.message
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException


@AndroidEntryPoint
class ShowPDFFragment : Fragment() {


    private lateinit var binding: FragmentShowPdfBinding
    val viewModel: ShowPDFVM by viewModels()

    var FILENAME = "CV.pdf"
    //  var pdfFilePath: String? = null

    /* var pageHeight = 1120
     var pageWidth = 792

     // creating a bitmap variable
     // for storing our images
     lateinit var bmp: Bitmap
     lateinit var scaledbmp: Bitmap
     var PERMISSION_CODE = 101*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShowPdfBinding.inflate(inflater, container, false)

        var pdfFilePath = arguments?.getString("path")


        binding.save.setOnClickListener {

            pdfFilePath?.let { it1 -> savePDF(it1) }

            // -----------------
            /*    val downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager



                val request = DownloadManager.Request(uri)
                    .setTitle(FILENAME)
                    .setDescription("Downloading")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, FILENAME)
                }

                downloadManager.enqueue(request)
    */

            message(requireContext(), "Download : ${pdfFilePath}")

        }


        binding.share.setOnClickListener {

            pdfFilePath?.let { it1 -> sharePDF(it1) }
        }



        pdfFilePath?.let {
            val file = File(it)
            if (file.exists()) {
                loadPdfFromPath(it)

            } else {
                message(requireContext(), "PDF yok")
            }
        }

        return binding.root
    }

    fun loadPdfFromPath(filePath: String) {
        binding.PdfViewer.fromFile(File(filePath))
            .defaultPage(0)
            .enableSwipe(true) // kaydırmayı kullanarak değişen sayfaları engellemeye izin verir
            .swipeHorizontal(false)
    //        .onPageChange { page, pageCount -> /* handle page change */ }
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
           message(requireContext(),"PDF saved successfully to Downloads folder!")
        } catch (e: IOException) {
            // Eğer bir hata olursa, kullanıcıya uygun bir hata mesajı gösterilebilir.
            e.printStackTrace()
            message(requireContext(), "Failed to save PDF!")
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

        startActivity(Intent.createChooser(shareIntent, "Share PDF"))
    }

}