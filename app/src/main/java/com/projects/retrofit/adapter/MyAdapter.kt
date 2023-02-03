package com.projects.retrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projects.retrofit.R
import com.projects.retrofit.databinding.ActivityMainBinding
import com.projects.retrofit.databinding.RowLayoutBinding
import com.projects.retrofit.model.Post

class MyAdapter(private val clickListener: PostListener): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    class PostListener(val clickListener: (post:Post) -> Unit) {
        fun onClick(post: Post) = clickListener(post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.userId_txt).text =
            myList[position].userId.toString()
        holder.itemView.findViewById<TextView>(R.id.id_txt).text = myList[position].id.toString()
        holder.itemView.findViewById<TextView>(R.id.title_txt).text = myList[position].title
        holder.itemView.findViewById<TextView>(R.id.body_txt).text = myList[position].body

        val currentPost = myList[position]
        holder.also {
            it.itemView.setOnClickListener {
                clickListener.onClick(currentPost)
            }
        }

    }


    fun setData(newList: List<Post>) {
        myList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myList.size
    }


}