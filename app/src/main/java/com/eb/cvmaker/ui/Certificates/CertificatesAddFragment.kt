package com.eb.cvmaker.ui.Certificates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentCertificatesAddBinding
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertificatesAddFragment : Fragment() {

    private lateinit var binding: FragmentCertificatesAddBinding
    private val viewModel: CertificatesAddVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var id = arguments?.getInt("id")
        var name = arguments?.getString("name")
        var about = arguments?.getString("about")
        var dateSt = arguments?.getString("dateSt")
        var dateFin = arguments?.getString("dateFin")
        var place = arguments?.getString("place")

        binding = FragmentCertificatesAddBinding.inflate(inflater, container, false)

        if (id != null && name != null &&
            dateSt != null && dateFin != null && place != null
        ) {

            binding.etCertificateName.setText(name)
            binding.etAboutCertificate.setText(about)
                ?: requireActivity().resources.getString(R.string.aboutCertificate)
            binding.etEducationPlace.setText(place)
            binding.etStartDate.setText(dateSt)
            binding.etFinishDate.setText(dateFin)

            binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
        }

        with(binding) {
            btnAdd.setOnClickListener {
                var name = etCertificateName.text.toString()
                var place = etEducationPlace.text.toString()
                var aboutCer = etAboutCertificate.text.toString() ?: null
                var startDate = etStartDate.text.toString()
                var finishDate = etFinishDate.text.toString()

                if (id != null) {
                    update(id, name, aboutCer, place, startDate, finishDate)
                } else {
                    add(name, aboutCer, place, startDate, finishDate)
                }
            }
        }

        return binding.root
    }

    fun update(
        id: Int,
        name: String,
        aboutCer: String? = null,
        place: String,
        startDate: String,
        finishDate: String
    ) {
        if (name.isNotEmpty() && place.isNotEmpty() && startDate.isNotEmpty() && finishDate.isNotEmpty()) {

            var userInfo = Certificates(
                id = id,
                certificateProjectOrAwardName = name,
                aboutCertificate = aboutCer,
                educationPlace = place,
                startDate = startDate,
                finishDate = finishDate
            )

            viewModel.updateData(userInfo)

        } else {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForEmpty)
            )
        }
        isSuccessful()
    }

    fun add(
        name: String,
        aboutCer: String? = null,
        place: String,
        startDate: String,
        finishDate: String
    ) {
        if (name.isNotEmpty() && place.isNotEmpty() && startDate.isNotEmpty() && finishDate.isNotEmpty()) {

            var userInfo = Certificates(
                certificateProjectOrAwardName = name,
                aboutCertificate = aboutCer,
                educationPlace = place,
                startDate = startDate,
                finishDate = finishDate
            )
            viewModel.saveData(userInfo)

        } else {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForEmpty)
            )
        }
        isSuccessful()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(CertificatesFragment())
        }
    }

    fun isSuccessful() {
        observe(viewModel.isSuccessfulMLD) {
            if (it == true)
                message(
                    requireContext(),
                    requireActivity().resources.getString(R.string.messageForSaved)
                )
            replace(CertificatesFragment())
        }
    }
}