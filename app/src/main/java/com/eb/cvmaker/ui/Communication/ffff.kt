import com.eb.cvmaker.R



/*
package com.eb.cvmaker.ui.Communication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentCommunicationBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import dagger.hilt.android.AndroidEntryPoint

class ffff {

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
                etUsername.hint = userInfo.name ?: requireActivity().resources.getString(R.string.name)
                etSurname.hint =
                    userInfo.surname ?: requireActivity().resources.getString(R.string.surname)
                etMail.hint = userInfo.email ?: requireActivity().resources.getString(R.string.email)
                etPhone.hint = userInfo.phone ?: requireActivity().resources.getString(R.string.phone)
                etJob.hint = userInfo.job ?: requireActivity().resources.getString(R.string.job)
                etAboutMe.hint =
                    userInfo.aboutMe ?: requireActivity().resources.getString(R.string.aboutMe)
                etAddress.hint =
                    userInfo.aboutMe ?: requireActivity().resources.getString(R.string.address)
                etGender.hint =
                    userInfo.gender ?: requireActivity().resources.getString(R.string.gender)
                etBirtday.hint =
                    userInfo.birtday ?: requireActivity().resources.getString(R.string.birtday)
                etGender.hint =
                    userInfo.gender ?: requireActivity().resources.getString(R.string.gender)
                etDrivingLicence.hint = userInfo.drivingLicence
                    ?: requireActivity().resources.getString(R.string.drivingLicence)
                etMilitary.hint =
                    userInfo.military ?: requireActivity().resources.getString(R.string.military)
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



}*/
