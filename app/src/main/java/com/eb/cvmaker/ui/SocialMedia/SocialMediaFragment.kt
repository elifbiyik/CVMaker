package com.eb.cvmaker.ui.SocialMedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.SocialMedia
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentSocialMediaBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocialMediaFragment : Fragment() {

    private lateinit var binding: FragmentSocialMediaBinding
    private val viewModel: SocialMediaVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSocialMediaBinding.inflate(inflater, container, false)

        observe(viewModel.userInfoMLD) {
            if (!it.isNullOrEmpty()) {
                binding.btnAdd.text = requireActivity().resources.getString(R.string.update)

                var user = it[0]
                getUserInfo(user)

                binding.btnAdd.setOnClickListener {
                    val updatedUser = getUserTexts().copy(id = user.id)
                    update(updatedUser)
                }

            } else {
                getUserHintTexts()
                binding.btnAdd.setOnClickListener {
                    val addUser = getUserTexts()
                    add(addUser)
                }
            }

        }

        return binding.root
    }

    fun getUserInfo(userInfo: SocialMedia) {
        with(binding) {

            if (!userInfo.linkedIn.isNullOrEmpty()) {
                etLinkedIn.setText(userInfo.linkedIn)
            } else {
                etLinkedIn.hint =
                    requireActivity().resources.getString(R.string.linkedIn)
            }

            if (!userInfo.github.isNullOrEmpty()) {
                etGithub.setText(userInfo.github)
            } else {
                etGithub.hint =
                    requireActivity().resources.getString(R.string.github)
            }

            if (!userInfo.webSite.isNullOrEmpty()) {
                etWebSites.setText(userInfo.webSite)
            } else {
                etWebSites.hint =
                    requireActivity().resources.getString(R.string.webSites)
            }
        }
    }

    fun getUserTexts(): SocialMedia {
        return SocialMedia(
            github = if (binding.etGithub.text.isNotBlank()) binding.etGithub.text.toString() else null,
            linkedIn = if (binding.etLinkedIn.text.isNotBlank()) binding.etLinkedIn.text.toString() else null,
            webSite = if (binding.etWebSites.text.isNotBlank()) binding.etWebSites.text.toString() else null,
        )
    }

    fun getUserHintTexts() {
        with(binding) {
            etLinkedIn.hint = requireActivity().resources.getString(R.string.linkedIn)
            etGithub.hint =
                requireActivity().resources.getString(R.string.github)
            etWebSites.hint = requireActivity().resources.getString(R.string.webSites)
        }
    }

    fun add(userInfo: SocialMedia) {

        viewModel.saveData(userInfo)
        isSuccessful()
    }

    fun update(userInfo: SocialMedia) {

        viewModel.updateData(userInfo)
        isSuccessful()

    }

    fun delete(userInfo: SocialMedia) {
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

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(InformationsFragment())
        }
    }
}