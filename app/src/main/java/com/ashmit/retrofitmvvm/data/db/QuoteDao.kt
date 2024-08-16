package com.ashmit.retrofitmvvm.data.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ashmit.retrofitmvvm.data.model.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote")
    fun getQuotes() : List<Quote>

    @Insert
    suspend fun addQuotes(quotes : List<Quote>)
}