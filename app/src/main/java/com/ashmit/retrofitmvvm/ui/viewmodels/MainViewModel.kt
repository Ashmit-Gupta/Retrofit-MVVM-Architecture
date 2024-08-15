package com.ashmit.retrofitmvvm.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashmit.retrofitmvvm.data.model.QuoteList
import com.ashmit.retrofitmvvm.data.repository.QuoteRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {
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