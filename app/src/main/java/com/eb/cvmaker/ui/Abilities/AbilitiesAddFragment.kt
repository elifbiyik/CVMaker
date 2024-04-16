package com.eb.cvmaker.ui.Abilities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentAddAbilitiesBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbilitiesAddFragment : Fragment() {

    private lateinit var binding: FragmentAddAbilitiesBinding
    private val viewModel: AbilitiesAddVM by viewModels()
    var level = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var id = arguments?.getInt("id")
        var name = arguments?.getString("name")
        var level = arguments?.getString("level")

        binding = FragmentAddAbilitiesBinding.inflate(inflater, container, false)

        if (id != null && name != null && level != null) {
            binding.etAbilitiesName.setText(name)
            updateLevel(level)
            binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
        } else {
           level = requireActivity().resources.getString(R.string.basis) // Başlangıç değeri baiss atandı
            binding.rbBasis.isChecked = true
        }

        binding.radioGroup.setOnCheckedChangeListener { group, id ->
            when (id) {
                R.id.rbBasis -> {
                    level = requireActivity().resources.getString(R.string.basis)
                }

                R.id.rbAverage -> {
                    level = requireActivity().resources.getString(R.string.average)
                }

                R.id.rbAdvanced -> {
                    level = requireActivity().resources.getString(R.string.advanced)
                }

                R.id.rbExpert -> {
                    level = requireActivity().resources.getString(R.string.expert)
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            var abilityName = binding.etAbilitiesName.text.toString()

            if (id != null) {
                update(id, abilityName, level)
            } else {
                add(abilityName, level)
            }
        }


        return binding.root
    }

    fun update(id: Int, abilityName: String, level: String ?= null) {
        if (abilityName.isNotEmpty()) {

            var userInfo = Abilities(
                id = id,
                abilitiesName = abilityName,
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
            replace(AbilitiesFragment())
        }
    }

    fun add(abilityName: String, level: String ?= null) {
        if (abilityName.isNotEmpty()) {
            var userInfo = Abilities(
                abilitiesName = abilityName,
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
            replace(AbilitiesFragment())
        }
    }


    fun updateLevel(level: String) {
        when (level) {
            requireActivity().resources.getString(R.string.basis) -> binding.rbBasis.isChecked =
                true

            requireActivity().resources.getString(R.string.average) -> binding.rbAverage.isChecked =
                true

            requireActivity().resources.getString(R.string.advanced) -> binding.rbAdvanced.isChecked =
                true

            requireActivity().resources.getString(R.string.expert) -> binding.rbExpert.isChecked =
                true

            else -> -1
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(AbilitiesFragment())
        }
    }

}