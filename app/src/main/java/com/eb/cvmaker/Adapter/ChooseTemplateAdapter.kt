package com.eb.cvmaker.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.ImageLoad
import com.eb.cvmaker.R
import com.eb.cvmaker.databinding.ItemChooseTemplateBinding

class ChooseTemplateAdapter(
    var list: ArrayList<Uri>,
    private val onClick: (Uri, Int) -> Unit,
) :
    RecyclerView.Adapter<ChooseTemplateAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemChooseTemplateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUri: Uri) {

            with(binding) {

                image.ImageLoad(imageUri)

                image.setOnClickListener {
                    onClick(imageUri, absoluteAdapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemChooseTemplateBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var _list = list[position]
        holder.bind(_list)
    }

}