package com.eb.cvmaker.ui._Create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.eb.cvmaker.databinding.FragmentInformationsBinding
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui.Abilities.AbilitiesFragment
import com.eb.cvmaker.ui.Certificates.CertificatesFragment
import com.eb.cvmaker.ui.Communication.CommunicationFragment
import com.eb.cvmaker.ui.Education.EducationFragment
import com.eb.cvmaker.ui.Experience.ExperienceFragment
import com.eb.cvmaker.ui.HomePageFragment
import com.eb.cvmaker.ui.Language.LanguagesFragment
import com.eb.cvmaker.ui.References.ReferencesFragment
import com.eb.cvmaker.ui.SocialMedia.SocialMediaFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationsFragment : Fragment() {


    private lateinit var binding: FragmentInformationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInformationsBinding.inflate(inflater, container, false)


        with(binding) {
            llAbilities.setOnClickListener {
                replace(AbilitiesFragment())
            }

            llCertificates.setOnClickListener {
                replace(CertificatesFragment())
            }

            llCommunication.setOnClickListener {
                replace(CommunicationFragment())
            }

            llEducation.setOnClickListener {
                replace(EducationFragment())
            }

            llExperience.setOnClickListener {
                replace(ExperienceFragment())
            }

            llLanguage.setOnClickListener {
                replace(LanguagesFragment())
            }

            llReferences.setOnClickListener {
                replace(ReferencesFragment())
            }

            llSocial.setOnClickListener {
                replace(SocialMediaFragment())
            }
        }

        binding.btnCreate.setOnClickListener {
            replace(ChooseTemplateFragment())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(HomePageFragment())
        }
    }

}