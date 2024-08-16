package com.ashmit.retrofitmvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashmit.retrofitmvvm.data.model.Quote

@Database(entities = [Quote::class], version = 1 , exportSchema = false)
abstract class QuoteDb : RoomDatabase() {
    abstract fun quoteDao() : QuoteDao
}