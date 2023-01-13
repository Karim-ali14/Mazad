package com.karimali.mazad.repository

import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.models.category.Statistics
import com.karimali.mazad.domain.models.category.SubCategoryModel
import com.karimali.mazad.domain.repository.MainRepository

class FakeMainRepositoryImp :MainRepository{
    override suspend fun fetchAllCategories(): ResponseModel<CategoryModel?> {
        return ResponseModel(
            data = CategoryModel(),
            msg = "",
            code = "0"
        )
    }

    override suspend fun fetchSubCategoriesByCatId(categoryId: String): ResponseModel<ArrayList<SubCategoryModel>> {
        return ResponseModel(
            data = arrayListOf(SubCategoryModel()),
            msg = "",
            code = "0"
        )
    }
}