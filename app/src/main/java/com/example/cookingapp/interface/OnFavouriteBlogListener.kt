package com.example.cookingapp.`interface`

import android.content.Context
import com.example.cookingapp.model.BlogData

interface OnFavouriteBlogListener
{
    fun isBlogFavourited(context: Context,blogData: BlogData):Boolean
}