package com.eb.cvmaker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseAdapter<T, VB : ViewBinding>(
    var list: List<T>,
    private val bind: (VB, T) -> Unit
) : RecyclerView.Adapter<BaseAdapter<T, VB>.ViewHolder>() {

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent)
        return ViewHolder(binding)
    }

    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        bind(holder.binding, item)
    }
}