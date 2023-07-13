package com.example.cookingapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private lateinit var blogViewModel: BlogViewModel
    private var blogRepository:BlogRepository=BlogRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
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
        bottomNavigationView.setOnItemSelectedListener {
            item->
            val fragment:Fragment
            when(item.itemId)
            {
                R.id.nav_home->
                {
                  fragment=MainFragment()
                  loadFragment(fragment)
                    true
                }
                /*R.id.nav_users->
                {
                    fragment=UserFragment()
                    loadFragment(fragment)
                    true
                }*/
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

                /*R.id.nav_chatlist->
                {
                    fragment=ChatListFragment()
                    loadFragment(fragment)
                    true
                }
                R.id.nav_profile->
                {
                    fragment=ProfileFragment()
                    loadFragment(fragment)
                    true
                }*/
                else->false
            }
        }
    }
}