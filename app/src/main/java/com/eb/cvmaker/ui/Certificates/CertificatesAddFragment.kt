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
            ?: requireActivity().resources.getString(R.string.present)
        var place = arguments?.getString("place")

        binding = FragmentCertificatesAddBinding.inflate(inflater, container, false)

        if (id != null && name != null &&
            dateSt != null && dateFin != null && place != null
        ) {

            val startMonth = dateSt.substring(0, dateSt.indexOf('.'))
            val startYear = dateSt.substring(dateSt.indexOf('.') + 1)

            var finishMonth = ""
            var finishYear = ""
            if (!dateFin.isNullOrEmpty()) {
                finishMonth = dateFin.substring(0, dateFin.indexOf('.'))
                finishYear = dateFin.substring(dateFin.indexOf('.') + 1)
            }

            binding.etCertificateName.setText(name)
            binding.etAboutCertificate.setText(about)
                ?: requireActivity().resources.getString(R.string.aboutCertificate)
            binding.etEducationPlace.setText(place)
            binding.etStartDateMonth.setText(startMonth)
            binding.etStartDateYear.setText(startYear)
            binding.etFinishDateMonth.setText(finishMonth)
            binding.etFinishDateYear.setText(finishYear)

            binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
        }

        with(binding) {
            btnAdd.setOnClickListener {
                var name = etCertificateName.text.toString()
                var place = etEducationPlace.text.toString()
                var aboutCer = etAboutCertificate.text.toString() ?: null
                var startDate =
                    etStartDateMonth.text.toString() + "." + etStartDateYear.text.toString()
                var finishDate: String
                if (!etFinishDateMonth.text.isNullOrEmpty() && !etFinishDateYear.text.isNullOrEmpty()) {
                    finishDate =
                        etFinishDateMonth.text.toString() + "." + etFinishDateYear.text.toString()
                } else {
                    finishDate =
                        etFinishDateMonth.text.toString() // Boş bırakıldığında (.) yazmasın diye month'u yazdırdık ama month yazmayack zaten (continue yazacak)
                }

                if (name.isNotEmpty() && place.isNotEmpty() && startDate.isNotEmpty()) {

                    if (id != null) {
                        var item = Certificates(
                            id = id,
                            certificateProjectOrAwardName = name,
                            aboutCertificate = aboutCer,
                            educationPlace = place,
                            startDate = startDate,
                            finishDate = finishDate
                        )
                        update(item)
                    } else {
                        var item = Certificates(
                            certificateProjectOrAwardName = name,
                            aboutCertificate = aboutCer,
                            educationPlace = place,
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

    fun update(userInfo: Certificates) {
        viewModel.updateData(userInfo)
        isSuccessful()

    }

    fun add(userInfo: Certificates) {
        viewModel.saveData(userInfo)
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