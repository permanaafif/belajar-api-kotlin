package com.example.belajarapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.belajarapi.R.id.ed_name
import com.example.belajarapi.model.CariUserResponse
import com.example.belajarapi.model.Comment
import com.example.belajarapi.model.UserRequest
import com.example.belajarapi.model.UserResponse
import com.example.belajarapi.service.CommentAPI
import com.example.belajarapi.service.UserAPI
import com.example.belajarapi.util.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var ed_name : EditText
    private lateinit var ed_job : EditText
    private lateinit var ed_id : EditText
    private lateinit var btn_create : Button
    private lateinit var btn_cari : Button
    private lateinit var tv_name : TextView
    private lateinit var tv_job : TextView
    private lateinit var tv_id : TextView
    private lateinit var tv_create_at : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed_name = findViewById(R.id.ed_name)
        ed_job = findViewById(R.id.ed_job)
        ed_id = findViewById(R.id.ed_id)
        btn_create = findViewById(R.id.btn_create)
        btn_cari = findViewById(R.id.btn_cari)
        tv_name = findViewById(R.id.tv_name)
        tv_job = findViewById(R.id.tv_job)
        tv_id = findViewById(R.id.tv_id)
        tv_create_at = findViewById(R.id.tv_create_at)

//        getCommentAPI()
        buatUser()
        cariID()
        findById()
    }

    private fun findById() {
        TODO("Not yet implemented")
    }

    private fun cariID() {
        btn_cari.setOnClickListener {
            if(!ed_id.text.isNullOrEmpty()){
                searchID()
                ed_id.text.clear()
            }
        }
    }

    private fun searchID() {
        val retro = Retro().getRetroClientInstance("https://reqres.in/").create(UserAPI::class.java)
        retro.cari((ed_id.text.toString()).toInt()).enqueue(object : Callback<CariUserResponse>{
            override fun onResponse(
                call: Call<CariUserResponse>,
                response: Response<CariUserResponse>
            ) {
                val res = response.body()
                tv_name.text = res!!.data.first_name
                tv_job.text = res!!.data.last_name

            }

            override fun onFailure(call: Call<CariUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun buatUser() {
        btn_create.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val req = UserRequest()
        req.name = ed_name.text.toString()
        req.job = ed_job.text.toString()
        val retro = Retro().getRetroClientInstance("https://reqres.in/").create(UserAPI::class.java)
        retro.createUser(req).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                tv_name.text = user!!.name
                tv_job.text = user!!.job
                tv_id.text = user!!.id
                tv_create_at.text = user!!.createAt
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Failed",t.message.toString())
            }

        })
    }

    fun getCommentAPI(){
        val retro = Retro().getRetroClientInstance("https://reqres.in/").create(CommentAPI::class.java)
        retro.getComments().enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val comment = response.body()
                for (c in comment!!){
                    Log.e("Hasil ", c.email!!)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e("Failde", t.message.toString())
            }

        })
    }
}