package com.rajan.CoffeeShop.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rajan.CoffeeShop.domain.model.Products.Products
import com.rajan.CoffeeShop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        category: String,
        onTotalCount: (Int?) -> Unit
    ): Flow<PagingData<Products>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { repository.getProductsPagingSource(category, onTotalCount) }
        ).flow
    }
}