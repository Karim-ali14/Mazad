package com.karimali.mazad.domain.useCase

import com.karimali.mazad.common.utils.Constants.RequestCodes.SUCCESS
import com.karimali.mazad.domain.helpers.NetworkCallHandler
import com.karimali.mazad.domain.models.ResultState
import com.karimali.mazad.domain.models.category.SubCategories
import com.karimali.mazad.domain.repository.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SubCategoryUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatcherOn:CoroutineDispatcher,
    private val dispatcherSwitcher : CoroutineDispatcher
): NetworkCallHandler() {

    private val subCategoriesState:MutableStateFlow<ResultState<SubCategories>> = MutableStateFlow(ResultState.Loading)

    suspend fun fetchAllSubCategoryByCatId(catId:String):MutableStateFlow<ResultState<SubCategories>>{
        performNetworkOp(
            dispatcherOn = dispatcherOn,
            dispatcherSwitcher = dispatcherSwitcher,
            networkCall = {
                mainRepository.fetchSubCategoriesByCatId(catId)
            }, onData = {
                subCategoriesState.emit(
                    when {
                        it?.code == SUCCESS && it.data.isNotEmpty() ->
                            ResultState.Success(it.data,null)

                        it?.code == SUCCESS && it.data.isEmpty() ->
                            ResultState.EmptyData("")

                        else ->
                            ResultState.Error("Something wrong")
                    }
                )
            }, onError = {
                ResultState.Error("Something wrong")
            }
        )
        return subCategoriesState
    }
}