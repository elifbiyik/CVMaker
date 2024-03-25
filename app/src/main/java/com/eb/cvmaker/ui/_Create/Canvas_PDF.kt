package com.eb.cvmaker.ui._Create

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eb.cvmaker.Adapter.ChooseTemplateAdapter
import com.eb.cvmaker.R
import com.eb.cvmaker._paragraphStyle.Styles
import com.eb.cvmaker.databinding.FragmentChooseTemplateBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.replace
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

class Canvas_PDF {


    @AndroidEntryPoint
    class ChooseTemplateFragment : Fragment() {

        private lateinit var binding: FragmentChooseTemplateBinding
        private val viewModel: ChooseTemplateVM by viewModels()
        private lateinit var adapter: ChooseTemplateAdapter
        var style = Styles()

        var pageHeight = 1120
        var pageWidth = 792

        // creating a bitmap variable
        // for storing our images
        lateinit var bmp: Bitmap
        lateinit var scaledbmp: Bitmap
        var PERMISSION_CODE = 101

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentChooseTemplateBinding.inflate(inflater, container, false)

            lifecycleScope.launch {
                var list = ArrayList<Uri>()

                list = viewModel.getTemplate()
                var listSize = list.size

                adapter = ChooseTemplateAdapter(list) { uri, index ->

                    // Butona tıklandığında seçilen template'e göre generatePDF çalışmalı
                    // O oluşana kadar progressBar dönsün

                    createCV()

                }
                binding.recView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recView.adapter = adapter


            }

            return binding.root
        }

        fun createCV() {
            // decodeResource = görüntü dosyalarını bitmap nesnelerine dönüştür.
            bmp = BitmapFactory.decodeResource(
                resources,
                R.drawable.communication
            )  // Kullanıcı profili vebaşlık iconalrı için kullan
            // createScaledBitmap = yükseklik ve genişlik belirterek ölçekli bir bitmap oluştur
            // scaledbmp = Bitmap.createScaledBitmap(bmp, pageWidth, pageHeight, false)
            scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false)

            //  val inputStream = assets.open("arka_plan.png")
            //  bmp = BitmapFactory.decodeStream(inputStream)

            // uygulamanın izni varsa oluşturur yoksa izin ister.
            if (checkPermissions()) {
                message(requireContext(), "OK")
                // API seviyesi 19 veya üzeri olan cihazlarda kullanılıyor. !!  (19 altı için ???)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    generatePDF2()
                }
            } else {
                requestPermission()
            }


        }

        /*    fun generatePDF1() {
                // Canvas nesnesi oluştur
                var canvas = Canvas()
                var paint: Paint = Paint()

                // Resmi drawable'a ekle
                var imageView = binding.im
                imageView.setImageResource(R.drawable.template_1)

                // Resmi canvas'ta çiz
                canvas.drawBitmap(
                    imageView.getDrawingCache(),
                    null,
                    Rect(0, 0, canvas.width, canvas.height),
                    null
                )

                // Resmi canvas'ta konumlandır
                canvas.translate(100F, 100F)

                // Ekran üzerindeki konumu bul
                val location = IntArray(2) // Create an empty IntArray with size 2
                imageView.getLocationOnScreen(location) // Pass the empty IntArray

                // Canvas'a göre konumu hesapla
                val canvasX = location[0] - imageView.getLeft() // Access x coordinate from index 0
                val canvasY = location[1] - imageView.getTop()  // Access y coordinate from index 1

                // ...

                // Canvas'ta bir dikdörtgen çizin
                canvas.drawRect(
                    canvasX.toFloat(), canvasY.toFloat(),
                    (canvasX + 100).toFloat(), (canvasY + 100).toFloat(), paint
                )
            } */

        fun generatePDF2() {

            message(requireContext(), "generate")
            var fileName = "example_cv.pdf"


            // Dosyanın oluşturulacağı dizini belirle
            val directory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val filePath = File(directory, fileName)

            // PDF dosyasını oluştur
            val pdfWriter = PdfWriter(filePath.absolutePath)
            val pdf = com.itextpdf.kernel.pdf.PdfDocument(pdfWriter)
            val document = Document(pdf, PageSize.A4)


            // Communication bilgileri bir tane olmalı 2 tane olduğu için pdf'te öyle yazıyor.
            // PDF belgesine paragraf ekle
   /*         document.add(personalInformationNameSurname())
            document.add(personalInformationJob())
            document.add(personalInformationAboutMe())
            document.add(personalInformationCommunication())

 */           // Belgeyi kapat
            document.close()

            // ShowPDF için path'i verdik
            // ShowPDFFragment().pdfFilePath = filePath.absolutePath
            var bundle = Bundle()
            var fr = ShowPDFFragment()
            bundle.putString("path", filePath.absolutePath)
            fr.arguments = bundle
            replace(fr)

        }

 /*       fun personalInformationNameSurname(): Paragraph {
            val infoParagraph = Paragraph()
            observe(viewModel.communicationMLD) {
                it?.forEach {
                    with(infoParagraph) {
                        add(Text(it.name?.toUpperCase()).addStyle(style.styleForNameAndSurname(
                            pdfFont
                        )
                        )).setTextAlignment(
                            TextAlignment.CENTER
                        )
                        add(" ") // Boşluk ekleyerek isim ve soyisim arasına boşluk bırakın
                        add(Text(it.surname?.toUpperCase()).addStyle(style.styleForNameAndSurname(
                            pdfFont
                        )
                        )).setTextAlignment(
                            TextAlignment.CENTER
                        )
                        add("\n")
                        setBorderBottom(SolidBorder(0.5f))
                    }
                }
            }
            return infoParagraph
        }

        fun personalInformationJob(): Paragraph {
            val infoParagraph = Paragraph()
            observe(viewModel.communicationMLD) {
                it?.forEach {
                    with(infoParagraph) {

                        add(Text(it.job?.toUpperCase())).addStyle(style.styleForJobTitle())
                            .setTextAlignment(TextAlignment.CENTER)
                        add("\n")
                        setBorderBottom(SolidBorder(0.5f))

                    }
                }
            }
            return infoParagraph
        }

        fun personalInformationAboutMe(): Paragraph {
            val infoParagraph = Paragraph()
            observe(viewModel.communicationMLD) {
                it?.forEach {
                    with(infoParagraph) {

                        add(Text(it.aboutMe)).addStyle(style.styleForText())
                        add("\n")
                        setBorderBottom(SolidBorder(0.5f))

                    }
                }
            }
            return infoParagraph
        }

        fun personalInformationCommunication(): Paragraph {
            val infoParagraph = Paragraph()
            observe(viewModel.communicationMLD) {
                it?.forEach {

                    val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.background)
                    val bitmap: Bitmap? = drawable?.toBitmap()

                    var stream1 = ByteArrayOutputStream()
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, stream1)
                    var byte = stream1.toByteArray()

                    var imageData1 = ImageDataFactory.create(byte)
                    var image1 = Image(imageData1)

                    with(infoParagraph) {

                        add(image1)
                        add(Text(it.phone)).addStyle(style.styleForText())

                        add("\n")
                        setBorderBottom(SolidBorder(0.5f))

                    }
                }
            }
            return infoParagraph
        }

     */

        fun checkPermissions(): Boolean {

            var writeStoragePermission = ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            var readStoragePermission = ContextCompat.checkSelfPermission(
                requireActivity().applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )

            return writeStoragePermission == PackageManager.PERMISSION_GRANTED
                    && readStoragePermission == PackageManager.PERMISSION_GRANTED
        }

        fun requestPermission() {

            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), PERMISSION_CODE
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
        }


        /*
            fun generatePDF() {

                var pdfDocument = PdfDocument()

        // 1 sayfa olduğunu belirterek bir PDF sayfasının bilgilerini tanımlar
                var myPageInfo: PdfDocument.PageInfo? =
                    PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

                // PDF sayfası oluşturulur (pdfDocument'e yeni bir sayfa ekler).
                var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)


                */
        /*
              Canvas, üzerine çizilen öğelerin konumunu ve boyutunu belirlerken,
              Paint bu öğelerin görünümünü belirler.
               *//*


        //  grafiksel şekiller ve metin çizmek için kullanılan bir araçtır.
        var paint: Paint = Paint()
        var title: Paint = Paint()

//  grafiksel içerik oluşturmak için kullanılan bir çizim tahtasıdır.
        var canvas: Canvas = myPage.canvas

       // var backgroundImage = R.drawable.background
        val backgroundImage = BitmapFactory.decodeResource(resources, R.drawable.template_3_background)

        // Resize the background image
        val resizedBackgroundImage = Bitmap.createScaledBitmap(backgroundImage, pageWidth, pageHeight, false)

      */
        /*  val matrix = Matrix()
                matrix.postScale(
                         pdfDocument.pageInfo.width / resizedBackgroundImage.width.toFloat(),
                    pdfDocument.pageInfo.height / resizedBackgroundImage.height.toFloat()
                )

                canvas.drawBitmap(resizedBackgroundImage, matrix, null)
        *//*

// Görseli PDF sayfasına hizalayın
        canvas.drawBitmap(backgroundImage, 0F,0F, null)


        canvas.drawBitmap(scaledbmp, 56F, 40F, paint)  // Kullanıcnın görseli


        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))

        title.textSize = 40F
        title.setColor(ContextCompat.getColor(requireContext(), R.color.black))

        val bounds = Rect()
        var name = "Kullanıcı Adı"
        paint.getTextBounds(name, 0, name.length, bounds)
        val nameWidth = bounds.width().toFloat()
        var surname = "Kullanıcı Soyadı"


        canvas.drawText(name.toUpperCase(), 220F, 100F, title)

        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        title.setColor(ContextCompat.getColor(requireContext(), R.color.red))
        title.textSize = 15F
        canvas.drawText(surname.toUpperCase(), 220F + nameWidth + 5F, 100F, title)


        title.textAlign = Paint.Align.CENTER
        canvas.drawText("This is sample document which we have created.", 396F, 560F, title)

        pdfDocument.finishPage(myPage)
        // Şuan pdf Oluştu!


        //    val file: File = File(Environment.getExternalStorageDirectory(), "GFG.pdf")
        //     val file: File = File(requireActivity().getExternalFilesDir(null), "GFG.pdf")

        // Pdf'in kayıt olacağı path (file/download)
        val file: File = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "GFG.pdf"
        )

        try {
//pdfDocument: Bellekteki PDF belgenizin içeriğini tutar.
// FileOutputStream(file): PDF'nizin içeriğini file tarafından belirtilen konuma yazmak için kullanılan bir çıktı akışıdır.
            pdfDocument.writeTo(FileOutputStream(file)) // PDF belgesini bellekte oluşturup dosya sistemine yazar.
            message(requireContext(), "PDF file generated..")

            // Dosya oluşturulduğunda pdf sayfasına gönder



        } catch (e: Exception) {

            var mes = e.printStackTrace()

            message(requireContext(), "Fail to generate PDF file..")
            Log.d("!!!! HATA", mes.toString())
        }

        pdfDocument.close()

    }
*/

    }



}