package com.example.github.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.adapter.UserAdapter
import com.example.github.data.response.ItemsItem
import com.example.github.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mainViewModel.listUser.observe(this) { user ->
            setListUsers(user)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        layout()
        searchUser()
    }

    private fun layout() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)
    }

    private fun searchUser() {
        binding.apply {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    mainViewModel.findUser(searchView.text.toString())
                    searchView.hide()
                    false
                }
        }
    }

    private fun setListUsers(listUser: List<ItemsItem>) {
        val adapter = UserAdapter()
        adapter.submitList(listUser)
        binding.rvReview.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val mBuilder = AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Yakin mau keluar aplikasi?")
            .setPositiveButton("Ya", null)
            .setNegativeButton("Tidak", null)
            .show()

        val mPositiveButton = mBuilder.getButton(AlertDialog.BUTTON_POSITIVE)
        mPositiveButton.setOnClickListener {
            exitProcess(0)
        }
    }
}