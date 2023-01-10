package com.karimali.mazad.ui.viewmodels

import com.examl.androidtesk.data.model.ResponseModel
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
    fun `getAllCategory() return list of Categories`() {
        // Arrange
        val fakeMainRepositoryImp = object : MainRepository {
            override suspend fun fetchAllCategories(): ResponseModel<CategoryModel>? {
                return ResponseModel(
                    data = CategoryModel(),
                    msg = "",
                    code = "0"
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
    }
}