package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.Model.AppLanguage
import com.eb.cvmaker.databinding.ItemChooseAppLanguageBinding


class AppLanguageAdapter(
    list: List<AppLanguage>,
    private val onClick: (AppLanguage) -> Unit,
) : BaseAdapter<AppLanguage, ItemChooseAppLanguageBinding>(list, { binding, item ->

    with(binding) {

        tvLang.setText(item.languageName)
        item.flag?.let { image.setImageResource(it) }

        root.setOnClickListener {
            onClick(item)
        }
    }
}) {
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemChooseAppLanguageBinding {
        return ItemChooseAppLanguageBinding.inflate(inflater, parent, false)
    }
}