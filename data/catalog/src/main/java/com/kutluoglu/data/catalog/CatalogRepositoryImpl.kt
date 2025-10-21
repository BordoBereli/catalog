package com.kutluoglu.data.catalog

import com.kutluoglu.data.catalog.dto.toProductEntity
import com.kutluoglu.data.catalog.local.ProductDao
import com.kutluoglu.data.catalog.local.toProduct
import com.kutluoglu.data.catalog.remote.CatalogApi
import com.kutluoglu.domain.catalog.CatalogRepository
import com.kutluoglu.domain.catalog.Product
import com.kutluoglu.domain.common.Result
import org.koin.core.annotation.Single

@Single
class CatalogRepositoryImpl(
	private val api: CatalogApi,
	private val productDao: ProductDao
) : CatalogRepository {
	override suspend fun getProducts(): Result<List<Product>> {
		return try {
			val dtos = api.getProducts()
			val entities = dtos.products.map { it.toProductEntity() }
			productDao.upsertAll(entities)
			Result.Success(entities.map { it.toProduct() })
		} catch (t: Throwable) {
			val cached = productDao.getAll()
			if (cached.isNotEmpty()) {
				Result.Success(cached.map { it.toProduct() })
			} else {
				Result.Error(t)
			}
		}
	}

	override suspend fun getProductById(id: Int): Result<Product> {
		return try {
			val productEntity = api.getProductById(id).toProductEntity()
			Result.Success(productEntity.toProduct())
		} catch (e: Exception) {
			Result.Error(e)
		}
	}
}