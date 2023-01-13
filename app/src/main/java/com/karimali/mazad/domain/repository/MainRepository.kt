package com.karimali.mazad.domain.repository

import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.models.category.SubCategoryModel
import retrofit2.http.Query

interface MainRepository {

    suspend fun fetchAllCategories() :ResponseModel<CategoryModel?>? = null

    suspend fun fetchSubCategoriesByCatId(categoryId: String) :ResponseModel<ArrayList<SubCategoryModel>>? = null

}