package com.example.cookingapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.R
import com.example.cookingapp.`interface`.OnBlogClickListener
import com.example.cookingapp.fragments.FavouritesFragment
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.preferences.MySharedPreferences


class BlogAdapter(private var context: Context,private var blogList:List<BlogData>): RecyclerView.Adapter<BlogAdapter.MyViewHolder>()
{
    private lateinit var onFavouriteClickListener:OnFavouriteImageClick
    private lateinit var mySharedPreferences: MySharedPreferences



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_bloglist,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val blogData=blogList[position]
        holder.textViewBlogTitle.text=blogData.title
        holder.textViewBlogDescription.text=blogData.description
       /* if (onBlogClickListener.isBlogFavourite(context,blogData))
        {
            holder.imageViewFavourite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        else
        {
            holder.imageViewFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        holder.imageViewFavourite.setOnClickListener {
            onBlogClickListener.addToFavourite(context,blogData)
        }*/
      //  holder.onBindFavourite(setFavouriteItem(position))
        holder.imageViewFavourite.setOnClickListener {
            onFavouriteClickListener.OnFavouriteBlogClick(position)
            checkFavouriteItem(blogData)
            if (checkFavouriteItem(blogData))
            {
                holder.imageViewFavourite.setImageResource(R.drawable.ic_baseline_favorite_24)
                holder.imageViewFavourite.isEnabled=false
            }
            else
            {
                holder.imageViewFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }

    }

    fun checkFavouriteItem(favblogData: BlogData):Boolean
    {
        var favouriteCheck:Boolean=false
        var favouriteBlogs:List<BlogData>
        mySharedPreferences=MySharedPreferences()
        favouriteBlogs= mySharedPreferences.getFavouriteBlogs(context)!!
        if (favouriteBlogs!=null)
        {
            for (blogdata in favouriteBlogs)
            {
                if (blogdata.equals(favblogData))
                {
                    favouriteCheck=true
                }
            }
        }
        return true
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

   interface OnFavouriteImageClick
   {
       fun OnFavouriteBlogClick(position:Int)
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