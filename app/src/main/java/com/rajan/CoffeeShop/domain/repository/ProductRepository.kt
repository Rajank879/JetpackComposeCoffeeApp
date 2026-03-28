package com.rajan.CoffeeShop.domain.repository

import androidx.paging.PagingSource
import com.rajan.CoffeeShop.domain.model.Products.ProductResponse
import com.rajan.CoffeeShop.domain.model.Products.Products

interface ProductRepository {
    suspend fun getCategories(): List<String>

    suspend fun getProducts(category: String, limit: Int, skip: Int): ProductResponse

    fun getProductsPagingSource(category: String, onTotalCount: (Int?) -> Unit): PagingSource<Int, Products>
}