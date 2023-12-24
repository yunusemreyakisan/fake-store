package com.yakisan.fakestore.service

import com.yakisan.fakestore.model.Product
import retrofit2.http.GET

interface ProductService {

    //Getting all products
    @GET("products")
    suspend fun getProducts(): List<Product>
}
