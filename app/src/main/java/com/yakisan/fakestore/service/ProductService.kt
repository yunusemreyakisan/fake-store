package com.yakisan.fakestore.service

import com.yakisan.fakestore.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    //Getting all products
    @GET("products")
    suspend fun getProducts(): List<Product>

    //Getting products specific category
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<Product>

    //Getting product by id
    @GET("products/{productId}")
    suspend fun getProductById(@Path("productId") productId: Int): Product
}
