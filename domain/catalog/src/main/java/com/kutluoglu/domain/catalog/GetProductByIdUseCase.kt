package com.kutluoglu.domain.catalog

import com.kutluoglu.domain.common.Result
import org.koin.core.annotation.Factory

@Factory
class GetProductByIdUseCase(
    private val repository: CatalogRepository
) {
    suspend operator fun invoke(id: Int): Result<Product> = repository.getProductById(id)
}