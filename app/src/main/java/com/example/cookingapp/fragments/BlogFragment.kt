package com.example.cookingapp.fragments

import android.app.ProgressDialog
import android.os.Bundle
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
import com.example.cookingapp.model.BlogData
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
    private lateinit var etblogTitle:EditText
    private lateinit var etblogDescription:EditText
    private lateinit var etblogPlace:EditText
    private lateinit var textViewAdd: TextView
    private lateinit var blogViewModel:BlogViewModel
    private  var blogRepository:BlogRepository=BlogRepository()

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
         val view= inflater.inflate(R.layout.fragment_blog, container, false)
        blogViewModel=ViewModelProvider(this,BlogViewModelFactory(blogRepository)).get(BlogViewModel::class.java)
         return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etblogTitle=view.findViewById(R.id.et_blogtitle)
        etblogDescription=view.findViewById(R.id.et_blogdescription)
        etblogPlace=view.findViewById(R.id.et_blogplace)
        textViewAdd=view.findViewById(R.id.tv_addblog)
        textViewAdd.setOnClickListener {
            val strBlogTitle=etblogTitle.text.toString()
            val strBlogDescription=etblogDescription.text.toString()
            val strBlogPlace=etblogPlace.text.toString()

            if (strBlogTitle.isEmpty()|| strBlogDescription.isEmpty()||strBlogPlace.isEmpty())
            {
                Toast.makeText(activity,"Please Fill All The Details",Toast.LENGTH_SHORT).show()
            }

            else
            {
                    blogViewModel.AddBlog(requireActivity(),strBlogTitle,strBlogDescription,strBlogPlace)
                    Toast.makeText(activity,"Details Added Successfully",Toast.LENGTH_SHORT).show()
                    ClearBlog()
            }
        }

    }

    private fun ClearBlog()
    {
        etblogDescription.setText("")
        etblogTitle.setText("")
        etblogPlace.setText("")
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

    override fun onDestroy() {
        super.onDestroy()
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