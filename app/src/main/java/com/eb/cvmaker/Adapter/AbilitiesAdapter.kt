package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.databinding.ItemAbilitiesBinding

class AbilitiesAdapter(
    list: List<Abilities>,
    private val onClick: (Abilities) -> Unit,
    private val onDeleteClick: (Abilities) -> Unit
) : BaseAdapter<Abilities, ItemAbilitiesBinding>(list, { binding, item ->

    with(binding) {
        tvAbilities.text = item.abilitiesName

        root.setOnClickListener {
            onClick(item)
        }

        imDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }
}) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemAbilitiesBinding {
        return ItemAbilitiesBinding.inflate(inflater, parent, false)
    }

}