package com.projects.retrofit.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.projects.retrofit.MainViewModel
import com.projects.retrofit.R
import com.projects.retrofit.adapter.MyAdapter
import com.projects.retrofit.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(requireActivity().application)).get(MainViewModel::class.java)
    }
    private val myAdapter = MyAdapter(MyAdapter.PostListener{
        viewModel.onSelectedPost(it)
    })

    private lateinit var orderOption: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        setUpRecyclerView()

        viewModel.postsList.observe(requireActivity()) { post ->
            //let calls the specified function block with this value as its argument and returns its result
            post.let { myAdapter.setData(it) }

        }

        viewModel.onlineStatus.observe(requireActivity()) { status ->
            status.let {
               if (status!!) {
                   Toast.makeText(requireContext(),"data from server",Toast.LENGTH_SHORT).show()
               } else {
                   Toast.makeText(requireContext(),"data from data base",Toast.LENGTH_SHORT).show()
//                viewModel.onlineStatus.value =  false
               }
            }
        }
//        viewModel.onlineStatus.observeOnce(requireActivity()) { status -> {
//            if (status != null) {
//                Toast.makeText(requireContext(),"data from server",Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(requireContext(),"data from data base",Toast.LENGTH_SHORT).show()
////                viewModel.onlineStatus.value =  false
//            }
////            viewModel.onlineStatus.removeObserver(status ->{})
//        }
//
//        }

//        val livedata = model.getDownloadByContentId(contentId)
//        livedata.observeOnce((AppCompatActivity) context, Observer<T> {
//            if (it != null) {
//                DownloadManager.this.downloadManagerListener.onDownloadManagerFailed(null, "this item already exists");
//            }
//            startDownload();
//        })



        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner) { post ->
            if (post != null) {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(post)
                findNavController().navigate(action)
                viewModel.onPostNavigated()
            }
        }




       Glide.with(this)
        .load("https://picsum.photos/200/300")
            .into(binding.imageOfTheDay)





        return binding.root
    }
//
//    override fun onPause() {
//        viewModel.onlineStatus.removeObservers(this)
//        super.onPause()
//    }

    private fun setUpRecyclerView() {
        binding.recyclerview.adapter = myAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_items, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onFilterSelected(
            when(item.itemId){
                R.id.show_50 -> {MainViewModel.PostsFilters.FIRST50}
                R.id.show_above50 -> { MainViewModel.PostsFilters.LAST51 }
                 else -> { MainViewModel.PostsFilters.ALL }
            }
        )
        return true
    }

//    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
//        observe(lifecycleOwner, object : Observer<T> {
//            override fun onChanged(t: T?) {
//                observer.onChanged(t)
//                removeObserver(this)
//            }
//        })
//    }

    override fun onPause() {
        viewModel.onlineStatus.removeObservers(this)
        super.onPause()
    }

    override fun onStop() {
        viewModel.onlineStatus.removeObservers(this)
        super.onStop()
    }

}