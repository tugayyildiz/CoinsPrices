package com.denemeprojesi.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(

    //@SerializedName("currency") // web servisten gelen değişkenle aynı ad currency diye bişi geleceğini söylüyoruz
    val currency : String ,

    //@SerializedName("price")
    val price : String

    )