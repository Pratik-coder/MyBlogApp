
package com.example.cookingapp.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import com.example.cookingapp.model.BlogData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class MySharedPreferences(context: Context)
{
    val PREFS_NAME="BLOGAPP"
    val BLOG_TITLE="strTextTitle"
    val BLOG_DESCRIPTION="strTextDescription"
    val BLOG_PLACE="strTextPlace"
    val sharedPreferences=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    fun setTextTitle(strTextTitle:String)
    {
        sharedPreferences.edit().putString(BLOG_TITLE,strTextTitle).apply()
    }

    fun getSaveTitle():String
    {
        return sharedPreferences.getString(BLOG_TITLE,"")?:""
    }

    fun setTextDescription(strTextDescription:String)
    {
        sharedPreferences.edit().putString(BLOG_DESCRIPTION,strTextDescription).apply()
    }

    fun getSaveDescription(): String
    {
        return sharedPreferences.getString(BLOG_DESCRIPTION,"")?:""
    }

    fun setTextPlace(strTextPlace:String)
    {
        sharedPreferences.edit().putString(BLOG_PLACE,strTextPlace).apply()
    }

    fun getSavePlace():String
    {
        return sharedPreferences.getString(BLOG_PLACE,"")?:""
    }
}
