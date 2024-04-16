package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.databinding.ItemLanguagesBinding

class LanguageAdapter(
    list: List<Languages>,
    private val onClick: (Languages) -> Unit,
    private val onDeleteClick: (Languages) -> Unit
) : BaseAdapter<Languages, ItemLanguagesBinding>(list, { binding, item ->

    with(binding) {
        tvLanguageName.text = item.languageName

        root.setOnClickListener {
            onClick(item)
        }

        imDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }

}) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemLanguagesBinding {
        return ItemLanguagesBinding.inflate(inflater, parent, false)
    }
}

