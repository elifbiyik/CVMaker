package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.databinding.ItemLanguagesBinding

class LanguageAdapter(
    var list: List<Languages>,
    private val onClick: (Languages) -> Unit,
    private val onDeleteClick: (Languages) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemLanguagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_list: Languages) {

            with(binding) {
                tvLanguageName.text = _list.languageName

                root.setOnClickListener {
                    onClick(_list)
                }

                imDelete.setOnClickListener {
                    onDeleteClick(_list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemLanguagesBinding.inflate(inflater, parent, false)
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