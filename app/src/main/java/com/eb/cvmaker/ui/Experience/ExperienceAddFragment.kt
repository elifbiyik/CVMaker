package com.eb.cvmaker.ui.Experience

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentExperienceAddBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
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
        var dateFin = arguments?.getString("dateFin") ?: requireActivity().resources.getString(R.string.continueEx)
        var info = arguments?.getString("info")

        binding = FragmentExperienceAddBinding.inflate(inflater, container, false)

        if (id != null && nameCom != null && namePos != null &&
            dateSt != null ) {

            val startMonth = dateSt.substring(0, dateSt.indexOf('.'))
            val startYear = dateSt.substring(dateSt.indexOf('.') + 1)

            val finishMonth = dateFin.substring(0, dateFin.indexOf('.'))
            val finishYear = dateFin.substring(dateFin.indexOf('.') + 1)


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
                   finishDate =
                       etFinishDateMonth.text.toString() + "." + etFinishDateYear.text.toString()
               } else {
                   finishDate = etFinishDateMonth.text.toString() // Boş bırakıldığında (.) yazmasın diye month'u yazdırdık ama monyh yazmayack zaten (continue yazacak)
               }

                if (id != null) {
                    update(id, company, position, info, startDate, finishDate)
                } else {
                    add(company, position, info, startDate, finishDate)
                }
            }
        }

        return binding.root
    }

    fun update(
        id: Int,
        company: String,
        position: String,
        info: String ?= null,
        startDate: String,
        finishDate: String
    ) {
        if (company.isNotEmpty() && position.isNotEmpty() && startDate.isNotEmpty()) {

            var userInfo = Experience(
                id = id,
                companyName = company,
                positionName = position,
                startDate = startDate,
                finishDate = finishDate,
                infoAboutJob = info
            )

            if (company.isNotEmpty() && position.isNotEmpty() && startDate.isNotEmpty()) {
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
                replace(ExperienceFragment())
            }
        }
    }

    fun add(
        company: String,
        position: String,
        info: String ?= null,
        startDate: String,
        finishDate: String
    ) {
        if (company.isNotEmpty() && position.isNotEmpty() && startDate.isNotEmpty() ) {
            var userInfo = Experience(
                companyName = company,
                positionName = position,
                startDate = startDate,
                finishDate = finishDate,
                infoAboutJob = info
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


