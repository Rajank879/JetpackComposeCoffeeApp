package com.rajan.CoffeeShop.domain.usecase

import com.rajan.CoffeeShop.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<String> {
        return repository.getCategories()
    }
}