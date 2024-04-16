package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.databinding.ItemExperienceBinding

class ExperienceAdapter(
    list: List<Experience>,
    private val onClick: (Experience) -> Unit,
    private val onDeleteClick: (Experience) -> Unit
) : BaseAdapter<Experience, ItemExperienceBinding>(list, { binding, item ->

    with(binding) {
        tvCompanyName.text = item.companyName
        tvPositionName.text = item.positionName

        root.setOnClickListener {
            onClick(item)
        }

        imDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }

}) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemExperienceBinding {
        return ItemExperienceBinding.inflate(inflater, parent, false)
    }
}