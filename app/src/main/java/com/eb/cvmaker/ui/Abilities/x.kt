/*
package com.eb.cvmaker.ui.Abilities

import androidx.activity.addCallback
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.R
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace

class x {


  var id = arguments?.getInt("id")
    var name = arguments?.getString("name")
    var level = arguments?.getString("level")

    binding = FragmentAddAbilitiesBinding.inflate(inflater, container, false)

    if (id != null && name != null && level != null) {
        binding.etAbilitiesName.setText(name)
        //       binding.tvLevel.text = level
        //    updateLevelSeekbar(level!!, binding.sbLevel)

        binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
    }

    */
/*
            binding.sbLevel.max = maxSeekBarValue
            binding.sbLevel.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    updateLevelText(p1, binding.tvLevel)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })
    *//*


    binding.rbBasis.isChecked = true

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



    binding.btnAdd.setOnClickListener
    {
        var abilityName = binding.etAbilitiesName.text.toString()
        //      var level = binding.tvLevel.text.toString()

        if (id != null) {
            update(id, abilityName, level)
        } else {
            add(abilityName, level)
        }
    }

    return binding.root
}

fun update(id: Int, abilityName: String, level: String) {
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

fun add(abilityName: String, level: String) {
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

*/
/*  fun updateLevelText(progress: Int, tvLevel: TextView) {
      val level = when (progress) {
          0 -> "1"
          1 -> "2"
          2 -> "3"
          3 -> "4"
          4 -> "5"
          else -> "1"
      }
      tvLevel.text = level
  }
*//*

*/
/* fun updateLevelSeekbar(level: String, sbLevel: SeekBar) {
     val progress = when (level) {
         "1" -> 0
         "2" -> 1
         "3" -> 2
         "4" -> 3
         "5" -> 4
         else -> -1
     }
     sbLevel.progress = progress
 }
*//*

override fun onResume() {
    super.onResume()
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        replace(AbilitiesFragment())
    }
}

}*/
