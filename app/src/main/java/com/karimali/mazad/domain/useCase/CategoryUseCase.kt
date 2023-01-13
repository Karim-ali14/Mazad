package com.karimali.mazad.domain.useCase

import com.karimali.mazad.common.utils.Constants.RequestCodes.SUCCESS
import com.karimali.mazad.di.IODispatcher
import com.karimali.mazad.di.MainDispatcher
import com.karimali.mazad.domain.models.ResultState
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.models.category.SubCategoryModel
import com.karimali.mazad.domain.repository.MainRepository
import com.karimali.mazad.domain.helpers.NetworkCallHandler
import com.karimali.mazad.domain.models.category.SubCategories
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    @IODispatcher private val dispatcherOn: CoroutineDispatcher,
    @MainDispatcher private val dispatcherSwitcher: CoroutineDispatcher,
): NetworkCallHandler(){

    private val category:MutableStateFlow<ResultState<CategoryModel?>> = MutableStateFlow(ResultState.Loading)

    private val subCategory:MutableStateFlow<ResultState<ArrayList<SubCategoryModel>>> = MutableStateFlow(ResultState.Loading)

    suspend fun fetchAllCategories() : MutableStateFlow<ResultState<CategoryModel?>>{
        performNetworkOp(
            dispatcherOn = dispatcherOn,
            dispatcherSwitcher = dispatcherSwitcher,
            networkCall = { mainRepository.fetchAllCategories() } ,
            onData = {
                category.emit(
                    when (it?.code) {
                        SUCCESS -> {
                            if (it.data == null)
                                ResultState.EmptyData("")
                            else
                                ResultState.Success(it.data)
                        }
                        else -> {
                            ResultState.Error("Something wrong")
                        }
                    }
                )
            },
            onError = {

            }
        )
        return category
    }

}