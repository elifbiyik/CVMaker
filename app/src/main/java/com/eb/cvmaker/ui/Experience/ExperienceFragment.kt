package com.eb.cvmaker.ui.Experience

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
import com.eb.cvmaker.Adapter.ExperienceAdapter
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentExperienceBinding
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExperienceFragment : Fragment() {

    private lateinit var binding: FragmentExperienceBinding
    private val viewModel: ExperienceVM by viewModels()
    private lateinit var adapter: ExperienceAdapter
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExperienceBinding.inflate(inflater, container, false)

        lottieAnimationView = binding.lottieAnimated
        lottieAnimationView.setAnimation(R.raw.empty_data)
        lottieAnimationView.playAnimation()
        lottieAnimationView.loop(true)

        observe(viewModel.userInfoMLD) {
            if (it != null && it.isNotEmpty()) {

                binding.linearLayout.visibility = View.VISIBLE
                binding.lottieAnimated.visibility = View.GONE

                adapter = ExperienceAdapter(it,
                    {
                        var bundle = Bundle()
                        var fr = ExperienceAddFragment()
                        bundle.putInt("id", it.id)
                        bundle.putString("nameCom", it.companyName)
                        bundle.putString("namePos", it.positionName)
                        bundle.putString("dateSt", it.startDate)
                        bundle.putString("dateFin", it.finishDate)
                        bundle.putString("info", it.infoAboutJob)
                        fr.arguments = bundle
                        replace(fr)
                    },
                    {
                        var fr = ExperienceFragment()
                        var item = Experience(
                            it.id,
                            it.companyName,
                            it.positionName,
                            it.startDate,
                            it.finishDate,
                            it.infoAboutJob
                        )
                        showDialog(fr, item)
                    })
                binding.recView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
            }
        }

        binding.btnAddCompany.setOnClickListener {
            replace(ExperienceAddFragment())
        }

        return binding.root
    }

    fun showDialog(fr: Fragment, item: Experience) {
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