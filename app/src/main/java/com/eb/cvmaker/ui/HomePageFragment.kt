package com.eb.cvmaker.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.eb.cvmaker.Model.AppLanguage
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentHomePageBinding
import com.eb.cvmaker.replace
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Locale

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var banner1: AdView
    private lateinit var banner2: AdView
    private lateinit var banner3: AdView
    lateinit var appLanguage: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        val adRequest = AdRequest.Builder().build()

        banner1 = binding.adView
        banner1.loadAd(adRequest)

        banner2 = binding.adView2
        banner2.loadAd(adRequest)

        banner3 = binding.adView3
        banner3.loadAd(adRequest)

        binding.btnCreate.setOnClickListener {
            replace(InformationsFragment())
        }

        // Oluşturulan CV
        var pdfFilePath = getPdfPathSharedPreferances()
        if (!pdfFilePath.isNullOrEmpty()) {
            binding.title.visibility = View.VISIBLE
            binding.createdCV.visibility = View.VISIBLE
            val file = File(pdfFilePath)
            if (file.exists()) {
                loadPdfFromPath(pdfFilePath)
            }
        } else {
            binding.title.visibility = View.GONE
            binding.createdCV.visibility = View.GONE
        }

        chooseLanguage()

        binding.languageFlagAndName.setOnClickListener {
            replace(AppLanguageFragment())
        }

        return binding.root
    }

    fun chooseLanguage() {
        //          var appLanguage = getLocaleSharedPreferances()
        appLanguage = Locale.getDefault().language

        if (appLanguage.isNullOrEmpty() || appLanguage == "") {
            appLanguage = Locale.getDefault().language
        }

        AppLanguage.listAppLanguages.forEach {
            with(binding) {
                if (appLanguage == it.code) {
                    tvLang.setText(it.languageName)
                    it.flag?.let { it1 -> image.setImageResource(it1) }
                }
            }
        }
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
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableAntialiasing(true)
            .load()
    }

    private fun alertDialog() {
        var exit = requireActivity().resources.getString(R.string.alert_exit)
        var yes = requireActivity().resources.getString(R.string.alert_Yes)
        var no = requireActivity().resources.getString(R.string.alert_No)
        var logOut = requireActivity().resources.getString(R.string.alert_logOut)

        SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
            .setTitleText(exit)
            .setContentText(logOut)
            .setConfirmText(yes)

            .setConfirmClickListener {
                requireActivity().finish()
            }
            .setCancelButton(no) { sDialog ->
                sDialog.dismissWithAnimation()
            }
            .show()
    }

    override fun onResume() {
        super.onResume()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            alertDialog()
        }
    }
}