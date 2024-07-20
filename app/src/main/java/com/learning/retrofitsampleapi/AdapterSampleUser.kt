package com.learning.retrofitsampleapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learning.retrofitsampleapi.Models.User

class AdapterSampleUser(val context: Context, val user:List<User>) :
    RecyclerView.Adapter<AdapterSampleUser.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSampleUser.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_user,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterSampleUser.MyViewHolder, position: Int) {
        holder.binding(user[position])
    }

    override fun getItemCount(): Int {
        return user.size
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val userId:TextView = itemView.findViewById(R.id.tvUserId)
        private val userName:TextView = itemView.findViewById(R.id.tvUserName)
        private val userEmail:TextView = itemView.findViewById(R.id.tvUserEmail)
        private val userWebsite:TextView = itemView.findViewById(R.id.tvUserWebsite)

        fun binding(user:User){
            userId.text = user.id.toString()
            userName.text = user.name
            userEmail.text = user.email
            userWebsite.text = user.website
        }
    }
}