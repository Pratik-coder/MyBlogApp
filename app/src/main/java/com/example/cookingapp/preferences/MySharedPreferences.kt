
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
    val sharedPreferences=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)


    fun setTextTitle(strTextTitle:String)
    {
        sharedPreferences.edit().putString("strTextTitle",strTextTitle).apply()
    }

    fun getSaveTitle():String
    {
        return sharedPreferences.getString("strTextTitle","")?:""
    }

    fun setTextDescription(strTextDescription:String)
    {
        sharedPreferences.edit().putString("strTextDescription",strTextDescription).apply()
    }

    fun getSaveDescription(): String
    {
        return sharedPreferences.getString("strTextDescription","")?:""
    }

    fun setTextPlace(strTextPlace:String)
    {
        sharedPreferences.edit().putString("strTextPlace",strTextPlace).apply()
    }

    fun getSavePlace():String
    {
        return sharedPreferences.getString("strTextPlace","")?:""
    }
}
