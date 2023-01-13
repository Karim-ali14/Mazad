package com.karimali.mazad.domain.useCase

import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.domain.models.ResultState
import com.karimali.mazad.domain.models.category.SubCategories
import com.karimali.mazad.domain.models.category.SubCategoryModel
import com.karimali.mazad.domain.repository.MainRepository
import com.karimali.mazad.rules.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SubCategoryUseCaseTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `fetchAllSubCategoryByCatId() with exist category id then return list of subCats`() = runTest{
        // Arrange
        val categoryId = "1"
        val fakeMainRepository = object : MainRepository {
            override suspend fun fetchSubCategoriesByCatId(categoryId: String): ResponseModel<SubCategories>? {
                return ResponseModel<SubCategories>(
                    data = arrayListOf(
                        SubCategoryModel(
                            id = 1
                        ),SubCategoryModel(
                            id = 2
                        ),
                    ),
                    code = "200",
                    msg = ""
                )
            }
        }
        val subCategoryUseCase = SubCategoryUseCase(
            mainRepository = fakeMainRepository,
            dispatcherOn = Dispatchers.Unconfined,
            dispatcherSwitcher = Dispatchers.Unconfined,
        )

        // Act
        val result = subCategoryUseCase.fetchAllSubCategoryByCatId(categoryId)

        // Expected
        val expected = ResultState.Success (
            data = arrayListOf<SubCategoryModel>(
                SubCategoryModel(id = 1),
                SubCategoryModel(id = 2),
            )
        )

       assertEquals(expected, result.value)
    }

}

