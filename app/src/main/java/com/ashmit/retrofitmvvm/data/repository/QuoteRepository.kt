package com.ashmit.retrofitmvvm.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashmit.retrofitmvvm.data.api.QuoteService
import com.ashmit.retrofitmvvm.data.db.QuoteDao
import com.ashmit.retrofitmvvm.data.model.Quote
import com.ashmit.retrofitmvvm.data.model.QuoteList
import com.ashmit.retrofitmvvm.data.networkutil.NetworkUtil
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteService: QuoteService,
    private val quoteDao :QuoteDao,
    private val context : Context
) {
    private val quotesLiveData = MutableLiveData<QuoteList>()
    val quotes :LiveData<QuoteList>
        get() {
            return quotesLiveData
        }

    suspend fun getQuote(page:Int){
        try{
            //if the internet is available
            if(NetworkUtil.isInternetAvailable(context)){
                val result = quoteService.getQuotes(page)
                if(result.isSuccessful){
                    result.body()?.let {
                        quoteDao.addQuotes(it.results)
                    }
                    quotesLiveData.postValue(result.body())
                }else{
                    Log.d("ERROR" , "unable to getQuote : ${result.message()}")
                }
            }else{ // if the internet is not available then we will use teh room for getting the data from the database
                //getting the quotes from the db
                val quotes =quoteDao.getQuotes()
                //creating a dummy quote list with the result as the data from the db as we are only using the quotes and not the other things thats why we have set them to 1

                val quoteList = QuoteList(1,1,1,quotes,1,1)
                //posting the data to the live data
                quotesLiveData.postValue(quoteList)
            }
        }catch(e:Exception){
            Log.d("ERROR" , "unable to getQuote : ${e.message}")
            e.printStackTrace()
        }

    }
}