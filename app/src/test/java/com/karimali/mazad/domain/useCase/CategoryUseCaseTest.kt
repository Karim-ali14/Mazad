package com.karimali.mazad.domain.useCase

import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.domain.models.ResultState
import com.karimali.mazad.domain.models.category.Category
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.repository.MainRepository
import com.karimali.mazad.rules.MainDispatcherRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class CategoryUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testDispatcher = MainDispatcherRule()


    @Test
    fun `getAllCategory() return Categories`() {
        // Arrange
        val fakeMainRepositoryImp = object : MainRepository {
            override suspend fun fetchAllCategories(): ResponseModel<CategoryModel?> {
                return ResponseModel(
                    data = CategoryModel(
                        categories = listOf(Category())
                    ),
                    msg = "",
                    code = "200"
                )
            }
        }

        val categoryUseCase = CategoryUseCase(
            mainRepository = fakeMainRepositoryImp,
            dispatcherOn = Dispatchers.Unconfined,
            dispatcherSwitcher = Dispatchers.Unconfined
        )

        // Act

        runBlocking {
            val result = categoryUseCase.fetchAllCategories()

            // expected
            val expected = ResultState.Success(
                data = CategoryModel(
                    categories = listOf(Category())
                ),
            )

            assertEquals(expected , result.value)
        }

    }

    @Test
    fun `getAllCategory() with return null`() = runTest {
        // Arrange
        val fakeMainRepositoryImp = object : MainRepository {
            override suspend fun fetchAllCategories(): ResponseModel<CategoryModel?> {
                return ResponseModel(
                    data = null,
                    code = "200",
                    msg = ""
                )
            }
        }
        val categoryUseCase = CategoryUseCase(
            mainRepository = fakeMainRepositoryImp,
            dispatcherOn = Dispatchers.Unconfined,
            dispatcherSwitcher = Dispatchers.Unconfined,
        )

        // Act
        val result = categoryUseCase.fetchAllCategories()

        // expected
        val expected = ResultState.EmptyData("")

        assertEquals(expected,result.value)
    }

    @Test
    fun `getAllCategory() with return Error`() = runTest {
        // Arrange
        val fakeMainRepositoryImp = object : MainRepository {
            override suspend fun fetchAllCategories(): ResponseModel<CategoryModel?> {
                return ResponseModel(
                    data = null,
                    code = "404",
                    msg = ""
                )
            }
        }
        val categoryUseCase = CategoryUseCase(
            mainRepository = fakeMainRepositoryImp,
            dispatcherOn = Dispatchers.Unconfined,
            dispatcherSwitcher = Dispatchers.Unconfined,
        )

        // Act
        val result = categoryUseCase.fetchAllCategories()

        // expected
        val expected = ResultState.Error("Something wrong")

        assertEquals(expected,result.value)
    }
}