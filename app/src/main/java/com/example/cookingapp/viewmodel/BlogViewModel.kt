package com.example.cookingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.preferences.MyPreferences
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import javax.inject.Inject


class BlogViewModel @Inject constructor (private val blogRepository: BlogRepository):ViewModel()
{
    var blogList:LiveData<List<BlogData>>?=null
    private lateinit var mySharedPreferences: MySharedPreferences





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

        blogData.isFavourite=true
        blogRepository.updateList(context,blogData)

    }


    fun getAllFavouriteBlogs(context: Context): LiveData<List<BlogData>>?
    {
        mySharedPreferences=MySharedPreferences()
        mySharedPreferences.getFavouriteBlogs(context)
        blogRepository.getFavouriteBlogs(context)
        return blogList
    }


}