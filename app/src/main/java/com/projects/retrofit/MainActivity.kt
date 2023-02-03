package com.projects.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.retrofit.adapter.MyAdapter
import com.projects.retrofit.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {


//    private lateinit var binding: ActivityMainBinding
//    private lateinit var viewModel: MainViewModel
//    private val myAdapter by lazy { MyAdapter() }
//
//    private lateinit var orderOption: String
//    private lateinit var sortOption: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        //       setUpRecyclerView()


        //   val myPostsList = viewModel.getPostsList(orderOption)

//        viewModel = ViewModelProvider(
//            this,
//            MainViewModel.Factory(application)
//        ).get(MainViewModel::class.java)
//        viewModel.postsList.observe(this) { post ->
//            //let calls the specified function block with this value as its argument and returns its result
//            post.let { myAdapter.setData(it) }
//        }

//        val orderArr = resources.getStringArray(R.array.order)
//        val arrayAdapterOrder = ArrayAdapter(this,R.layout.list_item,orderArr)
//        binding.autoCompleteOrder.setAdapter(arrayAdapterOrder)
//        binding.autoCompleteOrder.setOnItemClickListener { parent, view, position, id ->
//            val orderOptionSelected = parent.getItemAtPosition(position).toString()
//            orderOption = orderOptionSelected
//        }
//
//
//    }
//
//    private fun setUpRecyclerView() {
//        binding.recyclerview.adapter = myAdapter
//        binding.recyclerview.layoutManager = LinearLayoutManager(this)
//
//    }
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu_items, menu)
//        return true
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//         viewModel.onFilterSelected(
//             when (item.itemId) {
//                 R.id.show_50 -> MainViewModel.PostsFilters.FIRST50
//                 R.id.show_above50 -> MainViewModel.PostsFilters.LAST51
//                 else -> MainViewModel.PostsFilters.ALL
//             }
//         )
//        return true
//    }
}








