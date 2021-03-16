package com.aspiration.interview.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.aspiration.interview.InterviewTaskApplication
import com.aspiration.interview.R
import com.aspiration.interview.data.models.ListItem
import com.aspiration.interview.data.models.Post
import com.aspiration.interview.databinding.ActivityMainBinding
import com.aspiration.interview.presentation.base.BaseActivity
import javax.inject.Inject

class MainActivity :
    BaseActivity<MainAgreement.Presenter, MainAgreement.View>(),
    MainAgreement.View {

    lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: ListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }

    override fun setupInputs() {
        InterviewTaskApplication.applicationComponent?.inject(this)

        listAdapter = ListItemAdapter()
        binding.rvListPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
        binding.btnFetch.bindClick {
            presenter.loadPosts()
        }
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showPosts(posts: List<ListItem>) {
        listAdapter.setData(posts)
        binding.tvError.visibility = View.GONE
        binding.btnFetch.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_posts), Toast.LENGTH_LONG).show()
    }

    override fun returnThisHerePlease(): MainAgreement.View = this

}