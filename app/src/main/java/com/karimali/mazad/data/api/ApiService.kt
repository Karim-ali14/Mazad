package com.karimali.mazad.data.api

import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.common.utils.Constants
import retrofit2.http.GET


interface ApiService {

    @GET(Constants.Routs.GET_TENNIS_PLAYER)
    suspend fun fetchTennisPlayers() :ResponseModel<ArrayList<PlayerModel>>

}