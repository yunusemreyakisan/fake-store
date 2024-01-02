package com.yakisan.fakestore.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yakisan.fakestore.R
import com.yakisan.fakestore.components.BaseTextField
import com.yakisan.fakestore.components.CategoryButton
import com.yakisan.fakestore.core.Extension.isInternetAvailable
import com.yakisan.fakestore.core.Extension.noRippleClickable
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.model.categories
import com.yakisan.fakestore.ui.theme.FakeStoreTheme
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.Resource
import com.yakisan.fakestore.util.ResourceState.ErrorState
import com.yakisan.fakestore.util.ResourceState.LoadingState
import com.yakisan.fakestore.util.ResourceState.SuccessState
import com.yakisan.fakestore.util.filterList
import com.yakisan.fakestore.util.getFocusManager
import com.yakisan.fakestore.util.getTextTheme
import com.yakisan.fakestore.util.getTheme
import com.yakisan.fakestore.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val products by productViewModel.products.observeAsState() //All products
    var filteredProducts by remember { mutableStateOf<List<Product>>(emptyList()) } //Filtered Products
    var searchProduct by remember { mutableStateOf("") } //Product Query
    var selectedCategory by remember { mutableStateOf<Int?>(0) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        //Profile
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.medium2),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small2)
        ) {

            Icon(
                modifier = Modifier.size(MaterialTheme.dimens.logoSize / 2),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                tint = getTextTheme()
            )

            Text(
                text = "Special Products",
                style = MaterialTheme.typography.titleLarge,
                color = getTextTheme()
            )

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Icon(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.logoSize / 2)
                        .noRippleClickable {
                            //navController.navigate(Screen.ProductDetailScreen.route)
                        },
                    painter = painterResource(id = R.drawable.ic_shopping_bag),
                    contentDescription = "",
                    tint = getTextTheme()
                )
            }
        }

        //Searching Products
        BaseTextField(
            placeholder = "Search Product",
            value = searchProduct,
            onValueChange = { query ->
                searchProduct = query
                // Update product list
                filteredProducts = filterList(products?.data!!, searchProduct) { it.title }
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

        //Categories
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = MaterialTheme.dimens.medium2)
        ) {
            items(categories.size) { index ->
                val category = categories[index]
                val isSelected = index == selectedCategory
                CategoryButton(
                    category = category,
                    isSelected = isSelected,
                    onCategorySelected = {
                        selectedCategory = if (isSelected) null else index
                        productViewModel.fetchProductsByCategory(category.name ?: "")
                    }
                )
            }
        }

        //Products
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (val resource = products) {
                is Resource.Loading -> LoadingState()
                is Resource.Success -> {
                    // Product all & filtered product
                    if (searchProduct.isEmpty())
                        SuccessState(
                            resource.data!!,
                            LocalContext.current.isInternetAvailable(),
                            navController
                        )
                    else if (searchProduct.isNotEmpty() && filteredProducts.isNotEmpty())
                        SuccessState(
                            filteredProducts,
                            LocalContext.current.isInternetAvailable(),
                            navController
                        )
                    else if (filteredProducts.isEmpty()) ErrorState(errorMessage = "Product not found")
                }

                is Resource.Error -> ErrorState(resource.message ?: "An error occurred")
                else -> {}
            }
        }
    } //eof base column
}



