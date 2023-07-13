package com.example.cookingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cookingapp.constant.Constant


@Entity(tableName = Constant.FAVOURITE_DATABASEBLOGS)
data class FavouriteBlogData(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo("place")
    var place:String,
    @ColumnInfo(name="isFavourite")
    var isFavourite:Boolean=true
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}

