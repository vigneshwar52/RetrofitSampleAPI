package com.learning.retrofitsampleapi.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learning.retrofitsampleapi.Models.Post
import com.learning.retrofitsampleapi.R

class PostAdapter(
    private val context:Context,
    private var posts:List<Post>,
    private val itemClickListener:ItemClickListener
) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(post: Post)
    }
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
            tvId.text = "Post #${post.id}"
            tvTitle.text = post.title
            tvBody.text = post.body
            itemView.setOnClickListener {
                itemClickListener.onItemClick(post)
            }
        }
    }
}