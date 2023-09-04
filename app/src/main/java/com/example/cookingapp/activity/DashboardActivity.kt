package com.example.cookingapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cookingapp.R
import com.example.cookingapp.fragments.*
import com.example.cookingapp.fragments.BlogFragment.Companion.newInstance
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.viewmodel.BlogViewModel
import com.example.cookingapp.viewmodelfactory.BlogViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity()
{
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var currentFragment:Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        loadFragment(MainFragment.newInstance())
        getFragmentByName()
    }


    private fun loadFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout,fragment).commit()
    }

    private fun getFragmentByName()
    {
        /*bottomNavigationView.setOnItemSelectedListener {
            item->
            var fragment=Fragment()
            when(item.itemId)
            {
                R.id.nav_home->
                {
                  fragment=MainFragment()
                  loadFragment(fragment)
                    true
                }
                *//*R.id.nav_users->
                {
                    fragment=UserFragment()
                    loadFragment(fragment)
                    true
                }*//*
                R.id.nav_addblog->
                {
                    fragment=BlogFragment()
                    loadFragment(fragment)
                    true
                }

                R.id.nav_addfaourited->
                {
                    fragment=FavouritesFragment()
                    loadFragment(fragment)
                    true
                }

                R.id.nav_settings->
                {
                    fragment=SettingsFragment()
                    loadFragment(fragment)
                    true
                }
                else->false
            }
        }*/
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_home->setFragment(MainFragment())
                R.id.nav_addblog->setFragment(BlogFragment())
                R.id.nav_addfaourited->setFragment(FavouritesFragment())
            //    R.id.nav_settings->setFragment(SettingsFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment)
    {
        currentFragment=fragment
        val transcation=supportFragmentManager.beginTransaction()
        transcation.replace(R.id.frameLayout,fragment)
        transcation.addToBackStack(null)
        transcation.commit()
    }
}