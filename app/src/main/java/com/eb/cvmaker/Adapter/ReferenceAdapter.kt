package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.databinding.ItemReferencesBinding

class ReferenceAdapter(
    list: List<References>,
    private val onClick: (References) -> Unit,
    private val onDeleteClick: (References) -> Unit
) : BaseAdapter<References, ItemReferencesBinding>(list, { binding, item ->

    with(binding) {
        tvName.text = item.name
        tvSurname.text = item.surname

        root.setOnClickListener {
            onClick(item)
        }

        imDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }
}) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemReferencesBinding {
        return ItemReferencesBinding.inflate(inflater, parent, false)
    }
}