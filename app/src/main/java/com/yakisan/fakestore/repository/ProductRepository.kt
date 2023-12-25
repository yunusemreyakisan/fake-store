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
            emptyList()
        }
    }

    //Getting specific products
    suspend fun getProductByCategory(category: String): List<Product> {
        return try {
            productService.getProductsByCategory(category)
        } catch (e: Exception) {
            emptyList()
        }
    }

    //Getting specific product by id
    suspend fun getProductById(productId: Int): Product {
        return try {
            productService.getProductById(productId)
        } catch (e: Exception) {
            Log.e("ProductID", "Not Found this product")
            return Product()
        }
    }

}
