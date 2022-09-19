package com.example.fruitdoc.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitdoc.adapter.NotificationAdapter
import com.example.fruitdoc.databinding.FragmentNotificationBinding
import com.example.fruitdoc.viewModel.NotificationViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NotificationFragment : Fragment() {
    private  var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var notificationViewModel: NotificationViewModel

    @SuppressLint("NotifyDataSetChanged")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater,container,false)
        notificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        val notificationRecyclerView: RecyclerView = binding.notificationRecyclerView
        notificationRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        GlobalScope.launch(Dispatchers.Main) {
            val notifications = notificationViewModel.allNotifications()
            Log.d("NotificationFragment", "MessagingService () notifications ${notifications.size}")
            notificationRecyclerView.adapter = NotificationAdapter(notifications)
        }
        return binding.root
    }
}