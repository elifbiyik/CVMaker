package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.databinding.ItemReferencesBinding

class ReferenceAdapter(
    var list: List<References>, private val onClick: (References) -> Unit,
    private val onDeleteClick: (References) -> Unit
) : RecyclerView.Adapter<ReferenceAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemReferencesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_list: References) {

            with(binding) {
                tvName.text = _list.name
                tvSurname.text = _list.surname

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
        var binding = ItemReferencesBinding.inflate(inflater, parent, false)
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