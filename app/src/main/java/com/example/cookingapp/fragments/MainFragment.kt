package com.example.cookingapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.R
import com.example.cookingapp.activity.DashboardActivity
import com.example.cookingapp.adapter.BlogAdapter
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.viewmodel.BlogViewModel
import com.example.cookingapp.viewmodelfactory.BlogViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(){
   /* // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null*/
    private lateinit var recyclerViewBlogList: RecyclerView
    private lateinit var blogViewModel: BlogViewModel
    private  var blogRepository: BlogRepository = BlogRepository()
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var textViewNoBlog:TextView
    lateinit var mySharedPreferences:MySharedPreferences


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main, container, false)
        textViewNoBlog=view.findViewById(R.id.tv_noblog)
        recyclerViewBlogList=view.findViewById(R.id.rv_blogList)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        val activity=activity as DashboardActivity
        mySharedPreferences=MySharedPreferences()
        recyclerViewBlogList.layoutManager = LinearLayoutManager(activity)
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
        blogViewModel.getAllBlogs(activity)?.observe(activity, Observer<List<BlogData>> {
            if (it!=null)
            {
                blogAdapter= BlogAdapter(activity,it)
                recyclerViewBlogList.adapter=blogAdapter
            }
            blogAdapter.setOnFavouriteClickListener(object :BlogAdapter.OnFavouriteImageClick
            {
                @SuppressLint("SuspiciousIndentation")
                override fun OnFavouriteBlogClick(position: Int) {
                    val favBlog = it[position] as BlogData
                        mySharedPreferences.addFavouriteBlogs(activity, favBlog)
                        favBlog.id=R.drawable.ic_baseline_favorite_24
                        favBlog.isFavourite=true
                        recyclerViewBlogList.adapter?.notifyDataSetChanged()
                        Toast.makeText(activity, "Blog Added To Favourite", Toast.LENGTH_SHORT).show()

                }
            })

        })

    }

    override fun onStart() {
        super.onStart()
    }



    override fun onResume() {
        super.onResume()
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

    companion object
    {
        fun newInstance()=MainFragment()
    }
}


