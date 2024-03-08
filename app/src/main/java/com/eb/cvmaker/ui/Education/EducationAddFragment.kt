package com.eb.cvmaker.ui.Education

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentEducationAddBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EducationAddFragment : Fragment() {

    private lateinit var binding: FragmentEducationAddBinding
    private val viewModel: EducationAddVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var id = arguments?.getInt("id")
        var nameDep = arguments?.getString("nameDep")
        var nameSch = arguments?.getString("nameSch")
        var dateSt = arguments?.getString("dateSt")
        var dateFin = arguments?.getString("dateFin")
        var gano = arguments?.getString("gano")

        binding = FragmentEducationAddBinding.inflate(inflater, container, false)

        if (id != null && nameDep != null && nameSch != null && dateSt != null) {

            binding.etDepartmentName.setText(nameDep)
            binding.etSchoolName.setText(nameSch)

            gano.let {
                binding.etGano.setText(gano)
            }

            binding.etStartDate.setText(dateSt)

            dateFin.let {
                binding.etFinishDate.setText(dateFin)
            }

            binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
        }

        with(binding) {
            btnAdd.setOnClickListener {
                var school = etSchoolName.text.toString().trim()
                var department = etDepartmentName.text.toString().trim()
                var gano = etGano.text.toString().trim() ?: null
                var startDate = etStartDate.text.toString().trim()
                var finishDate = etFinishDate.text.toString().trim() ?: null


                if (school.isNotEmpty() && department.isNotEmpty() && startDate.isNotEmpty()) {
                    if (id != null) {
                        var item = Education(id, school, department, gano, startDate, finishDate)
                        update(item)
                    } else {
                        var item = Education(
                            schoolName = school,
                            departmentName = department,
                            gano = gano,
                            startDate = startDate,
                            finishDate = finishDate
                        )
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

    fun update(userInfo: Education) {

        viewModel.updateData(userInfo)
        isSuccessful()
    }

    fun add(userInfo: Education) {

        viewModel.saveData(userInfo)
        isSuccessful()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(EducationFragment())
        }
    }

    fun isSuccessful() {
        observe(viewModel.isSuccessfulMLD) {
            if (it == true)
                message(
                    requireContext(),
                    requireActivity().resources.getString(R.string.messageForSaved)
                )
            replace(EducationFragment())
        }
    }
}

