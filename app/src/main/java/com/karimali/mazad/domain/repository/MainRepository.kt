package com.karimali.mazad.domain.repository

import com.examl.androidtesk.data.model.ResponseModel
import retrofit2.http.Query

interface MainRepository {

    suspend fun fetchAllCategories() :ResponseModel<ArrayList<PlayerModel>>

    suspend fun fetchSubCategoriesByCatId(categoryId: String) :ResponseModel<ArrayList<PlayerModel>>

}