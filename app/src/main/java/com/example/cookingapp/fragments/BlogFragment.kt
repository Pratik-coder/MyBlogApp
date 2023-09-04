package com.example.cookingapp.fragments

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cookingapp.R
import com.example.cookingapp.databinding.FragmentBlogBinding
import com.example.cookingapp.model.BlogData
import com.example.cookingapp.preferences.MySharedPreferences
import com.example.cookingapp.repository.BlogRepository
import com.example.cookingapp.roomdatabase.BlogDatabase
import com.example.cookingapp.viewmodel.BlogViewModel
import com.example.cookingapp.viewmodelfactory.BlogViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var blogBinding: FragmentBlogBinding
    private lateinit var blogViewModel:BlogViewModel
    private  var blogRepository:BlogRepository=BlogRepository()
    private lateinit var preferences:MySharedPreferences


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
        // val view= inflater.inflate(R.layout.fragment_blog, container, false)
        blogBinding=FragmentBlogBinding.inflate(inflater)
        preferences= MySharedPreferences(requireActivity())
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
        return blogBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    override fun onStart() {
        super.onStart()
    }



    private fun setObservers()
    {
        blogBinding.tvAddblog.setOnClickListener {
            val strBlogTitle=blogBinding.etBlogtitle.text.toString()
            val strBlogDescription=blogBinding.etBlogdescription.text.toString()
            val strBlogPlace=blogBinding.etBlogplace.text.toString()

            if (TextUtils.isEmpty(strBlogTitle))
            {
                Toast.makeText(activity,getString(R.string.str_title),Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(strBlogDescription))
            {
                Toast.makeText(activity,getString(R.string.str_description),Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(strBlogPlace))
            {
                Toast.makeText(activity,getString(R.string.str_place),Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            blogViewModel.AddBlog(requireActivity(),strBlogTitle,strBlogDescription,strBlogPlace)
            Toast.makeText(activity,getString(R.string.str_blogsuccessfull),Toast.LENGTH_SHORT).show()
            ClearBlog()
        }
    }

    private fun ClearBlog()
    {
       blogBinding.etBlogtitle.setText("")
       blogBinding.etBlogdescription.setText("")
       blogBinding.etBlogplace.setText("")
    }


    override fun onResume() {
        super.onResume()
        preferences.setTextTitle(blogBinding.etBlogtitle.text.toString())
        preferences.setTextDescription(blogBinding.etBlogdescription.text.toString())
        preferences.setTextPlace(blogBinding.etBlogplace.text.toString())
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        ClearBlog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}