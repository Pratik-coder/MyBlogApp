package com.example.cookingapp.preferences

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.example.cookingapp.model.BlogData
import com.google.gson.Gson

object MyPreferences
{
     lateinit var preferences: SharedPreferences
     lateinit var editor: SharedPreferences.Editor
     val PREFS_NAME="BLOG_APP"
     val FAVOURITES="Blog_Favourite"

    fun init(context: Context)
    {
        preferences=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        editor= preferences.edit()
    }

    fun setBlogAsFavourite(blogData: BlogData):Boolean
    {
        val gson=Gson()
        val favBlogJson=gson.toJson(blogData)
        editor.putString(PREFS_NAME,favBlogJson)
        editor.putBoolean(FAVOURITES,true)
        return true
    }

    fun getFavouriteBlog():List<BlogData>?
    {
        if (preferences.contains(FAVOURITES))
        {
            val favuriteBlogs:String?= preferences.getString(FAVOURITES,null)
            val gson=Gson()
            val favourites=gson.fromJson(favuriteBlogs,Array<BlogData>::class.java).asList()
            return favourites
        }
        return null
    }
}