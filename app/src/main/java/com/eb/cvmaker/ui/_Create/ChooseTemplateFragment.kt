package com.eb.cvmaker.ui._Create


import android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eb.cvmaker.Adapter.ChooseTemplateAdapter
import com.eb.cvmaker.databinding.FragmentChooseTemplateBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.replace
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class ChooseTemplateFragment : Fragment() {

    private lateinit var binding: FragmentChooseTemplateBinding
    private val viewModel: ChooseTemplateVM by viewModels()
    private lateinit var adapter: ChooseTemplateAdapter

    var PERMISSION_CODE = 101
    var FILE_NAME = "CV.pdf"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseTemplateBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            var list = viewModel.getTemplate()

            adapter = ChooseTemplateAdapter(list) { uri, index ->
                createCV(index)
            }
            binding.recView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recView.adapter = adapter
        }

        return binding.root
    }

    // uygulamanın izni varsa oluşturur yoksa izin ister.
    fun createCV(index: Int) {
        if (checkPermissions()) {
            generatePDF(index)
        } else {
            requestPermission()
        }
    }

    fun generatePDF(index: Int) {
        val directory: File
        val filePath: File

        // Dosyanın oluşturulacağı dizi belirlenir. (Download klasörüne kaydeder.)
        // Android 10 ve üzei için depolama yönetimi değişti. getExternalStoragePublicDirectory yerine
        // getExternalFilesDir() veya getExternalStorageDirectory() kullanılır.

        // Android 10 ve sonraki sürümler için
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            directory = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!
        } else {
            // Android 9 ve önceki sürümler için
            directory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        }

        filePath = File(directory, FILE_NAME)

        // PDF dosyasını oluştur
        val pdfWriter = PdfWriter(filePath.absolutePath)
        val pdf = PdfDocument(pdfWriter)

        val document = Document(pdf, PageSize.A4)
//  Document -> iText5

        when (index) {
            0 -> Template_1(viewModel, requireActivity(), requireContext()).generateCV(document)
            1 -> Template_2(viewModel, requireActivity(), requireContext()).generateCV(document)
            2 -> Template_3(viewModel, requireActivity(), requireContext()).generateCV(document)
        }

        // Belge içeriği varsa kapatılmalı. Belgede veri yoksa kapatma yapılmıyor. Kapatmadığı için crash oluyor.  Hata veriyor. (Document has no pages.)
        if (pdf.numberOfPages > 0) {
            document.close()
        } else {
            message(requireContext(), "PDF is empty. ")
        }

        // ShowPDF için path'i verdik
        var fr = ShowPDFFragment()
        writePdfPathSharedPreferances(filePath.absolutePath)
        replace(fr)
    }

    fun writePdfPathSharedPreferances(pdfFilePath: String) {

        var sharedPreferences =
            requireContext().getSharedPreferences("PDFFilePath", AppCompatActivity.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString(
            "pdfFilePath",
            pdfFilePath
        )
        editor.apply()
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
            arrayOf(
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE,
                MANAGE_EXTERNAL_STORAGE
            ),
            PERMISSION_CODE
        )
    }

    // izin isteği sonucu kullanıcının verdiği cevapları ele alır.
    // istek kodu, istek izinleri, izin durumlarını(grantResults) parametrelerini alır
    // istek kodu PERMISSION_CODE değerine eşit mi diye kontrol edilir. hangi izin isteği olduğunu belirlemek için kullanılır.
    // dizisinin boyutu kontrol edilir . 0'dan büyükse :
    // Sıfırıncı ve Birinci isteklere bakılır.(Bütün isteklere bakılır) Her ikisinede izin verildiyse message döner.

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.size > 0) {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    message(requireContext(), "Permission Granted..")
                } else {
                    message(requireContext(), "Permission Denied..")
                }
            }
        }
    }

}