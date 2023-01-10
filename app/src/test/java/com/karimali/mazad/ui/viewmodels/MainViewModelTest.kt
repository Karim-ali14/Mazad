package com.karimali.mazad.ui.viewmodels

import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.domain.models.category.Category
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.repository.MainRepository
import com.karimali.mazad.domain.useCase.CategoryUseCase
import com.karimali.mazad.repository.FakeMainRepositoryImp
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(
            categoryUseCase = CategoryUseCase(
                mainRepository = FakeMainRepositoryImp(),
                dispatcherOn = Dispatchers.Unconfined,
                dispatcherSwitcher = Dispatchers.Unconfined
            )
        )
    }


    @Test
    fun `getAllCategory() return Categories`() {
        // Arrange
        val fakeMainRepositoryImp = object : MainRepository {
            override suspend fun fetchAllCategories(): ResponseModel<CategoryModel>? {
                return ResponseModel(
                    data = CategoryModel(
                        categories = listOf(Category())
                    ),
                    msg = "",
                    code = "200"
                )
            }
        }
        mainViewModel = MainViewModel(
            categoryUseCase = CategoryUseCase(
                mainRepository = fakeMainRepositoryImp,
                dispatcherOn = Dispatchers.Unconfined,
                dispatcherSwitcher = Dispatchers.Unconfined
            )
        )

        // Act
        val result = mainViewModel.getAllCategory()

        // expected
        val expected = CategoryModel(
            categories = listOf(Category())
        )

        assertEquals(expected , result)
    }
}