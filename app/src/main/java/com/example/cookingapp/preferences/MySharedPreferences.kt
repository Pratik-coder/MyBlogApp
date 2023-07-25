
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
    val FAVOURITES:Boolean=true

    fun init(context: Context)
    {
       preferences =context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
       editor =preferences.edit()
    }

   /* fun setBlogAsFavourite(context: Context,blogData: BlogData):Boolean
    {
        init(context)
        val gson=Gson()
        val favBlogJson=gson.toJson(blogData)
        editor.putString(PREFS_NAME,favBlogJson)
        editor.putBoolean(FAVOURITES,true)
        return true
    }

    fun getFavouriteBlog(context: Context):List<BlogData>?
    {
        init(context)
        if (preferences.contains(FAVOURITES))
        {
            val favuriteBlogs:String?=preferences.getString(FAVOURITES,null)
            val gson=Gson()
            val favourites=gson.fromJson(favuriteBlogs,Array<BlogData>::class.java).asList()
            return favourites
        }
        return null
    }*/


    fun saveFavourites(context: Context, favouriteBlog:ArrayList<BlogData>)
    {
        var sharedPreferences: SharedPreferences
        var editor: SharedPreferences.Editor
        sharedPreferences=context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor=sharedPreferences.edit()
        var gson= Gson()
        var jsonFavouriteBlogs=gson.toJson(favouriteBlog)
        editor.putString(FAVOURITES.toString(),jsonFavouriteBlogs).commit()
       // editor.putBoolean(FAVOURITES,true).commit()
    }



    fun addFavouriteBlogs(context:Context, blogData: BlogData)
    {
        /*var myFavouriteBlogList:List<BlogData>?=null
        myFavouriteBlogList= getFavouriteBlogs(context)
        if (myFavouriteBlogList==null)
        {
            myFavouriteBlogList=ArrayList<BlogData>()
            myFavouriteBlogList.add(blogData)
            saveFavourites(context,myFavouriteBlogList)
        }*/

        var myFavouriteBlogList:MutableList<BlogData>?=getFavouriteBlogs(context)?.toMutableList()
        val currentList = myFavouriteBlogList?.toMutableList() ?: mutableListOf()
        /*if (myFavouriteBlogList==null)
        {
            myFavouriteBlogList= mutableListOf()
        }*/
        if (!blogData.isFavourite) {
            blogData.isFavourite=true
            currentList.add(blogData)
            myFavouriteBlogList=currentList
          //  saveFavourites(context,myFavouriteBlogList as ArrayList<BlogData>)
        }
    }

    fun getFavouriteBlogs(context: Context):List<BlogData>?
    {

       // var blogs:List<BlogData>
        val favouritePreferences:SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

       /* if (favouritePreferences.contains(FAVOURITES))
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
        }*/
        if (favouritePreferences.contains(FAVOURITES.toString()))
        {
            val listFavourites: String? =favouritePreferences.getString(FAVOURITES.toString(),null)
            if (listFavourites!=null)
            {
                val gson= Gson()
                val favouriteBlogs: Array<BlogData> = gson.fromJson(listFavourites, Array<BlogData>::class.java)
                return favouriteBlogs.toList()
            }
        }
            return null
    }




}
