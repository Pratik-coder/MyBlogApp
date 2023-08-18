package com.example.cookingapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.R
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.viewmodel.BlogViewModel
import com.example.cookingapp.viewmodelfactory.BlogViewModelFactory


class BlogAdapter(private var context: Context,private var blogList:List<BlogData>): RecyclerView.Adapter<BlogAdapter.MyViewHolder>()
{
    private lateinit var onFavouriteClickListener:OnFavouriteImageClick



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_bloglist,parent,false)
        return MyViewHolder(view)
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val blogData=blogList[position]
        holder.textViewBlogTitle.text=blogData.title
        holder.textViewBlogDescription.text=blogData.description
        holder.imageViewFavourite.setImageResource(if(blogData.isFavourite)R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
        holder.imageViewFavourite.setOnClickListener {
            onFavouriteClickListener.OnFavouriteBlogClick(blogData)
        }
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

   interface OnFavouriteImageClick
   {
     fun OnFavouriteBlogClick(blogData: BlogData)
   }

    fun setOnFavouriteClickListener(listener:OnFavouriteImageClick)
    {
       this.onFavouriteClickListener=listener
    }

  private fun setFavouriteItem(position: Int):BlogData=blogList[position]

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
       val cardViewBlogList:CardView=itemView.findViewById(R.id.card_blogList)
       val linearLayoutBlogList:LinearLayout=itemView.findViewById(R.id.linearLayoutblogList)
       val textViewBlogTitle:TextView=itemView.findViewById(R.id.tv_blogTitle)
       val textViewBlogDescription:TextView=itemView.findViewById(R.id.tv_blogDescription)
       val imageViewFavourite:ImageView=itemView.findViewById(R.id.iv_blogFavourite)
    }
}