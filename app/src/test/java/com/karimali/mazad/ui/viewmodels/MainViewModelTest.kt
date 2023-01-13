package com.karimali.mazad.ui.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.examl.androidtesk.data.model.ResponseModel
import com.karimali.mazad.domain.models.ResultState
import com.karimali.mazad.domain.models.category.Category
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.repository.MainRepository
import com.karimali.mazad.domain.useCase.CategoryUseCase
import com.karimali.mazad.repository.FakeMainRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

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

    @ExperimentalCoroutinesApi
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
        val expected = ResultState.Success(CategoryModel(
            categories = listOf(Category())
        ))

        assertEquals(expected , result.value)
    }
}

@ExperimentalCoroutinesApi
class MainDispatcherRule(val dispatcher: TestDispatcher = StandardTestDispatcher()): TestWatcher() {

    override fun starting(description: Description?) = Dispatchers.setMain(dispatcher)

    override fun finished(description: Description?) = Dispatchers.resetMain()

}
