package com.denemeprojesi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denemeprojesi.R
import com.denemeprojesi.adapter.RecyclerViewAdapter
import com.denemeprojesi.model.CryptoModel
import com.denemeprojesi.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() , RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://api.nomics.com/v1/";  // url'nin devamı interface sınıfında
    private var cryptoModels : ArrayList<CryptoModel>? =null
    private var recyclerViewAdapter :  RecyclerViewAdapter ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()
    }


    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<CryptoModel>>{ //asenkronist şekilde veriyi alacak

            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {

                if (response.isSuccessful){
                    response.body()?.let { //response null değilse

                        cryptoModels=ArrayList(it)

                        cryptoModels?.let {

                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter=recyclerViewAdapter

                        }
                        /*
                        for (cryptomodel : CryptoModel in cryptoModels!!){
                            println(cryptomodel.currency)
                            println(cryptomodel.price)
                        }

                         */
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {

        Toast.makeText(this,"Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}