package com.eb.cvmaker.ui.Education

import android.annotation.SuppressLint
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
import com.eb.cvmaker.Adapter.EducationAdapter
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentEducationBinding
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EducationFragment : Fragment() {

    private lateinit var binding: FragmentEducationBinding
    private val viewModel: EducationVM by viewModels()
    private lateinit var adapter: EducationAdapter
    private lateinit var lottieAnimationView: LottieAnimationView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEducationBinding.inflate(inflater, container, false)

        lottieAnimationView = binding.lottieAnimated
        lottieAnimationView.setAnimation(R.raw.empty_ghost)
        lottieAnimationView.playAnimation()
        lottieAnimationView.loop(true)

        observe(viewModel.userInfoMLD) {
            if (it != null && it.isNotEmpty()) {

                binding.linearLayout.visibility = View.VISIBLE
                binding.lottieAnimated.visibility = View.GONE

                adapter = EducationAdapter(it,
                    {
                        var bundle = Bundle()
                        var fr = EducationAddFragment()

                        bundle.putInt("id", it.id)
                        bundle.putString("nameDep", it.departmentName)
                        bundle.putString("nameSch", it.schoolName)
                        bundle.putString("dateSt", it.startDate)
                        bundle.putString("dateFin", it.finishDate)
                        bundle.putString("gano", it.gano)


                        /*      var item = Education(
                                  it.id,
                                  it.schoolName,
                                  it.departmentName,
                                  it.startDate,
                                  it.finishDate,
                                  it.gano
                              )
                              bundle.putParcelable("education", item as Parcelable)
      */

                        fr.arguments = bundle
                        replace(fr)
                    },
                    {
                        var fr = EducationFragment()

                        if (it.departmentName != null && it.schoolName != null && it.startDate != null && it.finishDate != null && it.gano != null) {
                            var item = Education(
                                it.id,
                                it.departmentName,
                                it.schoolName,
                                it.startDate,
                                it.finishDate,
                                it.gano
                            )
                            showDialog(fr, item)
                        }
                    })

                binding.recView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recView.adapter = adapter
            }
        }

        binding.btnAddEducation.setOnClickListener {
            replace(EducationAddFragment())
        }

        return binding.root
    }

    fun showDialog(fr: Fragment, item: Education) {
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
