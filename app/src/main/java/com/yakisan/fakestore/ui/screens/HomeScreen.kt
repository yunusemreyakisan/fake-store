package com.yakisan.fakestore.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yakisan.fakestore.R
import com.yakisan.fakestore.components.BaseTextField
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.Resource
import com.yakisan.fakestore.util.ResourceState.ErrorState
import com.yakisan.fakestore.util.ResourceState.LoadingState
import com.yakisan.fakestore.util.ResourceState.NoResultState
import com.yakisan.fakestore.util.ResourceState.SuccessState
import com.yakisan.fakestore.util.filterList
import com.yakisan.fakestore.util.getFocusManager
import com.yakisan.fakestore.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val products by productViewModel.products.observeAsState() //All products
    var filteredProducts by remember { mutableStateOf<List<Product>>(emptyList()) } //Filtered Products
    var searchCountry by remember { mutableStateOf("") } //Product Query

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.dimens.medium2,
                end = MaterialTheme.dimens.medium2,
                top = MaterialTheme.dimens.medium2
            ),
    ) {

        //Searching Products
        BaseTextField(
            placeholder = "Search Product",
            value = searchCountry,
            onValueChange = { query ->
                searchCountry = query
                // Update product list
                filteredProducts = filterList(products?.data!!, searchCountry) { it.title }
            },
            focusManager = getFocusManager(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon"
                )
            },
            keyBoardOnClick = {
                Log.e("Düzenlendi", "Liste yeniden düzenlendi.")
            }
        )

        //Products
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (val resource = products) {
                is Resource.Loading -> LoadingState()
                is Resource.Success -> {
                    // Product all & filtered product
                    if (searchCountry.isEmpty()) SuccessState(resource.data!!)
                    else if(searchCountry.isNotEmpty() && filteredProducts.isNotEmpty()) SuccessState(filteredProducts)
                    else if(filteredProducts.isEmpty()) NoResultState()
                }
                is Resource.Error -> ErrorState(resource.message ?: "An error occurred")
                else -> {}
            }
        }
    } //eof base column
}


