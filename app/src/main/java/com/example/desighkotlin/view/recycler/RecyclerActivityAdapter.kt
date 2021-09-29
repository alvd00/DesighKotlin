package com.example.desighkotlin.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desighkotlin.databinding.ActivityRecyclerDealsHeaderBinding
import com.example.desighkotlin.databinding.ActivityRecyclerDealsItemBinding

class RecyclerActivityAdapter(
    private var onListItemClickListener: OnItemClickListener,
    private var data: MutableList<Data>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_DEAL -> {
                val binding: ActivityRecyclerDealsItemBinding =
                    ActivityRecyclerDealsItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                DealViewHolder(binding.root)
            }
            else -> {
                val binding: ActivityRecyclerDealsHeaderBinding =
                    ActivityRecyclerDealsHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                DealViewHolder(binding.root)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return TYPE_HEADER
        return TYPE_DEAL
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_DEAL -> {
                (holder as DealViewHolder).bind(data[position])
            }
            //Если добавляю, то вылетает, почему? FIXME
//            TYPE_HEADER->{
//                (holder as HeaderViewHolder).bind(data[position])
//            }
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class DealViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ActivityRecyclerDealsItemBinding.bind(itemView).apply {
                descriptionTextView.text = data.someDescription
                itemDealList.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
                addItemImageView.setOnClickListener { addItem() }
                removeItemImageView.setOnClickListener { removeItem() }
                moveItemUp.setOnClickListener { moveUp() }
                moveItemDown.setOnClickListener { moveDown() }
            }
        }


        private fun moveUp() {
            layoutPosition.takeIf { it > 1 }?.also {
                data.removeAt(it).apply {
                    data.add(it - 1, this)
                }
                notifyItemMoved(it, it - 1)
            }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < itemCount - 1 }?.also {
                data.removeAt(it).apply {
                    data.add(it + 1, this)
                }
                notifyItemMoved(it, it + 1)
            }
        }

        private fun addItem() {
            data.add(layoutPosition, generateItem())
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }


    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }


    private fun generateItem() = Data("Deal", "")

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ActivityRecyclerDealsHeaderBinding.bind(itemView).apply {
                root.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    companion object {
        private const val TYPE_DEAL = 0
        private const val TYPE_HEADER = 1
    }


}