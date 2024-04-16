package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.databinding.ItemEducationBinding

class EducationAdapter(
    list: List<Education>,
    private val onClick: (Education) -> Unit,
    private val onDeleteClick: (Education) -> Unit
) : BaseAdapter<Education, ItemEducationBinding>(list, { binding, item ->
    with(binding) {
        tvSchoolName.text = item.schoolName
        tvDepartmentName.text = item.departmentName

        root.setOnClickListener {
            onClick(item)
        }

        imDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }
}) {
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemEducationBinding {
        return ItemEducationBinding.inflate(inflater, parent, false)
    }

}