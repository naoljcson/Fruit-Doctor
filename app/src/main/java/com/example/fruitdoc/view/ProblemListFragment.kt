package com.example.fruitdoc.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitdoc.MainActivity
import com.example.fruitdoc.R
import com.example.fruitdoc.adapter.ProblemAdapter
import com.example.fruitdoc.databinding.FragmentProblemListBinding
import com.example.fruitdoc.model.Problem
import com.example.fruitdoc.util.PreferenceHelper
import com.example.fruitdoc.view.disorders.DisordersFragmentArgs
import com.example.fruitdoc.viewModel.ProblemViewModel


class ProblemListFragment : Fragment(), ProblemAdapter.OnRecyclerViewItemClickListener {
    // TODO: Rename and change types of parameters
    private val args: DisordersFragmentArgs by navArgs()
    private var _binding: FragmentProblemListBinding? = null
    private val binding get() = _binding!!
    private lateinit var problemAdapter: ProblemAdapter
    private val problems = mutableListOf<Problem>()

    private lateinit var problemViewModel: ProblemViewModel
    private var fruitId: Int = 0
    private var type: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProblemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fruitId = args.id
        type = args.type
        problemViewModel = ViewModelProvider(this)[ProblemViewModel::class.java]
        val problemListRecyclerView: RecyclerView = binding.problemListRecyclerView
        problemListRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        problemAdapter = ProblemAdapter(problems, this)

        problemListRecyclerView.adapter = problemAdapter
        observeEvents()
    }

    override fun onResume() {
        super.onResume()
        if(activity != null){
            var title: String = ""
            when(args.type){
                "Pest" -> title =  getString(R.string.pest)
                "Disease"-> title =  getString(R.string.disease)
                "Disorder"-> title =  getString(R.string.disorder)
            }
            (activity as MainActivity).setTitle(title)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeEvents() {
        problemViewModel.allProblems.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                problems.clear()
                list.forEach { problem ->
                    if (problem.fruitId == fruitId && problem.type == type)
                        problems.add(problem)
                }
                problemAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(index: Int) {
        val id = problems[index].id
        val name = if (PreferenceHelper(requireContext()).language == "am")
            problems[index].amharicName
        else
            problems[index].name
        Navigation.createNavigateOnClickListener(
            R.id.action_problemListFragment_to_disorderDetailFragment,
            bundleOf("id" to id, "name" to name)
        ).onClick(view)
    }
}