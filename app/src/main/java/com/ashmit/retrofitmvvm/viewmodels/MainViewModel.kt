package com.ashmit.retrofitmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashmit.retrofitmvvm.data.model.QuoteList
import com.ashmit.retrofitmvvm.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {
    init{
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote(1)
        }
    }
    val quotes :LiveData<QuoteList>
        get() {
            return repository.quotes
        }

}