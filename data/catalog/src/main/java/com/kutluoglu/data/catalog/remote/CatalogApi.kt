package com.kutluoglu.data.catalog.remote

import com.kutluoglu.data.catalog.dto.ProductDto
import com.kutluoglu.data.catalog.dto.Products
import retrofit2.http.GET

interface CatalogApi {
	@GET("products")
	suspend fun getProducts(): Products
}