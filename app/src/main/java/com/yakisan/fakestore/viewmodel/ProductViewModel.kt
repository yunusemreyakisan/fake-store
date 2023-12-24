package com.yakisan.fakestore.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.repository.ProductRepository
import com.yakisan.fakestore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {
    private val _productsLiveData = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>> get() = _productsLiveData

    init {
        fetchProducts()
    }

    //Fetching Product Data
    private fun fetchProducts() {
        _productsLiveData.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val products = productRepository.getProducts()
                _productsLiveData.value = Resource.Success(products)
            } catch (e: Exception) {
                _productsLiveData.value = Resource.Error("An error occurred: ${e.message}")
            }
        }
    }
}
