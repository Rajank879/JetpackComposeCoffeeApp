package com.rajan.CoffeeShop.data.repository

import androidx.paging.PagingSource
import com.rajan.CoffeeShop.data.remote.api.ProductApi
import com.rajan.CoffeeShop.data.remote.paging.ProductPagingSource
import com.rajan.CoffeeShop.domain.model.Products.ProductResponse
import com.rajan.CoffeeShop.domain.model.Products.Products
import com.rajan.CoffeeShop.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository{

    override suspend fun getCategories(): List<String> {
        val categories= productApi.getCategories().map { it.name }
        return listOf("All") + categories
    }

    override suspend fun getProducts(category: String, limit: Int, skip: Int): ProductResponse {
        return if (category.equals("all", ignoreCase = true)) {
            productApi.getAllProducts(limit, skip)
        } else productApi.getProductByCategory(category.replace(" ","-"), limit, skip)
    }

    override fun getProductsPagingSource(category: String, onTotalCount: (Int?) -> Unit): PagingSource<Int, Products> {
        return ProductPagingSource(productApi, category, onTotalCount)
    }
}