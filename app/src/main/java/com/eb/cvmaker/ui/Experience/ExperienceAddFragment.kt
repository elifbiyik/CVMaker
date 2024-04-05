package com.eb.cvmaker.ui.Experience

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentExperienceAddBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExperienceAddFragment : Fragment() {

    private lateinit var binding: FragmentExperienceAddBinding
    private val viewModel: ExperienceAddVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var id = arguments?.getInt("id")
        var nameCom = arguments?.getString("nameCom")
        var namePos = arguments?.getString("namePos")
        var dateSt = arguments?.getString("dateSt")
        var dateFin = arguments?.getString("dateFin") ?: requireActivity().resources.getString(R.string.present)
        var info = arguments?.getString("info")

        binding = FragmentExperienceAddBinding.inflate(inflater, container, false)

        if (id != null && nameCom != null && namePos != null && dateSt != null) {

            val startMonth = dateSt.substring(0, dateSt.indexOf('.'))
            val startYear = dateSt.substring(dateSt.indexOf('.') + 1)

            var finishMonth = ""
            var finishYear = ""
            if(!dateFin.isNullOrEmpty()) {
                 finishMonth = dateFin.substring(0, dateFin.indexOf('.'))
                 finishYear = dateFin.substring(dateFin.indexOf('.') + 1)
            }

            binding.etCompanyName.setText(nameCom)
            binding.etPositionName.setText(namePos)
            binding.etInfo.setText(info)
            binding.etStartDateMonth.setText(startMonth)
            binding.etStartDateYear.setText(startYear)
            binding.etFinishDateMonth.setText(finishMonth)
            binding.etFinishDateYear.setText(finishYear)

            binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
        }

        with(binding) {

            btnAdd.setOnClickListener {
                var company = etCompanyName.text.toString()
                var position = etPositionName.text.toString()
                var info = etInfo.text.toString() ?: null
                var startDate = etStartDateMonth.text.toString() +"."+ etStartDateYear.text.toString()
                var finishDate :String

                if(!etFinishDateMonth.text.isNullOrEmpty() && !etFinishDateYear.text.isNullOrEmpty()) {
                    finishDate = etFinishDateMonth.text.toString() + "." + etFinishDateYear.text.toString()
                } else {
                    finishDate = etFinishDateMonth.text.toString() // Boş bırakıldığında (.) yazmasın diye month'u yazdırdık ama month yazmayack zaten (continue yazacak)
                }

                if (company.isNotEmpty() && position.isNotEmpty() && etStartDateMonth.text.toString().isNotEmpty() && etStartDateYear.text.toString().isNotEmpty()) {
                    if (id != null) {
                        var item = Experience(id = id, companyName = company, positionName = position, infoAboutJob = info, startDate = startDate, finishDate = finishDate)
                        update (item)
                    } else {
                        var item = Experience(
                            companyName = company, positionName = position, infoAboutJob = info, startDate = startDate, finishDate = finishDate)
                        add(item)
                    }
                } else {
                    message(
                        requireContext(),
                        requireActivity().resources.getString(R.string.messageForEmpty)
                    )
                }
                isSuccessful()
            }
        }

        return binding.root
    }

    fun update(userInfo : Experience) {
        viewModel.updateData(userInfo)
        isSuccessful()
    }

    fun add(userInfo : Experience) {
        viewModel.saveData(userInfo)
        isSuccessful()
    }

    fun isSuccessful() {
        observe(viewModel.isSuccessfulMLD) {
            if (it == true)
                message(
                    requireContext(),
                    requireActivity().resources.getString(R.string.messageForSaved)
                )
            replace(ExperienceFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(ExperienceFragment())
        }
    }
}


