package com.eb.cvmaker.ui.References

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
import com.eb.cvmaker.Adapter.ReferenceAdapter
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentReferencesBinding
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReferencesFragment : Fragment() {

    private lateinit var binding: FragmentReferencesBinding
    private val viewModel: ReferencesVM by viewModels()
    private lateinit var adapter : ReferenceAdapter
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReferencesBinding.inflate(inflater, container, false)

        lottieAnimationView = binding.lottieAnimated
        lottieAnimationView.setAnimation(R.raw.empty_ghost)
        lottieAnimationView.playAnimation()
        lottieAnimationView.loop(true)

        observe(viewModel.userInfoMLD) {
            if (it != null && it.isNotEmpty()) {

                binding.linearLayout.visibility = View.VISIBLE
                binding.lottieAnimated.visibility = View.GONE

                adapter = ReferenceAdapter(it,
                    {
                        var bundle = Bundle()
                        var fr = ReferencesAddFragment()

                        bundle.putInt("id", it.id)
                        bundle.putString("name", it.name)
                        bundle.putString("surname", it.surname)
                        bundle.putString("email", it.email)
                        bundle.putString("phone", it.phone)
                        bundle.putString("position", it.positionName)

                        fr.arguments = bundle
                        replace(fr)
                    },
                    {
                        var fr = ReferencesFragment()

                        if (it.name != null && it.surname != null && it.email != null && it.phone != null && it.positionName != null) {
                            var item = References(
                                it.id,
                                it.name,
                                it.surname,
                                it.email,
                                it.phone,
                                it.positionName
                            )
                            showDialog(fr, item)
                        }
                    })

                binding.recView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
            }
        }

        binding.btnAddReferences.setOnClickListener {
            replace(ReferencesAddFragment())
        }

        return binding.root

    }

    fun showDialog(fr: Fragment, item: References) {
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