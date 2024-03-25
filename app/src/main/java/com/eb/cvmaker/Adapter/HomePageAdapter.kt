package com.eb.cvmaker.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.cvmaker.ImageLoad
import com.eb.cvmaker.databinding.ItemAllTemplatesBinding
import javax.inject.Inject

class HomePageAdapter @Inject constructor(var list : List<Uri>) : RecyclerView.Adapter<HomePageAdapter.ViewHolder> () {

   inner class ViewHolder (var binding: ItemAllTemplatesBinding) : RecyclerView.ViewHolder(binding.root){

       fun bind(imageUri : Uri) {
           with(binding) {
               image.ImageLoad(imageUri)
           }
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemAllTemplatesBinding.inflate(inflater, parent, false)
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