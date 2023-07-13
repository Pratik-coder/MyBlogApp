package com.example.cookingapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.viewmodel.BlogViewModel
import javax.inject.Inject

class BlogViewModelFactory @Inject constructor(private val blogRepository: BlogRepository):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlogViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return BlogViewModel(this.blogRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}