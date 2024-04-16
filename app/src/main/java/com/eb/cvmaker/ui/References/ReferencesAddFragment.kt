package com.eb.cvmaker.ui.References

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentReferencesAddBinding
import com.eb.cvmaker.formatPhone
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReferencesAddFragment : Fragment() {

    private lateinit var binding: FragmentReferencesAddBinding
    private val viewModel: ReferencesAddVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var id = arguments?.getInt("id")
        var name = arguments?.getString("name")
        var surname = arguments?.getString("surname")
        var email = arguments?.getString("email")
        var phone = arguments?.getString("phone")
        var position = arguments?.getString("position")

        binding = FragmentReferencesAddBinding.inflate(inflater, container, false)

        binding.etPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher("TR"))
        binding.etPhone.setOnClickListener {
            val formattedNumber = binding.etPhone.text.toString().formatPhone()
            if (formattedNumber != null) {
                binding.etPhone.setText(formattedNumber)
                binding.etPhone.setSelection(binding.etPhone.text.length)
            }
        }

        if (id != null && name != null && surname != null) {
            binding.etReferenceName.setText(name)
            binding.etReferenceSurname.setText(surname)
            binding.etEmail.setText(email)
            binding.etPhone.setText(phone)
            binding.etPosition.setText(position)

            binding.btnAdd.text = requireActivity().resources.getString(R.string.update)
        }

        with(binding) {
            btnAdd.setOnClickListener {
                var name = etReferenceName.text.toString()
                var surname = etReferenceSurname.text.toString()
                var position = etPosition.text.toString()
                var email = etEmail.text.toString()
                var phone = etPhone.text.toString()

                if (name.isNotEmpty() && surname.isNotEmpty()) {
                    if (id != null) {
                        var item = References(id, name, surname, position, email, phone.formatPhone())
                        update(item)
                    } else {
                        var item = References(
                            name = name,
                            surname = surname,
                            positionName = position,
                            email = email,
                            phone = phone.formatPhone()
                        )
                        add(item)
                    }
                }else {
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

    fun update(userInfo : References) {
        viewModel.updateData(userInfo)
        isSuccessful()
    }

    fun add(userInfo : References) {
        viewModel.saveData(userInfo)
        isSuccessful()
    }

    fun isSuccessful() {
        observe(viewModel.isSuccessfulMLD) {
            if (it == true)
                message(
                    requireContext(),
                    requireActivity().resources.getString(R.string.messageForSaved)
                )
            replace(ReferencesFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            replace(ReferencesFragment())
        }
    }
}

