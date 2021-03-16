package com.aspiration.interview.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aspiration.interview.R
import com.aspiration.interview.data.models.ListItem

class ListItemAdapter : RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {

    private var items: List<ListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemAdapter.ViewHolder, position: Int) {
        val item = items[position]
        if(item.type == ListItem.POST) {
            holder.title.text = item.post?.title ?: "Error"
            holder.desc.text = item.post?.body ?: "Error"
            holder.desc.visibility = View.VISIBLE
        } else {
            holder.title.text = item.date
            holder.desc.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list: List<ListItem>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val desc: TextView

        init {
            title = view.findViewById(R.id.titleTV)
            desc = view.findViewById(R.id.descTV)
        }
    }

}