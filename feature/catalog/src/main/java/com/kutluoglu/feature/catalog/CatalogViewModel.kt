package com.kutluoglu.feature.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kutluoglu.domain.catalog.GetProductsUseCase
import com.kutluoglu.domain.catalog.Product
import com.kutluoglu.domain.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

sealed class CatalogUiState {
	data object Loading : CatalogUiState()
	data class Error(val message: String) : CatalogUiState()
	data class Success(val products: List<Product>) : CatalogUiState()
}

@KoinViewModel
class CatalogViewModel(
	private val getProducts: GetProductsUseCase
) : ViewModel() {
	private val _uiState = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
	val uiState: StateFlow<CatalogUiState> = _uiState

	init {
		load()
	}

	fun retry() = load()

	private fun load() {
		_uiState.value = CatalogUiState.Loading
		viewModelScope.launch {
			when (val res = getProducts()) {
				is Result.Success -> _uiState.value = CatalogUiState.Success(res.value)
				is Result.Error -> _uiState.value = CatalogUiState.Error(res.throwable.message ?: "Unknown error")
			}
		}
	}

	fun onProductClick(product: Product) {
		// Handle product click event
		// Handle navigation, e.g., navController.navigate("product/${product.id}")
		println("${product.title} clicked!")
	}

}