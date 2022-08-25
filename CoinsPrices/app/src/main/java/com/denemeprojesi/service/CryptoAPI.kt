package com.denemeprojesi.service

import com.denemeprojesi.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {


    @GET("prices?key=yourapikey")  //url'nin başı main sınıfta
    fun getData():Call<List<CryptoModel>>
}