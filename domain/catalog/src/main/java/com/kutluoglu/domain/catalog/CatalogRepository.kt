package com.kutluoglu.domain.catalog

import com.kutluoglu.domain.common.Result
interface CatalogRepository {
	suspend fun getProducts(): Result<List<Product>>
}