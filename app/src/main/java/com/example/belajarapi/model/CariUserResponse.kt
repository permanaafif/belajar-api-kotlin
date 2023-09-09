package com.example.belajarapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CariUserResponse {

    @SerializedName("data")
    @Expose
    lateinit var data : Data

    class Data{
        @SerializedName("id")
        @Expose
        var id : Int? = null

        @SerializedName("email")
        @Expose
        var email : String? = null

        @SerializedName("first_name")
        @Expose
        var first_name : String? = null

        @SerializedName("last_name")
        @Expose
        var last_name : String? = null
    }

}