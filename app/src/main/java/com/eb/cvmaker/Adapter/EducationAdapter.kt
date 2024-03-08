package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.databinding.ItemEducationBinding

class EducationAdapter(
    var list: List<Education>,
    private val onClick: (Education) -> Unit,
    private val onDeleteClick: (Education) -> Unit
) :
    RecyclerView.Adapter<EducationAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemEducationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_list: Education) {

            with(binding) {
                tvSchoolName.text = _list.schoolName
                tvDepartmentName.text = _list.departmentName

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
        var binding = ItemEducationBinding.inflate(inflater, parent, false)
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