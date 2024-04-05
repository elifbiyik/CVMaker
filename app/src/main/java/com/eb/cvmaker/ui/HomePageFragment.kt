package com.eb.cvmaker.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.eb.cvmaker.Adapter.HomePageAdapter
import com.eb.cvmaker.MainActivity
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentHomePageBinding
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale


@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageVM by viewModels()
    private lateinit var banner1: AdView
    private lateinit var banner2: AdView
    private lateinit var adapter: HomePageAdapter


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

        lifecycleScope.launch {
            var list = viewModel.getTemplate()
            adapter = HomePageAdapter(list)
            binding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = adapter
        }

        binding.btnCreate.setOnClickListener {
            replace(InformationsFragment())
        }


        var language = getLocaleSharedPreferances()
        binding.tvLanguage.text = language
        language?.let { updateLanguageUI(it) }

        binding.switchLanguage.setOnClickListener {
            if (binding.switchLanguage.isChecked) {
                setLocale("en")
                updateLanguageUI("en")

            } else {
                setLocale("tr")
                updateLanguageUI("tr")
            }
        }


        return binding.root
    }

    fun setLocale(selectedLocale: String) {
        val locale = Locale(selectedLocale) //gönderdiğimiz parametreye göre lokalimizi ayarladık.
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireActivity().baseContext.resources.updateConfiguration(
            config,
            requireActivity().baseContext.resources.displayMetrics
        )
        writeLocaleSharedPreferances(selectedLocale)//dil seçimini cihaza kaydedecek fonksiyonu çağırıyoruz.
        val intent = Intent(requireContext(), MainActivity::class.java)
        requireActivity().finish()//mevcut acivity i bitir.
        startActivity(intent)//activity i baştan yükle
    }

    fun writeLocaleSharedPreferances(selectedLocale: String) {
        var sharedPreferences =
            requireContext().getSharedPreferences("language", AppCompatActivity.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString(
            "selectedLocale",
            selectedLocale
        )
        editor.apply()
    }

    fun getLocaleSharedPreferances(): String? {
        var sharedPreferences =
            context?.getSharedPreferences("language", AppCompatActivity.MODE_PRIVATE)
        var language = sharedPreferences?.getString("selectedLocale", "")
        return language
    }

    fun updateLanguageUI(language: String) {
        if (language == "en") {
            binding.tvLanguage.setText(language)
            binding.switchLanguage.isChecked = true
        } else {
            binding.tvLanguage.setText(language)
            binding.switchLanguage.isChecked = false
        }
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