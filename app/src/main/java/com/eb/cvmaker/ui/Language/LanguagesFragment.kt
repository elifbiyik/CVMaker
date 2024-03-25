package com.eb.cvmaker.ui.Language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.eb.cvmaker.Adapter.LanguageAdapter
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentLanguagesBinding
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguagesFragment : Fragment() {

    private lateinit var binding: FragmentLanguagesBinding
    private val viewModel: LanguageVM by viewModels()
    private lateinit var adapter: LanguageAdapter
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLanguagesBinding.inflate(inflater, container, false)

        lottieAnimationView = binding.lottieAnimated
        lottieAnimationView.playAnimation()
        lottieAnimationView.loop(true)

        observe(viewModel.userInfoMLD) {
            if (it != null && it.isNotEmpty()) {

                binding.linearLayout.visibility = View.VISIBLE
                binding.lottieAnimated.visibility = View.GONE

                adapter = LanguageAdapter(
                    it,
                    {
                        var bundle = Bundle()
                        var fr = LanguageAddFragment()
                        bundle.putInt("id", it.id)
                        bundle.putString("name", it.languageName)
                        bundle.putString("level", it.level)
                        fr.arguments = bundle
                        replace(fr)
                    },
                    {
                        var fr = LanguagesFragment()
                        var item = Languages(it.id, it.languageName, it.level)
                        showDialog(fr, item)
                    }
                )
                binding.recView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
            }
        }

        binding.btnAddLanguage.setOnClickListener {
            replace(LanguageAddFragment())
        }

        return binding.root
    }

    fun showDialog(fr: Fragment, item: Languages) {
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

