//package com.ashmit.retrofitmvvm.ui.viewmodels
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.ashmit.retrofitmvvm.data.repository.QuoteRepository
////used dependency injection instead of factory As factory provides the parameters to the view model so now we have used a dependency injection so we can inject the repository with the dependency injection rather than creating another view model factory for passing the parameter from the main activity
//
//class MainViewModelFactory(private val repository: QuoteRepository) : ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MainViewModel(repository) as T
//    }
//}