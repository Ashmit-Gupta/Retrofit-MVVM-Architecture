package com.ashmit.retrofitmvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashmit.retrofitmvvm.data.api.QuoteService
import com.ashmit.retrofitmvvm.data.model.QuoteList
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteService: QuoteService) {
    private val quotesLiveData = MutableLiveData<QuoteList>()
    val quotes :LiveData<QuoteList>
        get() {
            return quotesLiveData
        }

    suspend fun getQuote(page:Int){
        try{
            val result = quoteService.getQuotes(page)
            if(result.isSuccessful){
                quotesLiveData.postValue(result.body())
            }else{
                Log.d("ERROR" , "unable to getQuote : ${result.message()}")
            }

        }catch(e:Exception){
            Log.d("ERROR" , "unable to getQuote : ${e.message}")
            e.printStackTrace()
        }

    }
}