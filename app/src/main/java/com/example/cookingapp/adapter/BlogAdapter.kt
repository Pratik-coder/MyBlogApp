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
import com.example.cookingapp.constant.OnFavouriteBlogClickListener
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.preferences.MyPreferences
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.viewmodel.BlogViewModel
import com.example.cookingapp.viewmodelfactory.BlogViewModelFactory


class BlogAdapter(private var context: Context,private var blogList:List<BlogData>): RecyclerView.Adapter<BlogAdapter.MyViewHolder>()
{
    private lateinit var onFavouriteClickListener:OnFavouriteImageClick
    private lateinit var mySharedPreferences:MySharedPreferences
    var favouriteBlogs=HashMap<BlogData,Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_bloglist,parent,false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        mySharedPreferences=MySharedPreferences()
        val blogData=blogList[position]
        holder.textViewBlogTitle.text=blogData.title
        holder.textViewBlogDescription.text=blogData.description
      //  holder.onBindFavourite(setFavouriteItem(position))
        holder.imageViewFavourite.setOnClickListener {
            onFavouriteClickListener.OnFavouriteBlogClick(blogData)
            holder.imageViewFavourite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
    }

    /*fun checkFavouriteItem(favblogData: BlogData):Boolean
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
    }*/

    /*private fun upDateFavouriteStatus(favouriteBlog:BlogData)
    {
        val favourites=mySharedPreferences.getFavouriteBlogs(context)
        if (!favouriteBlog.isFavourite)
        {
            favouriteBlog.isFavourite=true
            if (favourites != null) {
                favourites.add(favouriteBlog)
                mySharedPreferences.saveFavourites(context,favourites)
            }
        }
    }*/

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

    fun UpdateList(upDatedList:List<BlogData>)
    {
        blogList=upDatedList
        notifyDataSetChanged()
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

    companion object
    {
        private val BLOG_FAVOURITES=object : DiffUtil.ItemCallback<BlogData>()
        {
            override fun areItemsTheSame(oldItem: BlogData, newItem: BlogData): Boolean {
              return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: BlogData, newItem: BlogData): Boolean {
               return oldItem==newItem
            }
        }
    }
}