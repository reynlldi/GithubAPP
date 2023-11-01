package com.example.github.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.adapter.SectionsPagerAdapter
import com.example.github.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val detailUserViewModel: DetailUserViewModel by viewModels()

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle().apply {
            putString(EXTRA_USERNAME, username)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs,viewPager){tab, position ->
            tab.text= resources.getString(TAB_TITLES[position])
        }.attach()

        detailUserViewModel.setDetailUser(username.toString())
        detailUserViewModel.detailUser.observe(this) {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .centerCrop()
                        .into(tvUser)
                    tvUsername.text = it.name
                    tvBio.text = it.bio
                    tvLocation.text = it.location
                    "${it.followers} \nFollowers".also { tvFollowers.text = it }
                    "${it.following} \nFollowing".also { tvFollowing.text = it }
                    "${it.publicRepos} \nRepo".also { tvRepo.text = it }
                }
            }
        }

        detailUserViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        @Suppress("DEPRECATION")
        onBackPressed()
        return true
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}