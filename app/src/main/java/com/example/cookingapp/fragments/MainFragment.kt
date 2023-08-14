package com.example.cookingapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch

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
    private lateinit var blogAdapter:BlogAdapter
    private lateinit var textViewNoBlog:TextView



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

    override fun onStart() {
        super.onStart()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main, container, false)
        textViewNoBlog=view.findViewById(R.id.tv_noblog)
        recyclerViewBlogList=view.findViewById(R.id.rv_blogList)
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

              setObservers()
    }

    private fun setObservers()
    {
        val activity=activity as DashboardActivity
        blogViewModel.getAllBlogs(activity).observe(activity, Observer<List<BlogData>> {
            if (it.isEmpty()) {
                textViewNoBlog.visibility = View.VISIBLE
            } else {
                textViewNoBlog.visibility = View.GONE
                blogAdapter = BlogAdapter(activity, it)
                recyclerViewBlogList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerViewBlogList.adapter = blogAdapter
                //  Log.d("TAG",it.toString())
                blogAdapter.setOnFavouriteClickListener(object :
                    BlogAdapter.OnFavouriteImageClick {
                    override fun OnFavouriteBlogClick(blogData: BlogData) {
                        blogViewModel.MarkBlogAsFavourite(requireActivity(), blogData)
                        Toast.makeText(activity, "Blog Added To Favourites", Toast.LENGTH_SHORT).show()
                    }
                })
            }

        })
    }



   /* override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        val activity=activity as DashboardActivity
        recyclerViewBlogList.layoutManager = LinearLayoutManager(activity)
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
        blogViewModel.getAllBlogs(activity).observe(activity, Observer<List<BlogData>> {
            if (it!=null)
            {
                blogAdapter= BlogAdapter(activity,it)
                recyclerViewBlogList.adapter=blogAdapter
            }

            blogAdapter.setOnFavouriteClickListener(object :BlogAdapter.OnFavouriteImageClick
            {
                override fun OnFavouriteBlogClick(blogData: BlogData) {

                 //   blogViewModel.MarkBlogAsFavourite(activity,blogData)
                    mySharedPreferences=MySharedPreferences()
                    mySharedPreferences.addFavouriteBlogs(activity,blogData)
                    Toast.makeText(activity,"Blog Added To Favourites",Toast.LENGTH_SHORT).show()
                }
            })
        })
    }*/

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







