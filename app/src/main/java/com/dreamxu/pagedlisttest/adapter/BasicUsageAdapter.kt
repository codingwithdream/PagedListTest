package com.dreamxu.pagedlisttest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dreamxu.pagedlisttest.R
import com.dreamxu.pagedlisttest.room.User

class BasicUsageAdapter: PagedListAdapter<User, BasicUsageAdapter.BasicUsageViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicUsageViewHolder {
        return BasicUsageViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BasicUsageViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    class BasicUsageViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)) {
        private val lastName = itemView.findViewById<TextView>(R.id.user_name)
        var user: User? = null

        /**
         * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
         * ViewHolder when Item is loaded.
         */
        fun bindData(user: User?) {
            this.user = user
            val userName = "${user?.firstName} ${user?.lastName}"
            lastName.text = userName
        }
    }
}
