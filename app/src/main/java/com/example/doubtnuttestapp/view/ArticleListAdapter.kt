package com.example.doubtnuttestapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doubtnuttestapp.R
import kotlinx.android.synthetic.main.article_list.view.*

class ArticleListAdapter(private val articleList: MutableList<ArticleList>, private var click: (Int) -> Unit) : RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder>() {

    inner class ArticleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(articleList: ArticleList) {
            itemView.tvTitle.text = articleList.title
            itemView.tvAuthor.text = articleList.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_list, parent, false)
        return ArticleListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.bindItem(articleList[position])

        holder.itemView.clArticle.setOnClickListener {
            click.invoke(position)
            notifyDataSetChanged()
        }
    }
}