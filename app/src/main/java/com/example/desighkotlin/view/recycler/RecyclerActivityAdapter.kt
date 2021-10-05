package com.example.desighkotlin.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desighkotlin.databinding.ActivityRecyclerDealsHeaderBinding
import com.example.desighkotlin.databinding.ActivityRecyclerDealsItemBinding

class RecyclerActivityAdapter(
    private var onListItemClickListener: OnItemClickListener,
    private var data: MutableList<Pair<Data, Boolean>>
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
                HeaderViewHolder(binding.root)
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
                holder.bind(data[position])
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
        override fun bind(pair: Pair<Data,Boolean>){
            ActivityRecyclerDealsItemBinding.bind(itemView).apply {
                descriptionTextView.text = pair.first.someDescription
                itemDealList.setOnClickListener {
                    onListItemClickListener.onItemClick(pair.first)
                }
                addItemImageView.setOnClickListener { addItem() }
                removeItemImageView.setOnClickListener { removeItem() }
                moveItemUp.setOnClickListener { moveUp() }
                moveItemDown.setOnClickListener { moveDown() }
                descriptionTextView.setOnClickListener { toggleText() }
                dealDescriptionTextView.visibility =  if(pair.second) View.VISIBLE else View.GONE
            }
        }


        private fun toggleText(){
            data[layoutPosition] = data[layoutPosition].let{
                it.first to !it.second
            }
            notifyItemChanged(layoutPosition)
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
            data.add(layoutPosition, Pair(generateItem(), false))
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }


    fun appendItem() {
        data.add(Pair(generateItem(),false))
        notifyItemInserted(itemCount - 1)
    }


    private fun generateItem() = Data("Deal", "")

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(pair: Pair<Data,Boolean>) {
            ActivityRecyclerDealsHeaderBinding.bind(itemView).apply {
                root.setOnClickListener {
                    onListItemClickListener.onItemClick(pair.first)
                }
            }
        }
    }

    companion object {
        private const val TYPE_DEAL = 0
        private const val TYPE_HEADER = 1
    }


}