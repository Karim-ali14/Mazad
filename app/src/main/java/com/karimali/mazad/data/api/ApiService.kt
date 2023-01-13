package com.karimali.mazad.data.api

import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.common.utils.Constants
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.models.category.SubCategoryModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(Constants.Routs.GET_ALL_CATEGORIES)
    suspend fun fetchAllCategories() :ResponseModel<CategoryModel?>

    @GET(Constants.Routs.GET_ALL_SUB_CATEGORIES)
    suspend fun fetchSubCategoriesByCatId(@Query("cat")categoryId: String) :ResponseModel<ArrayList<SubCategoryModel>>

}