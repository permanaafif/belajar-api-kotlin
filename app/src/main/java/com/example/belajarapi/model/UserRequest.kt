package com.example.belajarapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserRequest {

    @SerializedName("name")
    @Expose
    var name : String? =null

    @SerializedName("job")
    @Expose
    var job : String? =null
}