package com.examl.androidtesk.data.repository

import com.karimali.mazad.data.api.ApiService
import com.examl.androidtesk.data.model.ResponseModel
import javax.annotation.Nullable
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(@Nullable val service: ApiService):MainRepository {
    override suspend  fun fetchTennisPlayers(): ResponseModel<ArrayList<PlayerModel>> {
        return service.fetchTennisPlayers()
    }
}