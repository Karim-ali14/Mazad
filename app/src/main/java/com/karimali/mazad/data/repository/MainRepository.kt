package com.examl.androidtesk.data.repository

import com.examl.androidtesk.data.model.ResponseModel

interface MainRepository {

    suspend fun fetchTennisPlayers() : ResponseModel<ArrayList<PlayerModel>>

}