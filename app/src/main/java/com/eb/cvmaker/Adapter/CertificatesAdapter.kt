package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.databinding.ItemCertificateBinding

class CertificatesAdapter(
    list: List<Certificates>,
    private val onClick: (Certificates) -> Unit,
    private val onDeleteClick: (Certificates) -> Unit
) : BaseAdapter<Certificates, ItemCertificateBinding>(list, { binding, item ->

    with(binding) {
        tvCertificateName.text = item.certificateProjectOrAwardName

        root.setOnClickListener {
            onClick(item)
        }

        imDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }
}) {
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemCertificateBinding {
        return ItemCertificateBinding.inflate(inflater, parent, false)
    }
}