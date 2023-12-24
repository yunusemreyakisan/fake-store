package com.yakisan.fakestore.repository

import android.util.Log
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.service.ProductService
import com.yakisan.fakestore.util.Resource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProductRepository @Inject constructor(
    private val productService: ProductService
) {
    // Getting Products
    suspend fun getProducts(): List<Product>{
        return try {
            productService.getProducts()
        } catch (e: Exception) {
            // Hata yönetimini burada gerçekleştirebilirsiniz
            emptyList()
        }
    }
}
