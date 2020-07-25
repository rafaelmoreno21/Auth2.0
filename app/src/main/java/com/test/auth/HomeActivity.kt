package com.test.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Gravity.apply
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val retrofit= Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api= retrofit.create(ApiService::class.java)

        api.fetchAllUsers().enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                showData(response.body()!!)
               // d("rafael","onResponse: ${response.body()!![0].phone}")
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                d("rafael","onFailure")
            }



        })



    }

    private fun showData(users:List<User>) {

        recyclerView.apply {
            layoutManager=LinearLayoutManager(this@HomeActivity)
            adapter=UsersAdapter(users)
        }
    }

}
