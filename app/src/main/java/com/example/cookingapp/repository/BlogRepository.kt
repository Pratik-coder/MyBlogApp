package com.example.cookingapp.repository

import android.content.Context
import android.content.LocusId
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.util.getColumnIndex
import com.example.cookingapp.fragments.FavouritesFragment
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.roomdatabase.BlogDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlogRepository
{
        private lateinit var blogDatabase: BlogDatabase

        private fun initializeDatabase(context: Context): BlogDatabase {
            return BlogDatabase.getDatabase(context)
        }

        fun getAllBlogList(context: Context):LiveData<MutableList<BlogData>> {
            blogDatabase = initializeDatabase(context)
            return blogDatabase.blogDao().getAllBlogs()
        }


      fun insertBlogData(context: Context,title:String,description:String,place:String)
    {
         blogDatabase=initializeDatabase(context)
        CoroutineScope(IO).launch {
            val blogData=BlogData(title,description,place)
            blogDatabase.blogDao().insertBlog(blogData)
        }
    }

     fun updateList(context: Context,blogData: BlogData) {
         blogDatabase = initializeDatabase(context)
         CoroutineScope(Main).launch {
             blogDatabase.blogDao().addToFavourites(blogData)
         }
     }

    fun getFavouriteBlogs(context: Context):LiveData<MutableList<BlogData>>
    {
        blogDatabase=initializeDatabase(context)
        return blogDatabase.blogDao().getFavouriteBlogs()
    }

     fun deleteFavouriteBlog(context:Context,id:Int,isFavourite:Boolean) {
        blogDatabase = initializeDatabase(context)
        CoroutineScope(Main).launch {
            blogDatabase.blogDao().updateFavouriteStatus(id,isFavourite)
        }
     }

    fun searchBlogs(context: Context, query:String):LiveData<MutableList<BlogData>>
    {
        blogDatabase=initializeDatabase(context)
        return blogDatabase.blogDao().getBlogsByTitle("%" + query + "%")
    }


}