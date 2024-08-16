package com.ashmit.retrofitmvvm.data.api

import com.ashmit.retrofitmvvm.data.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {
//these will always return response , we have to use the live data in the repo to reflect changes , simple si baat tu query hit kara ke live data kaise maang sakta h , tune yaha se response liya repo mai store karava vo bhi live Data mai usme check hoga na jo algli baar response aya h usme kuch changes hue h ki nhi

    /* Retrofit's Response object is used to make network requests and receive responses. It doesn't inherently provide LiveData. You use LiveData in your repository to observe changes to data.*/

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page:Int):Response<QuoteList>

}