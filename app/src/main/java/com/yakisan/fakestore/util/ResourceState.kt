package com.yakisan.fakestore.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.yakisan.fakestore.R
import com.yakisan.fakestore.components.ProductCard
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.ui.theme.dimens

object ResourceState {
    @Composable
    fun LoadingState() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(MaterialTheme.dimens.logoSize / 2),
                color = getTextTheme()
            )
        }
    }

    @Composable
    fun SuccessState(products: List<Product>) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = MaterialTheme.dimens.medium2)
        ) {
            items(products.size) { index ->
                val product = products[index]
                ProductCard(product = product)
            }
        }
    }

    @Composable
    fun ErrorState(errorMessage: String) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.medium2),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = ""
            )
            Text(text = errorMessage)
        }
    }


    @Composable
    fun NoResultState() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.medium2),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(MaterialTheme.dimens.logoSize),
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))

            Text(text = "Product not found")
        }
    }
}
