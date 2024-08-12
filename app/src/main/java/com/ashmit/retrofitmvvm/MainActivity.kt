package com.ashmit.retrofitmvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ashmit.retrofitmvvm.data.api.QuoteService
import com.ashmit.retrofitmvvm.data.api.RetrofitHelper
import com.ashmit.retrofitmvvm.data.repository.QuoteRepository
import com.ashmit.retrofitmvvm.databinding.ActivityMainBinding
import com.ashmit.retrofitmvvm.viewmodels.MainViewModel
import com.ashmit.retrofitmvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val repository = QuoteRepository(quoteService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this) { quoteList ->
            if (quoteList != null) {
                binding.result = quoteList
                Log.d("DATA", quoteList.results.toString())
            } else {
                Log.e("ERROR", "Failed to load quotes.")
            }
        }
    }
}
