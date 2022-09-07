package com.example.fruitdoc.view.disorders

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.fruitdoc.MainActivity
import com.example.fruitdoc.R
import com.example.fruitdoc.databinding.FragmentDisordersBinding
import com.example.fruitdoc.model.Problem
import com.example.fruitdoc.util.PreferenceHelper
import com.example.fruitdoc.view.home.HomeFragmentArgs
import com.example.fruitdoc.viewModel.ProblemViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DisordersFragment : Fragment() {

    private var _binding: FragmentDisordersBinding? = null
    private val binding get() = _binding!!

    private lateinit var problemViewModel: ProblemViewModel
    private var fruitId: Int = 0

    private var peasts: List<Problem> = emptyList()
    private var diseases: List<Problem> = emptyList()
    private var disorders: List<Problem> = emptyList()
    private var toolBarTitle: String = ""

    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDisordersBinding.inflate(inflater, container, false)

        fruitId = args.id

        problemViewModel = ViewModelProvider(this)[ProblemViewModel::class.java]

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            val selectedVegetable = problemViewModel.fruitByProblemId(fruitId)
            toolBarTitle = if (PreferenceHelper(requireContext()).language == "am")
                selectedVegetable.amharicName
            else
                selectedVegetable.name
            (activity as MainActivity).setTitle(toolBarTitle)
            peasts = problemViewModel.allProblemsByFruitId(fruitId, "Pest")
            diseases = problemViewModel.allProblemsByFruitId(fruitId, "Disease")
            disorders = problemViewModel.allProblemsByFruitId(fruitId, "Disorder")
            val numberOfElement = getString(R.string.numberOfElement)
            with(binding) {
                numberOfPeasts.text = "$numberOfElement ${peasts.count()}"
                numberOfDiseases.text = "$numberOfElement ${diseases.count()}"
                numberOfDisOrders.text = "$numberOfElement ${disorders.count()}"

                peastCard.setOnClickListener {
                    openDisorderDetail(
                        "Pest"
                    )
                }
                diseaseCard.setOnClickListener {
                    openDisorderDetail(
                        "Disease"
                    )
                }
                disorderCard.setOnClickListener {
                    openDisorderDetail(
                        "Disorder"
                    )
                }
            }
        }
    }

    private fun openDisorderDetail(type: String) {
        Navigation.createNavigateOnClickListener(
            R.id.action_nav_disorder_to_problemListFragment,
            bundleOf("id" to fruitId, "type" to type)
        ).onClick(view)
    }
}
