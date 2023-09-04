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
import com.example.cookingapp.databinding.LayoutBloglistBinding
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.viewmodel.BlogViewModel
import com.example.cookingapp.viewmodelfactory.BlogViewModelFactory


class BlogAdapter(private var context: Context,private var blogList:List<BlogData>): RecyclerView.Adapter<BlogAdapter.MyViewHolder>()
{
    private lateinit var onFavouriteClickListener:OnFavouriteImageClick



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holderBlogBinding=LayoutBloglistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(holderBlogBinding)
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
       holder.onBind(setFavouriteItem(position))
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

   inner class MyViewHolder(private val binding:LayoutBloglistBinding):RecyclerView.ViewHolder(binding.root)
    {
       fun onBind(blogData: BlogData)
       {
           binding.tvBlogTitle.text=blogData.title
           binding.tvBlogDescription.text=blogData.description
           binding.ivBlogFavourite.setImageResource(if(blogData.isFavourite)R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
           binding.ivBlogFavourite.setOnClickListener {
           onFavouriteClickListener.OnFavouriteBlogClick(blogData)
           }
       }
   }
}