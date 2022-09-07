package com.example.fruitdoc.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.fruitdoc.databinding.FragmentThirdBinding
import com.example.fruitdoc.model.Fruit
import com.example.fruitdoc.util.Constants
import com.example.fruitdoc.viewModel.ProblemViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ThirdFragment : Fragment() {
     private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var problemViewModel: ProblemViewModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        if(arguments == null)  return binding.root
        val problemId = requireArguments().getInt(Constants.PROBLEM_ID);

        problemViewModel = ViewModelProvider(this, )[ProblemViewModel::class.java]

        GlobalScope.launch(Dispatchers.Main) {
            val selectedProblem = problemViewModel.problemsById(problemId)
            var fruitId: Int = 0
            if (selectedProblem != null) {
                fruitId = selectedProblem.fruitId!!
            }
            val fruit: Fruit =
                problemViewModel.fruitByProblemId(fruitId)
            val selectedProblemName = selectedProblem.name.lowercase()
                .replace(",", "")
                .replace("/", "")
                .replace("&", "")
                .replace("(", "")
                .replace(")", "")
                .replace("  ", " ")
                .trim()
                .replace(' ', '_')
     if(fruit != null){
         val image1: ImageView = binding.imageView1
         val image2: ImageView = binding.imageView2
         val image3: ImageView = binding.imageView3

         Log.i("ThirdFragment", fruit.name)

         val problemName = "img_${fruit.name.lowercase()}_$selectedProblemName"
         val packageName = requireContext().packageName
         var resourceName = "${problemName}1"
         Log.i("ThirdFragment", resourceName)
         var resId =
             requireContext().resources.getIdentifier(resourceName, "drawable", packageName)
         image1.setImageResource(resId)
         resourceName = "${problemName}2"
         Log.i("ThirdFragment", resourceName)
         resId = requireContext().resources.getIdentifier(resourceName, "drawable", packageName)
         image2.setImageResource(resId)
         resourceName = "${problemName}3"
         Log.i("ThirdFragment", resourceName)
         resId = requireContext().resources.getIdentifier(resourceName, "drawable", packageName)
         image3.setImageResource(resId)
     }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}