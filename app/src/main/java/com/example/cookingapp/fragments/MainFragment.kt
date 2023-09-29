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
import com.example.cookingapp.databinding.FragmentMainBinding
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
    private lateinit var mainFragmentBinding:FragmentMainBinding
    private lateinit var blogViewModel: BlogViewModel
    private  var blogRepository: BlogRepository = BlogRepository()
    private lateinit var blogAdapter:BlogAdapter
    private var isBlogSearch=true



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
      //  val view= inflater.inflate(R.layout.fragment_main, container, false)
        mainFragmentBinding=FragmentMainBinding.inflate(inflater)
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
              setObservers()
              onSearchImageClick()
    }

    private fun setObservers()
    {

        val activity=activity as DashboardActivity
        blogViewModel.getAllBlogs(activity).observe(activity, Observer<MutableList<BlogData>> {
            if (it.isEmpty()) {
                mainFragmentBinding.tvNoblog.visibility=View.VISIBLE
                mainFragmentBinding.ivSearch.visibility=View.GONE
            } else {
                mainFragmentBinding.tvNoblog.visibility=View.GONE
                mainFragmentBinding.ivSearch.visibility=View.VISIBLE
                blogAdapter = BlogAdapter(activity, it)
                val layoutManager=LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                mainFragmentBinding.rvBlogList.layoutManager=layoutManager
                mainFragmentBinding.rvBlogList.adapter=blogAdapter
                //  Log.d("TAG",it.toString())
                blogAdapter.setOnFavouriteClickListener(object :
                    BlogAdapter.OnFavouriteImageClick {
                    override fun OnFavouriteBlogClick(blogData: BlogData) {
                        blogViewModel.markBlogAsFavourite(requireActivity(),blogData)
                        Toast.makeText(activity,getString(R.string.str_addBlogToFavourite), Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }

    private fun onSearchImageClick() {
        mainFragmentBinding.ivSearch.setOnClickListener {
            if (isBlogSearch) {
                mainFragmentBinding.llBlogSearch.visibility = View.VISIBLE
                mainFragmentBinding.ivSearch.visibility = View.GONE
                searchBlogs()
            }
           /* else{
                mainFragmentBinding.llBlogSearch.visibility=View.GONE
                mainFragmentBinding.ivSearch.visibility=View.VISIBLE
                isBlogSearch=false
            }*/
        }
    }


   @SuppressLint("SuspiciousIndentation")
   private fun searchBlogs()
   {

       val activity=activity as DashboardActivity
            mainFragmentBinding.etSearchBlogs.addTextChangedListener(object :TextWatcher
            {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    val strQuery:String=s.toString()
                    blogViewModel.getBlogsBySearch(requireActivity(),strQuery).observe(requireActivity(),Observer<MutableList<BlogData>>
                    {
                        try {
                             blogAdapter.updateData(it)
                             blogAdapter.notifyDataSetChanged()
                             blogAdapter.setOnFavouriteClickListener(object :
                                BlogAdapter.OnFavouriteImageClick {
                                override fun OnFavouriteBlogClick(blogData: BlogData) {
                                    blogViewModel.markBlogAsFavourite(requireActivity(), blogData)
                                    Toast.makeText(activity, getString(R.string.str_addBlogToFavourite), Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        catch (e:Exception)
                        {
                            e.printStackTrace()
                            Toast.makeText(activity,"Error Updating data :${e.message}",Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            })
   }



    override fun onResume() {
        super.onResume()
        onSearchImageClick()
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







