package com.example.cookingapp.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookingapp.constant.Constant
import com.example.cookingapp.model.BlogData

@Dao
interface BlogDao
{
    @Query("SELECT * FROM ${Constant.DATABASE_NAME}")
    fun getBlogList():LiveData<List<BlogData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(blogData: BlogData)

     @Insert(onConflict =OnConflictStrategy.REPLACE)
     fun addToFavourite(blogData: BlogData)

    @Query("SELECT * FROM ${Constant.DATABASE_NAME} WHERE isFavourite=:isFavourite")
    fun getFavouriteBlogList(isFavourite:Boolean):LiveData<List<BlogData>>

}