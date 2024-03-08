package com.eb.cvmaker.ui.References

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentReferencesAddBinding
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

        if (id != null && name != null && surname != null &&
            email != null && phone != null && position != null
        ) {

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

                if (id != null) {
                    update(id, name, surname, position, email, phone)
                } else {
                    add(name, surname, position, email, phone)
                }
            }
        }

        return binding.root


    }

    fun update(
        id: Int,
        name: String,
        surname: String,
        position: String,
        email: String,
        phone: String
    ) {
        if (name.isNotEmpty() && surname.isNotEmpty() && position.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {

            var userInfo = References(
                id = id,
                name = name,
                surname = surname,
                positionName = position,
                email = email,
                phone = phone
            )

            viewModel.updateData(userInfo)

        } else {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForEmpty)
            )
        }

        observe(viewModel.isSuccessfulMLD) {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForSaved)
            )
            replace(ReferencesFragment())
        }
    }

    fun add(name: String, surname: String, position: String, email: String, phone: String) {

        if (name.isNotEmpty() && surname.isNotEmpty() && position.isNotEmpty() && position.isNotEmpty() && phone.isNotEmpty()) {

            var userInfo = References(
                name = name,
                surname = surname,
                positionName = position,
                email = email,
                phone = phone
            )
            viewModel.saveData(userInfo)

        } else {
            message(
                requireContext(),
                requireActivity().resources.getString(R.string.messageForEmpty)
            )
        }

        observe(viewModel.isSuccessfulMLD) {
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

