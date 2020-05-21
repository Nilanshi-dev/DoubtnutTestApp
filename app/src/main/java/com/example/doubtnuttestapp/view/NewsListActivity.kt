package com.example.doubtnuttestapp.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doubtnuttestapp.NetworkUtils
import com.example.doubtnuttestapp.R
import com.example.doubtnuttestapp.Resource
import kotlinx.android.synthetic.main.activity_new_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListActivity : AppCompatActivity() {
    private val accountDetailViewModel: NewsListViewModel by viewModel()
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)
        setProgressBar()
        newListData()
    }

    private fun newListData() {
        accountDetailViewModel.getNewList(NetworkUtils.isNetworkConnected(this))
            .observe(this,
                androidx.lifecycle.Observer {
                    when (it.status) {
                        Resource.LOADING -> {
                            progressDialog?.show()
                        }
                        Resource.SUCCESS -> {
                            progressDialog?.dismiss()
                            val dataList = it.data as NewListInput
                            getNewListView(dataList.articles!!)
                        }
                        Resource.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                })
    }

    private fun setProgressBar() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(getString(R.string.uploading))
    }

    private fun getNewListView(articleList: MutableList<ArticleList>) {
        rvArticleList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ArticleListAdapter(articleList) {
                articleListClick(it, articleList)
            }
        }
    }

    private fun articleListClick(id: Int, articleList: MutableList<ArticleList>) {
        startActivity(Intent(this, ArticleDetailActivity::class.java).apply {
            putExtra("articleImage", articleList[id].urlToImage)
            putExtra("articleDescription", articleList[id].content)
        })
    }
}
