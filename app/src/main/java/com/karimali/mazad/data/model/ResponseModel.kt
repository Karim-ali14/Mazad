package com.examl.androidtesk.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel<T> (
    var code : String,
    var msg : String,
    var data : T,
)

