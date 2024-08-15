package com.ashmit.retrofitmvvm.di

import com.ashmit.retrofitmvvm.data.api.QuoteService
import com.ashmit.retrofitmvvm.data.repository.QuoteRepository
import com.ashmit.retrofitmvvm.ui.viewmodels.MainViewModel
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependencyInjector {

    //first we create retrofit instance and Once we have a Retrofit instance, we use it to create an implementation of the service(QuoteService) interface. Retrofit will automatically generate the code for the interface methods based on the annotations we provided. so thats why first we have to create instance and then connect the interface(QuoteService) with retrofit and wherewver we will use QuoteService we will use the Services that shas been automatically implemeted by the retrofit
    @Provides
    @Singleton
    fun provideRetroFitInstance():Retrofit{
        val BASE_URL = "https://quotable.io/"
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideQuoteService(retrofit: Retrofit):QuoteService{
        return retrofit.create(QuoteService::class.java) // here we are connecting the retorfit with the service class and the Retrofit will automatically generate the code for the interface methods based on the annotations we provided.
    }

    @Provides
    @Singleton
    fun provideRepository(quoteService: QuoteService):QuoteRepository{
        return QuoteRepository(quoteService)
    }

    @Provides
    fun provideMainViewModel(repository: QuoteRepository) : MainViewModel {
        return MainViewModel(repository)
    }
}
/*Simplified Explanation:

    Why Can't We Use the Interface Directly?
        We can't directly use an interface or service class in Retrofit because these interfaces are just definitions. They don’t have any actual code behind them yet.

    Creating the Service Class:
        First, we create a service class where we define different interface methods for operations like GET, POST, PUT, etc. These methods tell Retrofit what kind of requests we want to make.

    Setting Up Retrofit:
        Next, we create an instance of Retrofit. Retrofit is a library that helps us make network requests. When we create this instance, we give it the necessary configurations like the base URL, converters, etc.

    Connecting the Interface with Retrofit:
        Once we have a Retrofit instance, we use it to create an implementation of the service interface. Retrofit will automatically generate the code for the interface methods based on the annotations we provided.

    Repository Pattern:
        In a repository class, we pass the service interface (that we created using Retrofit) as a parameter. The repository class uses this interface to make network requests without needing to know how the network call is actually made.

    Using the Repository in ViewModel:
        Finally, the repository is used in the ViewModel to fetch data and perform operations. This keeps the network logic separate from the UI logic.

This way, Retrofit takes care of the complex parts, like making network calls and handling responses, so you only need to focus on defining what you want to do, not how it’s done.
*/