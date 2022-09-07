package com.example.fruitdoc.view.ui.main


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fruitdoc.R
import com.example.fruitdoc.util.Constants.PROBLEM_ID
import com.example.fruitdoc.view.FirstFragment
import com.example.fruitdoc.view.SecondFragment
import com.example.fruitdoc.view.ThirdFragment



private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager,private val problemId: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val args = Bundle()
        args.putInt(PROBLEM_ID, problemId)
        var fragment:Fragment = FirstFragment()
        if (position == 0){
            fragment.arguments = args
        }else if(position == 1){
            fragment = SecondFragment()
            fragment.arguments = args
        }else if(position == 2){
            fragment = ThirdFragment()
            fragment.arguments = args
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }
}