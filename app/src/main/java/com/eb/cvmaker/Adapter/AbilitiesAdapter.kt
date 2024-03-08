package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.databinding.ItemAbilitiesBinding

class AbilitiesAdapter(
    var list: List<Abilities>,
    private val onClick: (Abilities) -> Unit,
    private val onDeleteClick: (Abilities) -> Unit
) : RecyclerView.Adapter<AbilitiesAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemAbilitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_list: Abilities) {

            with(binding) {
                tvAbilities.text = _list.abilitiesName

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
        var binding = ItemAbilitiesBinding.inflate(inflater, parent, false)
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