package com.example.cookingapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cookingapp.model.BlogData


@Database(entities = [BlogData::class], version = 2, exportSchema = false)

abstract class BlogDatabase:RoomDatabase()
{
    abstract fun blogDao():BlogDao
  //  abstract fun favBlogDao():FavouriteBlogDao


    companion object
    {
        @Volatile
        private var INSTANCE:BlogDatabase?=null

        fun getDatabase(context: Context):BlogDatabase
        {
            return INSTANCE?: synchronized(this)
            {
                val instance=Room.databaseBuilder(
                    context.applicationContext,BlogDatabase::class.java,"BlogDatabase.db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}