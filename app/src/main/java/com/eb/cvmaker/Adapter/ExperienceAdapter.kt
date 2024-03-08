package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.databinding.ItemEducationBinding
import com.eb.cvmaker.databinding.ItemExperienceBinding

class ExperienceAdapter(
    var list: List<Experience>,
    private val onClick: (Experience) -> Unit,
    private val onDeleteClick: (Experience) -> Unit
) : RecyclerView.Adapter<ExperienceAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemExperienceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_list: Experience) {

            with(binding) {
                tvCompanyName.text = _list.companyName
                tvPositionName.text = _list.positionName

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
        var binding = ItemExperienceBinding.inflate(inflater, parent, false)
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