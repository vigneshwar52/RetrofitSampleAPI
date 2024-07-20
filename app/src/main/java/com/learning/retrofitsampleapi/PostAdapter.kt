package com.learning.retrofitsampleapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learning.retrofitsampleapi.Models.Post

class PostAdapter(val context:Context,var posts:List<Post>) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_view,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun updatePosts(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvId:TextView = itemView.findViewById(R.id.tvId)
        private val tvTitle:TextView = itemView.findViewById(R.id.tvTitle)
        private val tvBody:TextView = itemView.findViewById(R.id.tvBlogBody)

        fun bind(post:Post){
            tvId.text = "Post "+post.id.toString()
            tvTitle.text = post.title
            tvBody.text = post.body
        }
    }
}