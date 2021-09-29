package com.example.desighkotlin.view.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder (view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(data: Data)
}