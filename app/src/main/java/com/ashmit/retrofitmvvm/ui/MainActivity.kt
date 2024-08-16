package com.ashmit.retrofitmvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ashmit.retrofitmvvm.R
import com.ashmit.retrofitmvvm.databinding.ActivityMainBinding
import com.ashmit.retrofitmvvm.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    //todo : caching strategies
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
//        val repository = QuoteRepository(quoteService)
//        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

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
