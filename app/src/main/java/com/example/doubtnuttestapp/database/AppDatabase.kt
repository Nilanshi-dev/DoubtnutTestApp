package com.example.doubtnuttestapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.doubtnuttestapp.view.NewListInput


//@Database(entities = [NewListInput::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    private var sInstance: AppDatabase? = null
    private val LOCK = Any()
    private val LOG_TAG = AppDatabase::class.java.simpleName
    private val DATABASE_NAME = "personlist"

    abstract fun newListDao(): NewsDao?
    open fun getInstance(context: Context): AppDatabase? {
        if (sInstance == null) {
            synchronized(LOCK) {
                Log.d(LOG_TAG, "Creating new database instance")
                sInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java, DATABASE_NAME
                )
                    .build()
            }
        }
        Log.d(LOG_TAG, "Getting the database instance")
        return sInstance
    }


}