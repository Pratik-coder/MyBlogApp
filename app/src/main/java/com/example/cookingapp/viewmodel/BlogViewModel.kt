package com.example.cookingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class BlogViewModel @Inject constructor (private val blogRepository: BlogRepository):ViewModel()
{
    var blogList:LiveData<List<BlogData>>?=null



    fun getAllBlogs(context: Context): LiveData<List<BlogData>>
    {
        blogList=blogRepository.getAllBlogList(context)
        return blogList as LiveData<List<BlogData>>
    }

    fun AddBlog(context: Context,blogTitle:String,blogDescription:String,blogPlace:String)
    {
        blogRepository.insertBlogData(context,blogTitle,blogDescription,blogPlace)
    }


    fun MarkBlogAsFavourite(context: Context,blogData: BlogData)
    {
        if (!blogData.isFavourite) {
        blogData.isFavourite = true
        viewModelScope.launch {
            blogRepository.updateList(context, blogData)
        }
    }
    }


    fun getAllFavouriteBlogs(context: Context): LiveData<List<BlogData>>
    {
            blogList=blogRepository.getFavouriteBlogs(context)
            return blogList as LiveData<List<BlogData>>
    }
}