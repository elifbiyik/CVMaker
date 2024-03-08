package com.eb.cvmaker.ui.Communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentCommunicationBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunicationFragment : Fragment() {

    private lateinit var binding: FragmentCommunicationBinding
    private val viewModel: CommunicationVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunicationBinding.inflate(inflater, container, false)

        binding.imDelete.setOnClickListener {
            observe(viewModel.userInfoMLD) {
                if (!it.isNullOrEmpty()) {
                    var user = it[0]
                    delete(user)
                    getUserHintTexts()
                    getUserTexts()
                }
            }
        }

        observe(viewModel.userInfoMLD) {

            if (!it.isNullOrEmpty()) {
                var user = it[0]

                getUserInfo(user)

                binding.btnApprove.setOnClickListener {
                    val updatedUser = getUserTexts().copy(id = user.id)
                    update(updatedUser)
                }
            } else {
                getUserHintTexts()
                binding.btnApprove.setOnClickListener {
                    val addUser = getUserTexts()
                    add(addUser)
                }
            }
        }

        return binding.root
    }

    fun getUserTexts(): Communication {
        return Communication(
            name = if (binding.etUsername.text.isNotBlank()) binding.etUsername.text.toString() else null,
            surname = if (binding.etSurname.text.isNotBlank()) binding.etSurname.text.toString() else null,
            email = if (binding.etMail.text.isNotBlank()) binding.etMail.text.toString() else null,
            phone = if (binding.etPhone.text.isNotBlank()) binding.etPhone.text.toString() else null,
            job = if (binding.etJob.text.isNotBlank()) binding.etJob.text.toString() else null,
            aboutMe = if (binding.etAboutMe.text.isNotBlank()) binding.etAboutMe.text.toString() else null,
            birtday = if (binding.etBirtday.text.isNotBlank()) binding.etBirtday.text.toString() else null,
            gender = if (binding.etGender.text.isNotBlank()) binding.etGender.text.toString() else null,
            drivingLicence = if (binding.etDrivingLicence.text.isNotBlank()) binding.etDrivingLicence.text.toString() else null,
            military = if (binding.etMilitary.text.isNotBlank()) binding.etMilitary.text.toString() else null
        )
    }

    fun getUserHintTexts() {
        with(binding) {
            etUsername.hint = requireActivity().resources.getString(R.string.name)
            etSurname.hint =
                requireActivity().resources.getString(R.string.surname)
            etMail.hint = requireActivity().resources.getString(R.string.email)
            etPhone.hint = requireActivity().resources.getString(R.string.phone)
            etJob.hint = requireActivity().resources.getString(R.string.job)
            etAboutMe.hint = requireActivity().resources.getString(R.string.aboutMe)
            etAddress.hint = requireActivity().resources.getString(R.string.address)
            etGender.hint = requireActivity().resources.getString(R.string.gender)
            etBirtday.hint = requireActivity().resources.getString(R.string.birtday)
            etGender.hint = requireActivity().resources.getString(R.string.gender)
            etDrivingLicence.hint =
                requireActivity().resources.getString(R.string.drivingLicence)
            etMilitary.hint = requireActivity().resources.getString(R.string.military)
        }
    }

    fun getUserInfo(userInfo: Communication) {
        with(binding) {
            if (!userInfo.name.isNullOrEmpty()) {
                etUsername.setText(userInfo.name)
            } else {
                binding.etUsername.hint =
                    requireActivity().resources.getString(R.string.name)
            }

            if (!userInfo.surname.isNullOrEmpty()) {
                binding.etSurname.setText(userInfo.surname)
            } else {
                binding.etSurname.hint =
                    requireActivity().resources.getString(R.string.surname)
            }

            if (!userInfo.job.isNullOrEmpty()) {
                binding.etJob.setText(userInfo.job)
            } else {
                binding.etJob.hint =
                    requireActivity().resources.getString(R.string.job)
            }

            if (!userInfo.email.isNullOrEmpty()) {
                binding.etMail.setText(userInfo.email)
            } else {
                binding.etMail.hint =
                    requireActivity().resources.getString(R.string.email)
            }

            if (!userInfo.phone.isNullOrEmpty()) {
                binding.etPhone.setText(userInfo.phone)
            } else {
                binding.etPhone.hint =
                    requireActivity().resources.getString(R.string.phone)
            }

            if (!userInfo.aboutMe.isNullOrEmpty()) {
                binding.etAboutMe.setText(userInfo.aboutMe)
            } else {
                binding.etAboutMe.hint =
                    requireActivity().resources.getString(R.string.aboutMe)
            }

            if (!userInfo.address.isNullOrEmpty()) {
                binding.etAddress.setText(userInfo.address)
            } else {
                binding.etAddress.hint =
                    requireActivity().resources.getString(R.string.address)
            }

            if (!userInfo.birtday.isNullOrEmpty()) {
                binding.etBirtday.setText(userInfo.birtday)
            } else {
                binding.etBirtday.hint =
                    requireActivity().resources.getString(R.string.birtday)
            }

            if (!userInfo.gender.isNullOrEmpty()) {
                binding.etGender.setText(userInfo.surname)
            } else {
                binding.etGender.hint =
                    requireActivity().resources.getString(R.string.gender)
            }

            if (!userInfo.drivingLicence.isNullOrEmpty()) {
                binding.etDrivingLicence.setText(userInfo.drivingLicence)
            } else {
                binding.etDrivingLicence.hint =
                    requireActivity().resources.getString(R.string.drivingLicence)
            }

            if (!userInfo.military.isNullOrEmpty()) {
                binding.etMilitary.setText(userInfo.military)
            } else {
                binding.etMilitary.hint =
                    requireActivity().resources.getString(R.string.military)
            }
        }
    }

    fun add(userInfo: Communication) {

        viewModel.saveData(userInfo)
        isSuccessful()
    }

    fun update(userInfo: Communication) {

        viewModel.updateData(userInfo)
        isSuccessful()
    }

    fun delete(userInfo: Communication) {
        viewModel.deleteData(userInfo)
        isSuccessful()
    }

    fun isSuccessful() {
        observe(viewModel.isSuccessfulMLD) {
            if (it == true)
                return@observe message(
                    requireContext(),
                    requireActivity().resources.getString(R.string.messageForSaved)
                )
        }
    }
}

/*
@AndroidEntryPoint
class CommunicationFragment : Fragment() {

    private lateinit var binding: FragmentCommunicationBinding
    private val viewModel: CommunicationVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunicationBinding.inflate(inflater, container, false)

        binding.imDelete.setOnClickListener {
            observe(viewModel.userInfoMLD) {
                if (!it.isNullOrEmpty()) {
                    var user = it[0]
                    delete(user)
                    getUserHintTexts()
                    getUserTexts()
                }
            }
        }

        observe(viewModel.userInfoMLD) {

            if (!it.isNullOrEmpty()) {
                var user = it[0]

                getUserInfo(user)

                binding.btnApprove.setOnClickListener {
                    val updatedUser = getUserTexts().copy(id = user.id)
                    update(updatedUser)
                }
            } else {
                getUserHintTexts()
                binding.btnApprove.setOnClickListener {
                    val addUser = getUserTexts()
                    add(addUser)
                }
            }
        }

        return binding.root
    }

    fun getUserTexts(): Communication {
        return Communication(
            name = if (binding.etUsername.text.isNotBlank()) binding.etUsername.text.toString() else null,
            surname = if (binding.etSurname.text.isNotBlank()) binding.etSurname.text.toString() else null,
            email = if (binding.etMail.text.isNotBlank()) binding.etMail.text.toString() else null,
            phone = if (binding.etPhone.text.isNotBlank()) binding.etPhone.text.toString() else null,
            job = if (binding.etJob.text.isNotBlank()) binding.etJob.text.toString() else null,
            aboutMe = if (binding.etAboutMe.text.isNotBlank()) binding.etAboutMe.text.toString() else null,
            birtday = if (binding.etBirtday.text.isNotBlank()) binding.etBirtday.text.toString() else null,
            gender = if (binding.etGender.text.isNotBlank()) binding.etGender.text.toString() else null,
            drivingLicence = if (binding.etDrivingLicence.text.isNotBlank()) binding.etDrivingLicence.text.toString() else null,
            military = if (binding.etMilitary.text.isNotBlank()) binding.etMilitary.text.toString() else null
        )
    }

    fun getUserHintTexts() {
        with(binding) {
            etUsername.hint = requireActivity().resources.getString(R.string.name)
            etSurname.hint =
                requireActivity().resources.getString(R.string.surname)
            etMail.hint = requireActivity().resources.getString(R.string.email)
            etPhone.hint = requireActivity().resources.getString(R.string.phone)
            etJob.hint = requireActivity().resources.getString(R.string.job)
            etAboutMe.hint = requireActivity().resources.getString(R.string.aboutMe)
            etAddress.hint = requireActivity().resources.getString(R.string.address)
            etGender.hint = requireActivity().resources.getString(R.string.gender)
            etBirtday.hint = requireActivity().resources.getString(R.string.birtday)
            etGender.hint = requireActivity().resources.getString(R.string.gender)
            etDrivingLicence.hint =
                requireActivity().resources.getString(R.string.drivingLicence)
            etMilitary.hint = requireActivity().resources.getString(R.string.military)
        }
    }

    fun getUserInfo(userInfo: Communication) {
        with(binding) {

            if (!userInfo.name.isNullOrEmpty()) {
                binding.etUsername.setText(userInfo.name)
            } else {
                binding.etUsername.hint =
                    requireActivity().resources.getString(R.string.name)
            }

            if (!userInfo.surname.isNullOrEmpty()) {
                binding.etSurname.setText(userInfo.surname)
            } else {
                binding.etSurname.hint =
                    requireActivity().resources.getString(R.string.surname)
            }

            if (!userInfo.job.isNullOrEmpty()) {
                binding.etJob.setText(userInfo.job)
            } else {
                binding.etJob.hint =
                    requireActivity().resources.getString(R.string.job)
            }

            if (!userInfo.email.isNullOrEmpty()) {
                binding.etMail.setText(userInfo.email)
            } else {
                binding.etMail.hint =
                    requireActivity().resources.getString(R.string.email)
            }

            if (!userInfo.phone.isNullOrEmpty()) {
                binding.etPhone.setText(userInfo.phone)
            } else {
                binding.etPhone.hint =
                    requireActivity().resources.getString(R.string.phone)
            }

            if (!userInfo.aboutMe.isNullOrEmpty()) {
                binding.etAboutMe.setText(userInfo.aboutMe)
            } else {
                binding.etAboutMe.hint =
                    requireActivity().resources.getString(R.string.aboutMe)
            }

            if (!userInfo.address.isNullOrEmpty()) {
                binding.etAddress.setText(userInfo.address)
            } else {
                binding.etAddress.hint =
                    requireActivity().resources.getString(R.string.address)
            }

            if (!userInfo.birtday.isNullOrEmpty()) {
                binding.etBirtday.setText(userInfo.birtday)
            } else {
                binding.etBirtday.hint =
                    requireActivity().resources.getString(R.string.birtday)
            }

            if (!userInfo.gender.isNullOrEmpty()) {
                binding.etGender.setText(userInfo.surname)
            } else {
                binding.etGender.hint =
                    requireActivity().resources.getString(R.string.gender)
            }

            if (!userInfo.drivingLicence.isNullOrEmpty()) {
                binding.etDrivingLicence.setText(userInfo.drivingLicence)
            } else {
                binding.etDrivingLicence.hint =
                    requireActivity().resources.getString(R.string.drivingLicence)
            }

            if (!userInfo.military.isNullOrEmpty()) {
                binding.etMilitary.setText(userInfo.military)
            } else {
                binding.etMilitary.hint =
                    requireActivity().resources.getString(R.string.military)
            }
        }
    }

    fun add(userInfo: Communication) {

        viewModel.saveData(userInfo)
        isSuccessful()
    }

    fun update(userInfo: Communication) {

        viewModel.updateData(userInfo)
        isSuccessful()
    }

    fun delete(userInfo: Communication) {
        viewModel.deleteData(userInfo)
        isSuccessful()
    }

    fun isSuccessful() {
        observe(viewModel.isSuccessfulMLD) {
            if (it == true)
                return@observe message(
                    requireContext(),
                    requireActivity().resources.getString(R.string.messageForSaved)
                )
        }
    }
}

*/


