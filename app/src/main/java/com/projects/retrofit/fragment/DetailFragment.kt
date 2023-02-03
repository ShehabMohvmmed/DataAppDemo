package com.projects.retrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.retrofit.MainViewModel
import com.projects.retrofit.databinding.FragmentDetailBinding
import com.projects.retrofit.model.Post

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val post = DetailFragmentArgs.fromBundle(requireArguments()).selectedPost

        binding.fragmentPost = post






        return binding.root
    }


}