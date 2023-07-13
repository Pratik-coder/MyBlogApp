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
import com.example.cookingapp.preferences.MySharedPreferences

class FavouriteAdapter(private var context: Context,private var favouriteBlogList: List<BlogData>) :RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>()
{
   private lateinit var mySharedPreferences: MySharedPreferences
   private var isBlogFavourite:Boolean=true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_favouritebloglist,parent,false)
       return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {

        mySharedPreferences=MySharedPreferences()
        val favouriteBlogData=favouriteBlogList[position]
        if (favouriteBlogList!=null) {
            isBlogFavourite=true
            mySharedPreferences.getFavouriteBlogs(context)
            holder.textViewFavouriteBlogTitle.text = favouriteBlogData.title
            holder.textViewFavouriteBlogPlace.text = favouriteBlogData.place
        }

    }



    override fun getItemCount(): Int
    {
      return favouriteBlogList.size
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
       val cardViewFavourite:CardView=itemView.findViewById(R.id.cardViewFavouriteBlog)
       val textViewFavouriteBlogTitle:TextView=itemView.findViewById(R.id.tv_favouriteblogTitle)
       val textViewFavouriteBlogPlace:TextView=itemView.findViewById(R.id.tv_favouriteblogPlace)
       val imageViewDelete:ImageView=itemView.findViewById(R.id.iv_deleteFavourite)

    }

}