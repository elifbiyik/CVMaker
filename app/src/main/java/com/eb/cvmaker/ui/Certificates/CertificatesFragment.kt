package com.eb.cvmaker.ui.Certificates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.eb.cvmaker.Adapter.CertificatesAdapter
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentCertificatesBinding
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertificatesFragment : Fragment() {

    private lateinit var binding: FragmentCertificatesBinding
    private val viewModel: CertificatesVM by viewModels()
    private lateinit var adapter: CertificatesAdapter
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCertificatesBinding.inflate(inflater, container, false)

        lottieAnimationView = binding.lottieAnimated
        lottieAnimationView.playAnimation()
        lottieAnimationView.loop(true)

        observe(viewModel.userInfoMLD) {
            if (it != null && it.isNotEmpty()) {

                binding.linearLayout.visibility = View.VISIBLE
                binding.lottieAnimated.visibility = View.GONE

                adapter = CertificatesAdapter(it, {
                    var bundle = Bundle()
                    var fr = CertificatesAddFragment()

                    bundle.putInt("id", it.id)
                    bundle.putString("name", it.certificateProjectOrAwardName)
                    bundle.putString("about", it.aboutCertificate)
                    bundle.putString("place", it.educationPlace)
                    bundle.putString("dateSt", it.startDate)
                    bundle.putString("dateFin", it.finishDate)

                    fr.arguments = bundle
                    replace(fr)
                },
                    {
                        var fr = CertificatesAddFragment()

                        var item = Certificates(
                            it.id,
                            it.certificateProjectOrAwardName,
                            it.aboutCertificate,
                            it.startDate,
                            it.finishDate,
                            it.educationPlace
                        )
                        showDialog(fr, item)
                    })

                binding.recView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
            }
        }

        binding.btnAddCertificates.setOnClickListener {
            replace(CertificatesAddFragment())
        }

        return binding.root
    }

    fun showDialog(fr: Fragment, item: Certificates) {
        SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
            .setTitleText(requireContext().resources.getString(R.string.titleForAlert))
            .setContentText(requireActivity().resources.getString(R.string.messageForAlert))
            .setConfirmText(requireActivity().resources.getString(R.string.buttonForAlert))
            .setConfirmClickListener { sDialog ->
                sDialog
                    .setTitleText(requireActivity().resources.getString(R.string.titleForDelete))
                    .setContentText(requireActivity().resources.getString(R.string.messageForDelete))
                    .setConfirmText(requireActivity().resources.getString(R.string.buttonForOK))
                    .setConfirmClickListener {
                        viewModel.deleteData(item)
                        sDialog.dismissWithAnimation()
                        replace(fr)
                    }
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(InformationsFragment())
        }
    }

}