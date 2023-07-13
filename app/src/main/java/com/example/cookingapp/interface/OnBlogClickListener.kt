package com.example.cookingapp.`interface`

import android.content.Context
import com.example.cookingapp.model.BlogData

interface OnBlogClickListener
{
    fun addToFavourite(context: Context,blogData: BlogData)
    fun isBlogFavourite(context: Context,blogData: BlogData):Boolean
}