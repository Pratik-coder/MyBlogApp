/*
package com.example.cookingapp.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cookingapp.constant.Constant
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData

@Dao
interface FavouriteBlogDao
{
    @Insert
    suspend fun addToFavourite(blogData: BlogData)

    */
/*@Query("SELECT * FROM ${Constant.FAVOURITE_DATABASEBLOGS} WHERE isFavourite=:isFavourite")
    fun getFavouriteBlogList(isFavourite:Boolean): LiveData<List<FavouriteBlogData>>
    *//*

    @Query("SELECT * FROM ${Constant.FAVOURITE_DATABASEBLOGS} INNER JOIN ${Constant.DATABASE_NAME} ON favouriteBlogs.favBlogId=blogs.id")
    fun getFavouriteBlogList(isFavourite:Boolean): LiveData<List<FavouriteBlogData>>
}*/
