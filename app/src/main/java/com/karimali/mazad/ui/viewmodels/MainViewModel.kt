package com.karimali.mazad.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimali.mazad.domain.models.ResultState
import com.karimali.mazad.domain.models.category.CategoryModel
import com.karimali.mazad.domain.models.category.SubCategoryModel
import com.karimali.mazad.domain.useCase.SetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setCategoryUseCase: SetCategoryUseCase
):ViewModel() {

    private val _category: MutableStateFlow<ResultState<CategoryModel>> = MutableStateFlow(ResultState.Loading)

    private val _subCategory:MutableStateFlow<ResultState<ArrayList<SubCategoryModel>>> = MutableStateFlow(ResultState.Loading)

    //    val category = _category
    fun getAllCategory(): MutableStateFlow<ResultState<CategoryModel>>{
        viewModelScope.launch {
            _category.emit(ResultState.Loading)
            setCategoryUseCase.fetchAllCategories().collect {
                Log.i("DataFlow", "Result -- > $it")
                _category.emit(it)
            }
        }
        return _category
    }

    fun getSubCategory(catId:String): MutableStateFlow<ResultState<ArrayList<SubCategoryModel>>>{
        viewModelScope.launch {
            _subCategory.emit(ResultState.Loading)
            setCategoryUseCase.fetchAllSubCategories(catId).collect {
                Log.i("DataFlow", "Result -- > $it")
                _subCategory.emit(it)
            }
        }
        return _subCategory
    }

}