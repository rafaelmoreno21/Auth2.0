package com.test.auth

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_row.view.*

class UsersAdapter(private val users: List<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.user_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=users.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user=users[position]
        d("rafael","phone numer? ${user.phone}")
        holder.firstName.text=user.website
        holder.lastName.text=user.lastName
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val firstName: TextView =itemView.firstName
        val lastName: TextView =itemView.lastName

    }
}
