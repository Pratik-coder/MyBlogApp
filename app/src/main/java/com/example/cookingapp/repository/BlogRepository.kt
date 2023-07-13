package com.example.cookingapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.roomdatabase.BlogDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class BlogRepository
{

        private lateinit var blogDatabase: BlogDatabase

        private fun initializeDatabase(context: Context): BlogDatabase {
            return BlogDatabase.getDatabase(context)
        }

        fun getAllBlogList(context: Context): LiveData<List<BlogData>> {
            blogDatabase = initializeDatabase(context)
            return blogDatabase.blogDao().getBlogList()
        }


      fun insertBlogData(context: Context,title:String,description:String,place:String)
    {
         blogDatabase=initializeDatabase(context)
        CoroutineScope(IO).launch {
            val blogData=BlogData(title,description,place)
            blogDatabase.blogDao().insertBlog(blogData)
        }
    }

    fun insertFavouriteBlogs(context: Context,favouriteTitle:String,favouriteDescription:String,favouritePlace:String,isFavourite:Boolean)
    {
           blogDatabase=initializeDatabase(context)
           CoroutineScope(IO).launch {
            val blogData=BlogData(favouriteTitle,favouriteDescription,favouritePlace,isFavourite=true)
            blogDatabase.blogDao().addToFavourite(blogData)
           }
    }

    fun getFavouriteBlogList(context: Context,isFavourite: Boolean):LiveData<List<BlogData>>
    {
        blogDatabase=initializeDatabase(context)
        return blogDatabase.blogDao().getFavouriteBlogList(isFavourite)

    }

}