package com.kutluoglu.feature.catalog.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kutluoglu.domain.catalog.GetProductByIdUseCase
import com.kutluoglu.domain.catalog.Product
import com.kutluoglu.domain.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

// UI State for the detail screen
sealed class ProductDetailUiState {
    data object Loading : ProductDetailUiState()
    data class Error(val message: String) : ProductDetailUiState()
    data class Success(val product: Product) : ProductDetailUiState()
}

@KoinViewModel
class ProductDetailViewModel(
    private val getProductById: GetProductByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _uiState.value = ProductDetailUiState.Loading
            when (val result = getProductById(productId)) {
                is Result.Success -> _uiState.value = ProductDetailUiState.Success(result.value)
                is Result.Error -> _uiState.value =
                    ProductDetailUiState.Error(result.throwable.message ?: "Unknown error")
            }
        }
    }
}