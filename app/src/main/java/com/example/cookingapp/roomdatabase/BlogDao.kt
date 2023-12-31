package com.example.cookingapp.roomdatabase

import android.renderscript.ScriptIntrinsicYuvToRGB
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.cookingapp.constant.Constant
import com.example.cookingapp.model.BlogData


@Dao
interface BlogDao
{
     @Query("SELECT * FROM ${Constant.DATABASE_NAME}")
     fun getAllBlogs(): LiveData<MutableList<BlogData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(blogData: BlogData)

    @Update
    suspend fun addToFavourites(blogData: BlogData)

    @Query("SELECT * FROM ${Constant.DATABASE_NAME} WHERE  isFavourite = 1")
    fun getFavouriteBlogs():LiveData<MutableList<BlogData>>

    @Query("UPDATE ${Constant.DATABASE_NAME} SET isFavourite = :isFavourite WHERE id=:id")
    suspend fun updateFavouriteStatus(id:Int,isFavourite:Boolean)

   @Query("SELECT * FROM ${Constant.DATABASE_NAME} WHERE title LIKE :query")
    fun getBlogsByTitle(query:String):LiveData<MutableList<BlogData>>
}