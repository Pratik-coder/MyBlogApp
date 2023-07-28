package com.example.cookingapp.roomdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.cookingapp.constant.Constant
import com.example.cookingapp.model.BlogData

@Dao
interface BlogDao
{
    @Query("SELECT * FROM ${Constant.DATABASE_NAME}")
     fun getBlogList(): LiveData<List<BlogData>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(blogData: BlogData)

    // @Query("UPDATE ${Constant.DATABASE_NAME} SET isFavourite WHERE id=:id")
    //  suspend fun updateBlogFavoriteStatus(id: Int, isFavorite: Boolean)   //NewMethod
      @Update
     suspend fun addToFavourite(blogData: BlogData)


    @Query("SELECT * FROM ${Constant.DATABASE_NAME} WHERE  isFavourite=:isFavourite")
    fun getFavouriteBlogList(isFavourite:Boolean):LiveData<List<BlogData>>
}