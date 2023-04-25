package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val listener: itemclicked) : RecyclerView.Adapter<Newsviewholder>() {
    var items = mutableListOf<FinalData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Newsviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val viewHolder = Newsviewholder(view)
        view.setOnClickListener {
            listener.onitemclicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Newsviewholder, position: Int) {
        val curitem = items[position]
        curitem?.let {
            holder.titleview.text = it.title
            Glide.with(holder.imageview.context).load(it.urlToImage).into(holder.imageview)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: List<FinalData>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}

class Newsviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleview: TextView = itemView.findViewById(R.id.title)
    val imageview: ImageView = itemView.findViewById(R.id.image)

}

interface itemclicked {
    fun onitemclicked(item: FinalData)
}