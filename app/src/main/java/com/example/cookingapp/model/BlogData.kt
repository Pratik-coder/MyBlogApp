package com.example.cookingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cookingapp.constant.Constant

@Entity(tableName =Constant.DATABASE_NAME)
  data class BlogData(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo("place")
    var place:String,
    @ColumnInfo(name="isFavourite")
    var isFavourite:Boolean=false
)
{
    @PrimaryKey (autoGenerate = true)
    var id:Int = 0
}
