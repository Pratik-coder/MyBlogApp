
package com.example.cookingapp.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.cookingapp.model.BlogData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class MySharedPreferences
{
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    val PREFS_NAME="BLOG_APP"
    val FAVOURITES="isFavourite"

    fun init(context: Context)
    {
       preferences =context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
       editor =preferences.edit()
    }

   /* fun saveFavourites(context: Context, favouriteBlog:BlogData)
    {
        var sharedPreferences: SharedPreferences
        var editor: SharedPreferences.Editor
        sharedPreferences=context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor=sharedPreferences.edit()
        var gson= Gson()
        var jsonFavouriteBlogs=gson.toJson(favouriteBlog)
        editor.putString(FAVOURITES,jsonFavouriteBlogs)
        editor.putBoolean(FAVOURITES,true).commit()
    }
*/


    fun addFavouriteBlogs(context:Context, blogData: BlogData)
    {
        val blogList: MutableList<BlogData> = mutableListOf()
        val favBlogList: MutableList<BlogData> = loadFavourites(context)
        if (!blogData.isFavourite)
        {
            blogData.isFavourite=true
            blogList.add(blogData)
            saveFavourites(context,favBlogList)
        }
    }

    fun saveFavourites(context: Context,favourites:MutableList<BlogData>)
    {
          val sharedPrefernces= context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
          val editor=sharedPrefernces.edit()
          val json=Gson().toJson(favourites)
          editor.putString(FAVOURITES,json)
          editor.apply()
    }

    fun loadFavourites(context: Context):MutableList<BlogData>
    {
        val sharedPreferences=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        val json=sharedPreferences.getString(FAVOURITES,null)
        return if (json!=null)
        {
            val typeToken=object :TypeToken<MutableList<BlogData>>() {}.type
            Gson().fromJson(json,typeToken)
        }
        else
        {
            mutableListOf()
        }
    }

    fun getFavouriteBlogs(context: Context): MutableList<BlogData>?
    {

        var blogs:List<BlogData>
        val favouritePreferences:SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        if (favouritePreferences.contains(FAVOURITES))
        {
            val listFavourites: String? =favouritePreferences.getString(FAVOURITES,null)
            val gson= Gson()
            val favouriteBlogs=gson.fromJson(listFavourites,Array<BlogData>::class.java)
            blogs= mutableListOf(*favouriteBlogs)
            blogs=ArrayList<BlogData>(blogs)
        }
        else
        {
            return null
        }
        return blogs
    }
}
