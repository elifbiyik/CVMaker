package com.eb.cvmaker.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.ImageLoad
import com.eb.cvmaker.databinding.ItemAllTemplatesBinding
import javax.inject.Inject

class HomePageAdapter @Inject constructor(list: List<Uri>) :
    BaseAdapter<Uri, ItemAllTemplatesBinding>(list, { binding, item ->
        with(binding) {
            image.ImageLoad(item)
        }
    }) {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemAllTemplatesBinding {
        return ItemAllTemplatesBinding.inflate(inflater, parent, false)
    }

}