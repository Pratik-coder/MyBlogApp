package com.example.cookingapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

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
    private lateinit var linearLayoutBlogSearch: LinearLayout
    private lateinit var editTextSearch: EditText
    private lateinit var imageViewSearch:ImageView






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



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main, container, false)
        textViewNoBlog=view.findViewById(R.id.tv_noblog)
        recyclerViewBlogList=view.findViewById(R.id.rv_blogList)
        imageViewSearch=view.findViewById(R.id.iv_Search)
        linearLayoutBlogSearch=view.findViewById(R.id.ll_BlogSearch)
        editTextSearch=view.findViewById(R.id.et_searchBlogs)
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
              setObservers()
              onSearchImageClick()
            //  searchBlogs()
    }

    private fun setObservers()
    {
        val activity=activity as DashboardActivity
        blogViewModel.getAllBlogs(activity).observe(activity, Observer<List<BlogData>> {
            if (it.isEmpty()) {
                textViewNoBlog.visibility = View.VISIBLE
                imageViewSearch.visibility=View.GONE
            } else {
                textViewNoBlog.visibility = View.GONE
                imageViewSearch.visibility=View.VISIBLE
                blogAdapter = BlogAdapter(activity, it)
                val layoutManager=LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerViewBlogList.layoutManager=layoutManager
                recyclerViewBlogList.adapter = blogAdapter
                //  Log.d("TAG",it.toString())
                blogAdapter.setOnFavouriteClickListener(object :
                    BlogAdapter.OnFavouriteImageClick {
                    override fun OnFavouriteBlogClick(blogData: BlogData) {
                        blogViewModel.MarkBlogAsFavourite(requireActivity(), blogData)
                        Toast.makeText(activity,getString(R.string.str_addBlogToFavourite), Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }

    private fun onSearchImageClick()
    {
        imageViewSearch.setOnClickListener {
            linearLayoutBlogSearch.visibility=View.VISIBLE
            searchBlogs()
        }
    }


   private fun searchBlogs()
   {
       val activity=activity as DashboardActivity
            editTextSearch.addTextChangedListener(object :TextWatcher
            {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                     val strquery:String=s.toString()
                     blogViewModel.getBlogsBySearch(requireActivity(),strquery).observe(requireActivity(),Observer<List<BlogData>>
                     {
                         blogAdapter=BlogAdapter(activity,it)
                         recyclerViewBlogList.adapter=blogAdapter
                     })
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
   }



    override fun onResume() {
        super.onResume()
        setObservers()
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







