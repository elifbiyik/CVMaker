package com.eb.cvmaker.ui.Abilities

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
import com.eb.cvmaker.Adapter.AbilitiesAdapter
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentAbilitiesBinding
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbilitiesFragment : Fragment() {

    private lateinit var binding: FragmentAbilitiesBinding
    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var adapter: AbilitiesAdapter
    private val viewModel: AbilitiesVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAbilitiesBinding.inflate(inflater, container, false)

        lottieAnimationView = binding.lottieAnimated
        lottieAnimationView.playAnimation()
        lottieAnimationView.loop(true)

        observe(viewModel.userInfoMLD) {
            if (it != null && it.isNotEmpty()) {

                binding.linearLayout.visibility = View.VISIBLE
                binding.lottieAnimated.visibility = View.GONE

                adapter = AbilitiesAdapter(
                    it,
                    {
                        var bundle = Bundle()
                        var fr = AbilitiesAddFragment()
                        bundle.putInt("id", it.id)
                        bundle.putString("name", it.abilitiesName)
                        bundle.putString("level", it.level)
                        fr.arguments = bundle
                        replace(fr)
                    },
                    {
                        var fr = AbilitiesFragment()
                        var item = Abilities(it.id, it.abilitiesName, it.level)
                        showDialog(fr, item)
                    }
                )
                binding.recView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
            }
        }

        with(binding) {
            btnAddAbilities.setOnClickListener {
                replace(AbilitiesAddFragment())
            }
        }

        return binding.root
    }

    fun showDialog(fr: Fragment, item: Abilities) {
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