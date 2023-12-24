package com.yakisan.fakestore.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yakisan.fakestore.R
import com.yakisan.fakestore.components.BaseTextField
import com.yakisan.fakestore.components.WelcomeCard
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.getFocusManager
import com.yakisan.fakestore.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val products = productViewModel.products.value //All products
    val searchCountry = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.dimens.medium2,
                end = MaterialTheme.dimens.medium2,
                top = MaterialTheme.dimens.medium2
            ),
    ) {

        //Search Product
        BaseTextField(
            placeholder = "Search Product",
            value = searchCountry,
            focusManager = getFocusManager(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon"
                )
            },
            keyBoardOnClick = {
                Log.e("Düzenlendi", "Yeniden liste düzenlendi.")
            })


        LazyColumn(
            contentPadding = PaddingValues(vertical = MaterialTheme.dimens.medium2)
        ) {
            items(products.size) { index ->
                val product = products[index]
                Log.e("Products" , products[index].toString())
                WelcomeCard(product = product)
            }
        }


    }


}