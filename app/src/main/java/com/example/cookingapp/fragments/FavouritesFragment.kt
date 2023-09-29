package com.example.cookingapp.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.audiofx.Equalizer.Settings
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.R
import com.example.cookingapp.activity.DashboardActivity
import com.example.cookingapp.adapter.FavouriteAdapter
import com.example.cookingapp.databinding.FragmentFavouritesBinding
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.model.FavouriteBlogData
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.viewmodel.BlogViewModel
import com.example.cookingapp.viewmodelfactory.BlogViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var favouritesBinding: FragmentFavouritesBinding
    private lateinit var blogViewModel: BlogViewModel
    private  var blogRepository: BlogRepository = BlogRepository()
    private lateinit var favouriteAdapter: FavouriteAdapter



   /* override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DashboardActivity)
        {
            val activity=context as DashboardActivity
        }
        else
        {
            throw IllegalArgumentException("Host Activity must be of type my activity")
        }
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // val view= inflater.inflate(R.layout.fragment_favourites, container, false)
        favouritesBinding=FragmentFavouritesBinding.inflate(inflater)
        blogViewModel= ViewModelProvider(this, BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
        return favouritesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        setObservers()
    }


    private fun setObservers()
    {
        val activity=activity as DashboardActivity
        blogViewModel.getAllFavouriteBlogs(activity).observe(activity, Observer<MutableList<BlogData>> {
            if (it.isNotEmpty() && it!=null)
            {
                favouritesBinding.tvNofavblog.visibility=View.GONE
                favouriteAdapter=FavouriteAdapter(activity,it)
                val layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                favouritesBinding.rvFavouriteBlogList.layoutManager=layoutManager
                favouritesBinding.rvFavouriteBlogList.adapter=favouriteAdapter
                showDeleteFavouriteAlert()
                getLocationPermission()
            }
            else
            {
                favouritesBinding.tvNofavblog.visibility=View.VISIBLE
            }
        })
    }

    private fun showDeleteFavouriteAlert()
    {
        favouriteAdapter.setOnFavouriteIconDelete(object :FavouriteAdapter.OnDeleteFavouriteBlogClick
        {

            @SuppressLint("SuspiciousIndentation")
            override fun deleteFavouriteFromList(favBlogId: Int)
            {
                val alertDialogBuilder=AlertDialog.Builder(requireContext())
                    alertDialogBuilder.setTitle(getString(R.string.str_FavouriteTitle))
                    .setMessage(getString(R.string.str_deletefavouriteiconAlert))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.str_yes))
                    {
                            dialogInterface,_->
                        dialogInterface.dismiss()
                        blogViewModel.deleteByFavourite(requireActivity(),favBlogId,false)
                        favouriteAdapter.removeBlogFromFavourites(favBlogId)
                        Toast.makeText(requireActivity(),getString(R.string.str_removeFromFavourites),Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton(getString(R.string.str_no))
                    {
                            dialogInterface,_->
                            dialogInterface.dismiss()
                    }
                alertDialogBuilder.create()
                alertDialogBuilder.show()
            }
        })
    }

    private fun openLocationSettings()=startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))

    private fun getLocationPermission()
    {
        favouriteAdapter.setOnLocationClickListener(object :FavouriteAdapter.getLocationByClick
        {
            override fun getLocation(position: Int) {
                val alertDialogBuilder=AlertDialog.Builder(requireContext())
                alertDialogBuilder.setTitle(getString(R.string.str_LocationTitle))
                    .setMessage(getString(R.string.str_LocationMessage))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.str_yes))
                    {
                        dialogInterface,_->
                        dialogInterface.dismiss()
                        openLocationSettings()
                    }
                    .setNegativeButton(getString(R.string.str_no))
                    {
                            dialogInterface,_->
                            dialogInterface.dismiss()
                    }
                alertDialogBuilder.create()
                alertDialogBuilder.show()
            }
        })
    }



    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()

    }




    /*companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouritesFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/

}