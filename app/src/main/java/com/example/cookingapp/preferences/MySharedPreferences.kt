package com.example.cookingapp.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.cookingapp.model.BlogData
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class MySharedPreferences
{

    companion object {
        const val PREFS_NAME="BLOG_APP"
        const val FAVOURITES="Blog_Favourite"
    }

    fun saveFavourites(context: Context, favouriteBlog:List<BlogData>)
    {
        var sharedPreferences: SharedPreferences
        var editor: SharedPreferences.Editor

        sharedPreferences=context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor=sharedPreferences.edit()
        var gson= Gson()
        var jsonFavouriteBlogs=gson.toJson(favouriteBlog)
        editor.putString(FAVOURITES,jsonFavouriteBlogs)
        editor.putBoolean("id",true)
         // editor.commit()
        editor.apply()
    }



    fun addFavouriteBlogs(context:Context, blogData: BlogData)
    {
        var myFavouriteBlogList:List<BlogData>?=null
        myFavouriteBlogList= getFavouriteBlogs(context)
        if (myFavouriteBlogList==null)
        {
            myFavouriteBlogList=ArrayList<BlogData>()
            myFavouriteBlogList.add(blogData)
            saveFavourites(context,myFavouriteBlogList)
        }
    }

    fun getFavouriteBlogs(context: Context): List<BlogData>?
    {
        val favouritePreferences: SharedPreferences
        var blogs:List<BlogData>

        favouritePreferences=context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        if (favouritePreferences.contains(FAVOURITES))
        {
            val listFavourites: String? =favouritePreferences.getString(FAVOURITES,null)
            val gson= Gson()
            val favouriteBlogs=gson.fromJson(listFavourites,Array<BlogData>::class.java)
            blogs= mutableListOf(*favouriteBlogs)
            blogs=ArrayList<BlogData>(blogs)
        }
        /*else
        {
            return null
        }*/
     //return kotlin.collections.ArrayList(blogs)
        return emptyList()

    }
}