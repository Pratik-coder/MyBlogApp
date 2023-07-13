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
    suspend fun addToFavourite(favouriteBlogData: FavouriteBlogData)

    @Query("SELECT * FROM ${Constant.DATABASE_NAME} WHERE isFavourite= :isFavourite")
    fun getFavouriteBlogList(isFavourite:Boolean): LiveData<List<FavouriteBlogData>>
}