package com.kutluoglu.domain.catalog

import com.kutluoglu.domain.common.Result
import org.koin.core.annotation.Factory

@Factory
class GetProductsUseCase(
	private val repository: CatalogRepository
) {
	suspend operator fun invoke(): Result<List<Product>> = repository.getProducts()
}