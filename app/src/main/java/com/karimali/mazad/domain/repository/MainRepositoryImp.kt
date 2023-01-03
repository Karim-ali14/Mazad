package com.karimali.mazad.domain.repository

import com.karimali.mazad.data.api.ApiService
import javax.annotation.Nullable
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val service: ApiService): MainRepository {

    override suspend fun fetchAllCategories() = service.fetchAllCategories()

    override suspend fun fetchSubCategoriesByCatId(categoryId: String) = service.fetchSubCategoriesByCatId(categoryId)

}