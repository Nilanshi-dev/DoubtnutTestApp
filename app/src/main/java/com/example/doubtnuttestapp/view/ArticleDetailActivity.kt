package com.example.doubtnuttestapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.doubtnuttestapp.R
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val articleDescription = intent.getStringExtra("articleDescription")
        val articleImage = intent.getStringExtra("articleImage")

        tvArticleDetail.text = articleDescription
        Glide.with(this)
            .load(articleImage)
            .into(ivNewsImage)
    }
}
