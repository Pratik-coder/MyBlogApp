package com.example.cookingapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData


@Database(entities = [BlogData::class], version = 3, exportSchema = false)

abstract class BlogDatabase:RoomDatabase()
{
    abstract fun blogDao():BlogDao

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
                   // .addMigrations()
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}