
package com.example.cookingapp.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import com.example.cookingapp.model.BlogData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class MySharedPreferences(context: Context)
{
    val PREFS_NAME="BLOGAPP"
    val IS_BLOGFAVOURITE="BLOG_FAVOURITE"





   /* fun loadFavouriteData(context: Context):MutableList<BlogData>
   {
       val sharedPreferences:SharedPreferences
       sharedPreferences=context.getSharedPreferences("MySharedPrefernces",Context.MODE_PRIVATE)
       val json=sharedPreferences.getString("favouriteItems",null)
       return if(json!=null)
       {
           val type=object :TypeToken<MutableList<BlogData>>(){}.type
           Gson().fromJson(json,type)
       }
       else
       {
           mutableListOf()
       }
   }

     fun saveData(context: Context)
    {
        val favouriteBlog= MutableList<BlogData>(0)
        val sharedPreferences:SharedPreferences
        sharedPreferences=context.getSharedPreferences("MySharedPrefernces",Context.MODE_PRIVATE)
        val json=Gson().toJson(favouriteBlog)
        sharedPreferences.edit().putString("favouriteItems",json).apply()
    }*/
}
