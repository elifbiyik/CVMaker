package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.databinding.ItemCertificateBinding

class CertificatesAdapter (var list : List<Certificates>,
                           private val onClick: (Certificates) -> Unit,
                           private val onDeleteClick: (Certificates) -> Unit) : RecyclerView.Adapter<CertificatesAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemCertificateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(_list: Certificates) {

            with(binding){
                tvCertificateName.text = _list.certificateProjectOrAwardName

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
        var binding = ItemCertificateBinding.inflate(inflater, parent, false)
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