package com.example.cookingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.repository.BlogRepository
import javax.inject.Inject


class BlogViewModel @Inject constructor (private val blogRepository: BlogRepository):ViewModel()
{
    var blogList:LiveData<List<BlogData>>?=null
    var favBlogList:MutableLiveData<List<BlogData>>?=null



    fun getAllBlogs(context: Context): LiveData<List<BlogData>>
    {
        blogList=blogRepository.getAllBlogList(context)
        return blogList as LiveData<List<BlogData>>
    }

    fun AddBlog(context: Context,blogTitle:String,blogDescription:String,blogPlace:String)
    {
        blogRepository.insertBlogData(context,blogTitle,blogDescription,blogPlace)
    }

    fun AddBlogsToFavourite(context: Context,blogData: BlogData/*favouriteTitle:String,favouriteDescription:String,favouritePlace:String,isFavourite:Boolean*/)
    {
        blogRepository.insertFavouriteBlogs(context,blogData)
    }

    fun getAllFavouriteBlogList(context: Context,isFavourite:Boolean):LiveData<List<BlogData>>
    {
        blogList=blogRepository.getFavouriteBlogList(context,true)
        return blogList as LiveData<List<BlogData>>

    }
}