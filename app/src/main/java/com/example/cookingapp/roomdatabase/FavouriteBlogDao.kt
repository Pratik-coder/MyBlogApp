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
interface FavouriteBlogDao {
    @Query("SELECT * FROM ${Constant.FAVOURITE_DATABASEBLOGS} INNER JOIN ${Constant.DATABASE_NAME} ON FavouriteBlogData.favBlogTitle=BlogData.title")
    fun getAllFavouriteBlogList(isFavourite:Boolean): LiveData<List<FavouriteBlogData>>
}

*/
