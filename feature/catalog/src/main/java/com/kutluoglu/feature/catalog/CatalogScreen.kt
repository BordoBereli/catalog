package com.kutluoglu.feature.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kutluoglu.domain.catalog.Product
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("DefaultLocale")
@Composable
fun CatalogScreen(
	viewModel: CatalogViewModel = koinViewModel()
) {
	val state by viewModel.uiState.collectAsState()
	when (state) {
		is CatalogUiState.Loading -> {
			Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = {
				Text("Loading...")
				CircularProgressIndicator()
			})
		}
		is CatalogUiState.Error -> {
			Box(Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center, content = {
				Text((state as CatalogUiState.Error).message)
			})
		}
		is CatalogUiState.Success -> {
			val products = (state as CatalogUiState.Success).products
			LazyColumn(Modifier.fillMaxSize()) {
				items(products.size) { index ->
					val product = products[index]
					ProductItem(product = product, onProductClick = {
						viewModel.onProductClick(product)
					})
				}
			}
		}
	}
}