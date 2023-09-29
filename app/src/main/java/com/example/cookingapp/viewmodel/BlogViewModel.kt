package com.example.cookingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class BlogViewModel @Inject constructor (private val blogRepository: BlogRepository):ViewModel()
{
    var blogList:LiveData<MutableList<BlogData>>?=null
    var favBlogList:LiveData<MutableList<BlogData>>?=null

    fun getAllBlogs(context: Context):LiveData<MutableList<BlogData>>
    {
        blogList=blogRepository.getAllBlogList(context)
        return blogList as LiveData<MutableList<BlogData>>
    }

    fun addBlog(context: Context,blogTitle:String,blogDescription:String,blogPlace:String)
    {
        blogRepository.insertBlogData(context,blogTitle,blogDescription,blogPlace)
    }


    fun markBlogAsFavourite(context: Context,blogData: BlogData)
    {
        if(!blogData.isFavourite) {
        blogData.isFavourite = true
        viewModelScope.launch {
             blogRepository.updateList(context, blogData)
           }
        }
    }

    fun getAllFavouriteBlogs(context: Context): LiveData<MutableList<BlogData>>
    {
          favBlogList=blogRepository.getFavouriteBlogs(context)
          return favBlogList as LiveData<MutableList<BlogData>>
    }

         fun deleteByFavourite(context: Context, id:Int, isFavourite:Boolean)
          {
              viewModelScope.launch {
                  blogRepository.deleteFavouriteBlog(context, id, isFavourite)
              }
         }

   fun getBlogsBySearch(context: Context,query:String):LiveData<MutableList<BlogData>>
    {
        blogList=blogRepository.searchBlogs(context,"%" + query + "%")
        return blogList as LiveData<MutableList<BlogData>>
    }

}