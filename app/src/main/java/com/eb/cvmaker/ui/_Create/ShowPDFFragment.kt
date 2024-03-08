package com.eb.cvmaker.ui._Create

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eb.cvmaker.databinding.FragmentShowPdfBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.ui.VM
import com.github.barteksc.pdfviewer.PDFView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileInputStream

@AndroidEntryPoint
class ShowPDFFragment : Fragment() {


    private lateinit var binding: FragmentShowPdfBinding
    private val viewModel: VM by viewModels()

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
/*
        Log.d("ShowPDF", "Ok")
  //      val file: File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "GFG.pdf")
        val file = File(Environment.getExternalStorageDirectory().absolutePath + "/" + "GFG.pdf")


        if (file.exists()) {
         //   val inputStream = FileInputStream(file)
      //      pdfView.fromStream(inputStream).load()
            binding.PdfViewer.fromFile(file).load()

        } else {
            message(requireContext(), "Hata")
        }


*/

/*

        val file = File(
            context?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
          //  "GFG.pdf"
        "example_cv.pdf"
        )



        val input = FileInputStream(file)
        pdfView.fromStream(input).load()

        val uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "GFG.pdf")
        val inputStream = context?.contentResolver?.openInputStream(uri)
        pdfView.fromStream(inputStream).load()


*/

        message(requireContext(), "ShowPDF")


        pdfFilePath?.let {
            val file = File(it)
            if (file.exists()) {
                loadPdfFromPath(it)
                message(requireContext(), "PDF var")

            } else {
                message(requireContext(), "PDF yok")
            }
        }
        message(requireContext(), "PDF null")

        return binding.root
    }

    fun loadPdfFromPath(filePath: String) {
        binding.PdfViewer.fromFile(File(filePath))
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .onPageChange { page, pageCount -> /* handle page change */ }
            .load()
    }

 /*   fun generatePDF() {
        var pdfDocument = PdfDocument()

        //  grafiksel şekiller ve metin çizmek için kullanılan bir araçtır.
        var paint: Paint = Paint()
        var title: Paint = Paint()

// 1 sayfa olduğunu belirterek bir PDF sayfasının bilgilerini tanımlar
        var myPageInfo: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()


        var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

//  grafiksel içerik oluşturmak için kullanılan bir çizim tahtasıdır.
        var canvas: Canvas = myPage.canvas


        canvas.drawBitmap(scaledbmp, 56F, 40F, paint)

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))

        title.textSize = 15F
        title.setColor(ContextCompat.getColor(requireContext(), R.color.red))

        canvas.drawText("A portal for IT professionals.", 209F, 100F, title)
        canvas.drawText("Geeks for Geeks", 209F, 80F, title)
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        title.setColor(ContextCompat.getColor(requireContext(), R.color.red))
        title.textSize = 15F

        title.textAlign = Paint.Align.CENTER
        canvas.drawText("This is sample document which we have created.", 396F, 560F, title)

        pdfDocument.finishPage(myPage)
        // Şuan pdf Oluştu!


    //    val file: File = File(Environment.getExternalStorageDirectory(), "GFG.pdf")
   //     val file: File = File(requireActivity().getExternalFilesDir(null), "GFG.pdf")

        // Pdf'in kayıt olacağı path (file/download)
        val file: File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "GFG.pdf")

        try {
//pdfDocument: Bellekteki PDF belgenizin içeriğini tutar.
// FileOutputStream(file): PDF'nizin içeriğini file tarafından belirtilen konuma yazmak için kullanılan bir çıktı akışıdır.
            pdfDocument.writeTo(FileOutputStream(file)) // PDF belgesini bellekte oluşturup dosya sistemine yazar.

            message(requireContext(), "PDF file generated..")
        } catch (e: Exception) {

            var mes = e.printStackTrace()

            message(requireContext(), "Fail to generate PDF file..")
            Log.d("!!!! HATA", mes.toString())
        }

        pdfDocument.close()

    }

    fun checkPermissions(): Boolean {

        var writeStoragePermission = ContextCompat.checkSelfPermission(
            requireActivity().applicationContext,
            WRITE_EXTERNAL_STORAGE
        )

        var readStoragePermission = ContextCompat.checkSelfPermission(
            requireActivity().applicationContext,
            READ_EXTERNAL_STORAGE
        )

        return writeStoragePermission == PackageManager.PERMISSION_GRANTED
                && readStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission() {

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE), PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (requestCode == PERMISSION_CODE) {

            if (grantResults.size > 0) {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]
                    == PackageManager.PERMISSION_GRANTED
                ) {

                    message(requireContext(), "Permission Granted..")

                } else {
                    message(requireContext(), "Permission Denied..")
       //             finish()
                }
            }
        }
    }*/
}