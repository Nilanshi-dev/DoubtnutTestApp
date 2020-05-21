package com.example.doubtnuttestapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.doubtnuttestapp.view.NewListInput

@Dao
interface NewsDao {
    /*@get:Query("SELECT * FROM NewsList")
    val productList: List<NewListInput>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: NewListInput?)*/
}