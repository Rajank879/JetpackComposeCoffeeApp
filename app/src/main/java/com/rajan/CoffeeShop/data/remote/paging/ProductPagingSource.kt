package com.rajan.CoffeeShop.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rajan.CoffeeShop.data.remote.api.ProductApi
import com.rajan.CoffeeShop.domain.model.Products.Products

class ProductPagingSource(
    private val productApi: ProductApi,
    private val category: String,
    private val onTotalCount: (Int?) -> Unit
): PagingSource<Int, Products> (){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Products> {
        return try {
            val page = params.key ?: 0
            val limit = params.loadSize
            val skip = page * limit

            val response = if (category.equals("all", ignoreCase = true)) {
                productApi.getAllProducts(limit, skip)
            } else productApi.getProductByCategory(category.replace(" ","-"), limit, skip)
            onTotalCount(response.total) // update total count
            LoadResult.Page(
                data = response.products,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.products.isEmpty()) null else page + 1
            )

        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Products>): Int? {
        return state.anchorPosition?.let { position->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: state.closestPageToPosition(position)?.nextKey?.minus(1)

        }
    }

}