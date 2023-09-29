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
import com.example.cookingapp.databinding.LayoutBloglistBinding
import com.example.cookingapp.databinding.LayoutFavouritebloglistBinding
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData

class FavouriteAdapter(private var context: Context,private var favouriteBlogList: MutableList<BlogData>) :RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>()
{
    private lateinit var onDeleteFavouriteBlogClick: OnDeleteFavouriteBlogClick
    private lateinit var getLocationByClickListener:getLocationByClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val holderFavouriteBlogBinding= LayoutFavouritebloglistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(holderFavouriteBlogBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.onBindFavouriteData(deleteFavouriteItem(position))
        holder.onBindFavouriteData(getLocation(position))
    }

    fun removeBlogFromFavourites(position:Int)
    {
        if(position in 0 until favouriteBlogList.size)
        {
            favouriteBlogList.removeAt(position)
            notifyItemRemoved(position)
        }
        clearAllBlog()
    }

    fun clearAllBlog()
    {
        favouriteBlogList.clear()
        notifyDataSetChanged()
    }


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

    private fun deleteFavouriteItem(position: Int):BlogData=favouriteBlogList[position]

    interface getLocationByClick
    {
        fun getLocation(position: Int)
    }

    fun setOnLocationClickListener(locationClick:getLocationByClick)
    {
        this.getLocationByClickListener=locationClick
    }

    private fun getLocation(position: Int):BlogData=favouriteBlogList[position]

   inner class MyViewHolder(private val binding:LayoutFavouritebloglistBinding):RecyclerView.ViewHolder(binding.root)
    {
       fun onBindFavouriteData(favouriteBlogData:BlogData)
      {
           binding.tvFavouriteblogTitle.text=favouriteBlogData.title
           binding.tvFavouriteblogPlace.text=favouriteBlogData.place
           binding.ivDeleteFavourite.setOnClickListener {
               onDeleteFavouriteBlogClick.deleteFavouriteFromList(favouriteBlogData.id)
           }
          binding.imageViewLocation.setOnClickListener {
              getLocationByClickListener.getLocation(favouriteBlogData.id)
          }
       }
    }
}