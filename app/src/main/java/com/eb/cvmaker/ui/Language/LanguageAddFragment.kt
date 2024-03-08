package com.eb.cvmaker.ui.Language

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentLanguageAddBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageAddFragment : Fragment() {

    private lateinit var binding: FragmentLanguageAddBinding
    private val viewModel: LanguageAddVM by viewModels()

    private val seekBarSteps = 6
    private val maxSeekBarValue = seekBarSteps - 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var id = arguments?.getInt("id")
        var name = arguments?.getString("name")
        var level = arguments?.getString("level")

        binding = FragmentLanguageAddBinding.inflate(inflater, container, false)

        if (id != null && name != null && level != null) {
            binding.etLanguageName.setText(name)
            binding.tvLevel.text = level
            updateLevelSeekbar(level!!, binding.sbLevel)

            Log.d("LANGUAGE ADD", name.toString() + " - " + level)

            binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
        }


        binding.sbLevel.max = maxSeekBarValue
        binding.sbLevel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Seviye değiştiğinde metni güncelle
                updateLevelText(progress, binding.tvLevel)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        with(binding) {
            btnAdd.setOnClickListener {
                var language = etLanguageName.text.toString()
                var level = binding.tvLevel.text.toString()

                if (id != null) {
                    update(id, language, level)
                } else {
                    add(language, level)
                }
            }
        }

        return binding.root
    }

    fun update(id: Int, language: String, level: String) {
        if (language.isNotEmpty()) {

            var userInfo = Languages(
                id = id,
                languageName = language,
                level = level
            )

            viewModel.updateData(userInfo)

        } else {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForEmpty)
            )
        }

        observe(viewModel.isSuccessfulMLD) {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForSaved)
            )
            replace(LanguagesFragment())
        }
    }

    fun add(language: String, level: String) {
        if (language.isNotEmpty()) {

            var userInfo = Languages(
                languageName = language,
                level = level
            )
            viewModel.saveData(userInfo)

        } else {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForEmpty)
            )
        }

        observe(viewModel.isSuccessfulMLD) {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForSaved)
            )
            replace(LanguagesFragment())
        }
    }


    fun updateLevelText(progress: Int, tvLevel: TextView) {
        // Progress'e göre uygun seviyeyi belirle
        val level = when (progress) {
            0 -> "A1"
            1 -> "A2"
            2 -> "B1"
            3 -> "B2"
            4 -> "C1"
            5 -> "C2"
            else -> "A1"
        }
        tvLevel.text = level
    }

    fun updateLevelSeekbar(level: String, sbLevel: SeekBar) {
        val progress = when (level) {
            "A1" -> 0
            "A2" -> 1
            "B1" -> 2
            "B2" -> 3
            "C1" -> 4
            "C2" -> 5
            else -> -1
        }
        sbLevel.progress = progress
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(LanguagesFragment())
        }
    }
}