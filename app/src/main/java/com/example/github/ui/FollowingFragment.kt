package com.example.github.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.R
import com.example.github.adapter.UserAdapter
import com.example.github.data.response.ItemsItem
import com.example.github.databinding.FragmentFollowBinding

class FollowingFragment : Fragment(R.layout.fragment_follow) {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private val followingViewModel by viewModels<FollowingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)
        val args = arguments
        val username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        followingViewModel.setListFollowing(username)
        followingViewModel.listFollowing.observe(viewLifecycleOwner) { listFollowing ->
            setListFollowing(listFollowing)
        }

        layout()
    }

    private fun layout() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvUser.layoutManager = layoutManager
    }

    private fun setListFollowing(listFollowing: List<ItemsItem>) {
        val adapter = UserAdapter()
        adapter.submitList(listFollowing)
        binding.rvUser.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}