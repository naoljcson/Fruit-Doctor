package com.example.fruitdoc.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitdoc.R
import com.example.fruitdoc.adapter.FruitAdapter
import com.example.fruitdoc.databinding.FragmentHomeBinding
import com.example.fruitdoc.model.Fruit
import com.example.fruitdoc.util.PreferenceHelper
import com.example.fruitdoc.viewModel.FruitViewModel


class HomeFragment : Fragment(), FruitAdapter.OnRecyclerViewItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FruitViewModel
    private lateinit var fruitAdapter: FruitAdapter
    private val fruits = mutableListOf<Fruit>()
    private lateinit var preference: PreferenceHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val vegetableRecyclerView: RecyclerView = binding.fruitRecyclerView
        vegetableRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this)[FruitViewModel::class.java]

        fruitAdapter = FruitAdapter(fruits, this)
        preference = PreferenceHelper(requireContext())
        vegetableRecyclerView.adapter = fruitAdapter

        observeEvents()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeEvents() {
        viewModel.allEvents.observe(viewLifecycleOwner) { list ->
            list?.let {
                fruits.clear()
                fruits.addAll(list)
                fruitAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(index: Int) {
        val vegetableName = if (preference.language == "am")
            fruits[index].amharicName
        else
            fruits[index].name

        Navigation.createNavigateOnClickListener(
            R.id.action_nav_home_to_nav_disorder,
            bundleOf("id" to fruits[index].id, "name" to vegetableName)
        ).onClick(view)
    }
}