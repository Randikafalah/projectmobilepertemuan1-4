package com.example.randika_mi1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val contacts: ArrayList<ReadModel.Data>,
    private val listener: OnAdapterListener
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_contact, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = contacts[position]
        holder.tvName.text = data.name
        holder.tvNumber.text = data.number
        holder.itemView.setOnClickListener {
            listener.onUpdate(data)
        }
        holder.imgDelete.setOnClickListener {
            listener.onDelete(data)
        }
    }

    override fun getItemCount() = contacts.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.TextViewName)
        val tvNumber: TextView = view.findViewById(R.id.TextViewNumber)
        val imgDelete: ImageView = view.findViewById(R.id.ImageViewDelete)
    }

    fun setData(data: List<ReadModel.Data>) {
        contacts.clear()
        contacts.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onUpdate(contact: ReadModel.Data)
        fun onDelete(contact: ReadModel.Data)
    }
}
