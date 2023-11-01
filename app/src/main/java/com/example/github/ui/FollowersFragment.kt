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

class FollowersFragment : Fragment(R.layout.fragment_follow) {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private val followersViewModel: FollowersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)
        val args = arguments
        val username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        followersViewModel.setListFollowers(username)
        followersViewModel.listFollowers.observe(viewLifecycleOwner) { listFollowers ->
            setListFollowers(listFollowers)
        }

        layout()
    }

    private fun layout() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvUser.layoutManager = layoutManager
    }

    private fun setListFollowers(listFollowers: List<ItemsItem>) {
        val adapter = UserAdapter()
        adapter.submitList(listFollowers)
        binding.rvUser.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}