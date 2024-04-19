package com.eb.cvmaker.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.eb.cvmaker.Adapter.AppLanguageAdapter
import com.eb.cvmaker.Model.AppLanguage
import com.eb.cvmaker.databinding.FragmentAppLanguageBinding
import com.eb.cvmaker.replace
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class AppLanguageFragment: Fragment() {

    private lateinit var binding: FragmentAppLanguageBinding
    private lateinit var adapter: AppLanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppLanguageBinding.inflate(inflater, container, false)

        var list = AppLanguage.listAppLanguages
        adapter = AppLanguageAdapter(list) {

            it.code?.let { it1 ->
                setLocale(it1)
                writeLocaleSharedPreferances(it1)
            }
            replace(HomePageFragment())
        }

        binding.recView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recView.adapter = adapter

        return binding.root
    }

    fun setLocale(selectedLocale: String) {
        val locale = Locale(selectedLocale)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireActivity().baseContext.resources.updateConfiguration(
            config,
            requireActivity().baseContext.resources.displayMetrics
        )
        activity?.recreate()
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
}