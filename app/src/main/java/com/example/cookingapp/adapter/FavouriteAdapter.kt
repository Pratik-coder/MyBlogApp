package com.example.cookingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.R
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData

class FavouriteAdapter(private var context: Context,private var favouriteBlogList: MutableList<BlogData>) :RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>()
{
    private lateinit var onDeleteFavouriteBlogClick: OnDeleteFavouriteBlogClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_favouritebloglist,parent,false)
       return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val favouriteBlogData=favouriteBlogList[position]
        holder.textViewFavouriteBlogTitle.text =favouriteBlogData.title
        holder.textViewFavouriteBlogPlace.text =favouriteBlogData.place
        holder.imageViewDelete.setOnClickListener {
            onDeleteFavouriteBlogClick.deleteFavouriteFromList(favouriteBlogData.id)

        }
    }

    fun ClearAllBlog()
    {
        favouriteBlogList.clear()
        notifyDataSetChanged()
    }

    /*fun updateFavBlogList(blogData: BlogData)
    {
        if (favouriteBlogList.isNotEmpty() *//*&& favouriteBlogList.contains(blogData)*//*)
        {
           // (favouriteBlogList as MutableList<BlogData>).remove(blogData)
            favouriteBlogList as MutableList<BlogData>
            (favouriteBlogList as MutableList<BlogData>).add(blogData)
            (favouriteBlogList as MutableList<BlogData>).clear()
            notifyDataSetChanged()
        }
    }*/

    override fun getItemCount(): Int
    {
      return favouriteBlogList.size
    }


    interface OnDeleteFavouriteBlogClick
    {
        fun deleteFavouriteFromList(favBlogId:Int)
    }

    fun setOnFavouriteIconDelete(listener:OnDeleteFavouriteBlogClick)
    {
        this.onDeleteFavouriteBlogClick=listener
    }


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
       val cardViewFavourite:CardView=itemView.findViewById(R.id.cardViewFavouriteBlog)
       val textViewFavouriteBlogTitle:TextView=itemView.findViewById(R.id.tv_favouriteblogTitle)
       val textViewFavouriteBlogPlace:TextView=itemView.findViewById(R.id.tv_favouriteblogPlace)
       val imageViewDelete:ImageView=itemView.findViewById(R.id.iv_deleteFavourite)
    }
}