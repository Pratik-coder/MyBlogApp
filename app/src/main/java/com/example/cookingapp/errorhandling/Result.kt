package com.example.cookingapp.errorhandling

import com.example.cookingapp.model.BlogData

sealed class Result
{
    object Empty:com.example.cookingapp.errorhandling.Result()
    data class Success(val data:List<BlogData>):com.example.cookingapp.errorhandling.Result()
    data class Error(val exception:Throwable):com.example.cookingapp.errorhandling.Result()

}
