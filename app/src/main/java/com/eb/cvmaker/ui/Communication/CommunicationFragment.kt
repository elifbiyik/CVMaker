package com.eb.cvmaker.ui.Communication

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.FragmentCommunicationBinding
import com.eb.cvmaker.formatPhone
import com.eb.cvmaker.message
import com.eb.cvmaker.observe
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommunicationFragment : Fragment() {

    private lateinit var binding: FragmentCommunicationBinding
    private val viewModel: CommunicationVM by viewModels()
    private val REQUEST_IMAGE_GALLERY = 1
    private var imageUri: Uri? = null // Fotoğrafın adresi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunicationBinding.inflate(inflater, container, false)


        binding.etPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher("TR"))
        binding.etPhone.setOnClickListener {
            val formattedNumber = binding.etPhone.text.toString().formatPhone()
            if (formattedNumber != null) {
                binding.etPhone.setText(formattedNumber)
                binding.etPhone.setSelection(binding.etPhone.text.length)
            }
        }

        binding.imProfile.setOnClickListener {
            userProfile()
        }

        observeDelete()
        observeViewModel()

        return binding.root
    }

    private fun observeDelete() {
        binding.imDelete.setOnClickListener {
            observe(viewModel.userInfoMLD) {
                if (!it.isNullOrEmpty()) {
                    delete(it[0])
                    getUserHintTexts()
                    getUserTexts()
                    replace(InformationsFragment())
                }
            }
        }
    }

    private fun observeViewModel() {
        observe(viewModel.userInfoMLD) {
            if (!it.isNullOrEmpty()) {
                val user = it[0]
                getUserInfo(user)

                binding.btnApprove.setOnClickListener {
                    if (!binding.etUsername.text.isNullOrEmpty() && !binding.etSurname.text.isNullOrEmpty()) {
                        update(getUserTexts(user.image).copy(id = user.id))
                    } else {
                        message(requireContext(), requireActivity().resources.getString(R.string.emptyNameAndSurname)
                        )
                    }
                    replace(InformationsFragment())
                }
            } else {
                getUserHintTexts()
                binding.btnApprove.setOnClickListener {

                    if (!binding.etUsername.text.isNullOrEmpty() && !binding.etSurname.text.isNullOrEmpty()) {
                        add(getUserTexts())
                    } else {
                        message(requireContext(), requireActivity().resources.getString(R.string.emptyNameAndSurname)
                        )
                    }
                    replace(InformationsFragment())
                }
            }
        }
    }
    
    // Image verilemsinin sebebi -> case : isim ve görsel kısımalrını doldur -> save tıkla -> geri gel ->
    //                                      Communication fragmentı aç -> soyisim gir -> geri gel ->
    //                                      Communication fragmentı aç -> Resim gelmiyor. Galeriden seçilmediği için imageUri null geliyor. ve imageView'de göstermiyor.

    fun getUserTexts(userImage: String? = null): Communication {
        return Communication(
            image = imageUri?.toString() ?: userImage, //if(!imageUri.toString().isNullOrEmpty()) imageUri.toString() else userImage ,
            name = if (binding.etUsername.text.isNotBlank()) binding.etUsername.text.toString() else null,
            surname = if (binding.etSurname.text.isNotBlank()) binding.etSurname.text.toString() else null,
            email = if (binding.etMail.text.isNotBlank()) binding.etMail.text.toString() else null,
            phone = if (binding.etPhone.text?.isNotBlank() == true) binding.etPhone.text.toString()
                .formatPhone() else null,
            job = if (binding.etJob.text.isNotBlank()) binding.etJob.text.toString() else null,
            aboutMe = if (binding.etAboutMe.text.isNotBlank()) binding.etAboutMe.text.toString() else null,
            address = if (binding.etAddress.text.isNotBlank()) binding.etAddress.text.toString() else null,
            birtday = if (binding.etBirtday.text.isNotBlank()) binding.etBirtday.text.toString() else null,
            gender = if (binding.etGender.text.isNotBlank()) binding.etGender.text.toString() else null,
            drivingLicence = if (binding.etDrivingLicence.text.isNotBlank()) binding.etDrivingLicence.text.toString() else null,
            military = if (binding.etMilitary.text.isNotBlank()) binding.etMilitary.text.toString() else null
        )
    }

    fun getUserHintTexts() {
        with(binding) {
            etUsername.hint = requireActivity().resources.getString(R.string.name)
            etSurname.hint =
                requireActivity().resources.getString(R.string.surname)
            etMail.hint = requireActivity().resources.getString(R.string.email)
            etPhone.hint = requireActivity().resources.getString(R.string.phone)
            etJob.hint = requireActivity().resources.getString(R.string.job)
            etAboutMe.hint = requireActivity().resources.getString(R.string.aboutMe)
            etAddress.hint = requireActivity().resources.getString(R.string.address)
            etGender.hint = requireActivity().resources.getString(R.string.gender)
            etBirtday.hint = requireActivity().resources.getString(R.string.birtday)
            etGender.hint = requireActivity().resources.getString(R.string.gender)
            etDrivingLicence.hint =
                requireActivity().resources.getString(R.string.drivingLicence)
            etMilitary.hint = requireActivity().resources.getString(R.string.military)
        }
    }

    fun profiles(image : String) {
        Glide.with(requireContext())
            .load(image)
            .into(binding.imProfile)
    }


    fun getUserInfo(userInfo: Communication) {
        with(binding) {

            if (!userInfo.image.isNullOrEmpty()) {
              profiles(userInfo.image!!)
            } /*else {
                profiles(R.drawable.communication)
            }*/

            if (!userInfo.name.isNullOrEmpty()) {
                etUsername.setText(userInfo.name)
            } else {
                binding.etUsername.hint = requireActivity().resources.getString(R.string.name)
            }

            if (!userInfo.surname.isNullOrEmpty()) {
                binding.etSurname.setText(userInfo.surname)
            } else {
                binding.etSurname.hint = requireActivity().resources.getString(R.string.surname)
            }

            if (!userInfo.job.isNullOrEmpty()) {
                binding.etJob.setText(userInfo.job)
            } else {
                binding.etJob.hint = requireActivity().resources.getString(R.string.job)
            }

            if (!userInfo.email.isNullOrEmpty()) {
                binding.etMail.setText(userInfo.email)
            } else {
                binding.etMail.hint = requireActivity().resources.getString(R.string.email)
            }

            if (!userInfo.phone.isNullOrEmpty()) {
                binding.etPhone.setText(userInfo.phone.formatPhone())
            } else {
                binding.etPhone.hint = requireActivity().resources.getString(R.string.phone)
            }

            if (!userInfo.aboutMe.isNullOrEmpty()) {
                binding.etAboutMe.setText(userInfo.aboutMe)
            } else {
                binding.etAboutMe.hint = requireActivity().resources.getString(R.string.aboutMe)
            }

            if (!userInfo.address.isNullOrEmpty()) {
                binding.etAddress.setText(userInfo.address)
            } else {
                binding.etAddress.hint = requireActivity().resources.getString(R.string.address)
            }

            if (!userInfo.birtday.isNullOrEmpty()) {
                binding.etBirtday.setText(userInfo.birtday)
            } else {
                binding.etBirtday.hint = requireActivity().resources.getString(R.string.birtday)
            }

            if (!userInfo.gender.isNullOrEmpty()) {
                binding.etGender.setText(userInfo.surname)
            } else {
                binding.etGender.hint = requireActivity().resources.getString(R.string.gender)
            }

            if (!userInfo.drivingLicence.isNullOrEmpty()) {
                binding.etDrivingLicence.setText(userInfo.drivingLicence)
            } else {
                binding.etDrivingLicence.hint = requireActivity().resources.getString(R.string.drivingLicence)
            }

            if (!userInfo.military.isNullOrEmpty()) {
                binding.etMilitary.setText(userInfo.military)
            } else {
                binding.etMilitary.hint = requireActivity().resources.getString(R.string.military)
            }
        }
    }

    fun add(userInfo: Communication) {
        viewModel.saveData(userInfo)
        isSuccessful()
    }

    fun update(userInfo: Communication) {
        viewModel.updateData(userInfo)
        isSuccessful()
    }

    fun delete(userInfo: Communication) {
        viewModel.deleteData(userInfo)
        isSuccessful()
    }

    fun isSuccessful() {
        observe(viewModel.isSuccessfulMLD) {
            if (it == true)
                return@observe message(
                    requireContext(),
                    requireActivity().resources.getString(R.string.messageForSaved)
                )
        }
    }

    private fun userProfile() {
        val intent = Intent(Intent.ACTION_PICK) //ACTION_GET_CONTENT
        intent.type = "image/*" //"*/*"  // Tüm dosya türlerini seçmek için
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                binding.imProfile.setImageURI(uri)
                imageUri = uri!!

                val filePath = getRealPathFromUri(uri)

                var sharedPreferences =
                    requireContext().getSharedPreferences("userImage", AppCompatActivity.MODE_PRIVATE)
                var editor = sharedPreferences.edit()
                editor.putString("imagePath", filePath)
                editor.apply()

            }
        }
    }

    private fun getRealPathFromUri(uri: Uri): String? {
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val index = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            val filePath = it.getString(index)
            it.close()
            return filePath
        }
        return null
    }

    fun userProfilee() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Image")
        builder.setMessage("Choose your option")

        builder.setPositiveButton("Gallery") { dialog, which ->
            dialog.dismiss()

            val intent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY)

        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    // ACTION_PICK -> belirli bir içerik türünü ve belirli bir veri türünü seçmek için kullanıl
    // MediaStore.Images.Media.EXTERNAL_CONTENT_URI -> Galeriden seçmek için
    // startActivityForResult -> Galeri seçiminin sonucunu alırız. (Uri bilgsini)

 /*   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_GALLERY -> {
                    val selectedImageUri: Uri? = data?.data  //Seçilen resmin urisi alınır
                    binding.imProfile.setImageURI(selectedImageUri)
                    imageUri = selectedImageUri!!

                }
            }
        }
    }

*/

}






