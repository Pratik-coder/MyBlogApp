package com.example.cookingapp.activity

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.Menu
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
    private lateinit var mFragmentManager: FragmentManager

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.menu,menu)
       val colour=ContextCompat.getColor(this,R.color.teal_200)
        for (i in 0 until menu.size())
        {
            val menuItem=menu.getItem(i)
            val icon=menuItem.icon
            icon?.setColorFilter(colour,PorterDuff.Mode.SRC_IN)
        }
        return true
    }

    private fun getFragmentByName()
    {
        bottomNavigationView.setOnItemSelectedListener {
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

                else->false
            }
        }
    }
}