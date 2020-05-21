package com.example.doubtnuttestapp.view

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

//@Entity(tableName = "NewsList")
data class NewListInput(
   // @PrimaryKey(autoGenerate = true)
                        val status : String,
                        val totalResults : Int,
    val articles : MutableList<ArticleList>? = null)

data class ArticleList(val source : Source,
                        val author : String,
                       val title : String,
                       val description : String,
                       val url : String,
                       val urlToImage : String,
                       val publishedAt : String,
                       val content : String)

data class Source(val name : String)/*val id : String,*/