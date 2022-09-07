package com.example.fruitdoc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fruitdoc.databinding.FragmentAboutHortLifeProjectBinding


class AboutHortLifeProjectFragment : Fragment() {
    private var _binding: FragmentAboutHortLifeProjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutHortLifeProjectBinding.inflate(inflater,container,false)
        return binding.root
    }
}