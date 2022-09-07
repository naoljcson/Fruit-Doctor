package com.example.fruitdoc.view

import android.R.color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fruitdoc.R
import com.example.fruitdoc.databinding.FragmentFirstBinding
import com.example.fruitdoc.util.Constants.PROBLEM_ID
import com.example.fruitdoc.util.PreferenceHelper
import com.example.fruitdoc.viewModel.ProblemViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


//import com.example.vegdoc.view.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var problemViewModel: ProblemViewModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        if(arguments == null)  return binding.root
        val problemId = requireArguments().getInt(PROBLEM_ID);
        val preference = PreferenceHelper(requireContext())
        problemViewModel = ViewModelProvider(this)[ProblemViewModel::class.java]
        GlobalScope.launch(Dispatchers.Main) {
            val selectedProblem = problemViewModel.problemsById(problemId)
            var description = selectedProblem.description
            var recommendation = selectedProblem.recommendation
            if(preference.language == "am"){
                description = selectedProblem.amharicDescription
                recommendation = selectedProblem.amharicRecommendation
            }

//            if (description != null) {
//                description = description.replace("\\n",System.getProperty("line.separator") ?: "")
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    binding.detailInfoBodyDescription.text = Html.fromHtml(description,Html.FROM_HTML_MODE_LEGACY)
//                }else{
//                    binding.detailInfoBodyDescription.text = Html.fromHtml(description)
//                }
//            }

            if (description != null) {
                formatDescription(description)
            }

            if (!recommendation.isNullOrBlank())
                addBullets(recommendation)
            else{
                binding.detailInfoRecommendationBody.visibility = View.GONE
                binding.detailInfoRecommendationTitle.visibility = View.GONE
            }

        }
        return binding.root
    }

    private fun addBullets(text: String) {
        Log.i("FirstFragment", "addBullets: $text ")
        val textBody = text.replace(" ".toRegex(),"#")
            .replace("\\s".toRegex(), "")
            .replace("#+".toRegex(), " ")
        val arr = textBody.split("\\n").toTypedArray()
        val bulletGap: Int = (resources.displayMetrics.density * 14).toInt()
        val spannableStringBuilder = SpannableStringBuilder()
        for (line in arr) {
            val spannableString = SpannableString(line)
            if(line.length > 5 ){
            if(!line.contains("{{")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    spannableString.setSpan(BulletSpan(24, ContextCompat.getColor(requireContext(),
                        R.color.secondaryDarkColor), 10), 0, line.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }else{
                    spannableString.setSpan(BulletSpan(bulletGap), 0, line.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                spannableStringBuilder.append(spannableString)
                spannableStringBuilder.append(System.getProperty("line.separator"))
            }else{
                Log.i("FirstFragment", "addBullets 222 : $line ")
                val title = line.substringAfter("{{").substringBefore("}}")
                val spanString = SpannableString(title)
                spanString.setSpan(UnderlineSpan(), 0, title.length, 0)
                spanString.setSpan(StyleSpan(Typeface.BOLD), 0, title.length, 0)
                spannableStringBuilder.append(spanString)
                spannableStringBuilder.append("\n")
             }
            }
        }
        spannableStringBuilder.also { binding.detailInfoRecommendationBody.text = it }
    }

    private fun formatDescription(text: String) {
        Log.i("FirstFragment", "addBullets: $text ")
        val textBody = text.replace(" ".toRegex(),"#")
            .replace("\\s".toRegex(), "")
            .replace("#+".toRegex(), " ")
        val arr = textBody.split("\\n").toTypedArray()
        val bulletGap: Int = (resources.displayMetrics.density * 14).toInt()
        val spannableStringBuilder = SpannableStringBuilder()
        for ((i, line) in arr.withIndex()) {
            val spannableString = SpannableString(line)
            if(i > 0){
                if(line.length > 5 ){
                    if(!line.contains("{{")){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            spannableString.setSpan(BulletSpan(24, ContextCompat.getColor(requireContext(),
                                R.color.purple_700), 8), 0, line.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }else{
                            spannableString.setSpan(BulletSpan(bulletGap), 0, line.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                        spannableStringBuilder.append(spannableString)
                        spannableStringBuilder.append(System.getProperty("line.separator"))
                    }else{
                        Log.i("FirstFragment", "addBullets 222 : $line ")
                        val title = line.substringAfter("{{").substringBefore("}}")
                        val spanString = SpannableString(title)
                        spanString.setSpan(UnderlineSpan(), 0, title.length, 0)
                        spanString.setSpan(StyleSpan(Typeface.BOLD), 0, title.length, 0)
                        spannableStringBuilder.append(spanString)
                        spannableStringBuilder.append("\n")
                    }
                }
            }else{
                spannableStringBuilder.append(spannableString)
                spannableStringBuilder.append(System.getProperty("line.separator"))
            }
        }
        spannableStringBuilder.also { binding.detailInfoBodyDescription.text = it }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}