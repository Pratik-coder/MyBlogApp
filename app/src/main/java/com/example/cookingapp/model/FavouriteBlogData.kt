/*
package com.example.cookingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.cookingapp.constant.Constant

@Entity(tableName = Constant.FAVOURITE_DATABASEBLOGS, foreignKeys = [ForeignKey(entity = BlogData::class, parentColumns = ["id"], childColumns = ["favBlogId"])])
data class FavouriteBlogData(
    @PrimaryKey(autoGenerate = true)
    var favBlogId:Int = 0,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo("place")
    var place:String,
    @ColumnInfo(name="isFavourite")
    var isFavourite:Boolean=true
)
*/
