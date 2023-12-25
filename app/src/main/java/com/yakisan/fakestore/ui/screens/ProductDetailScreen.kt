package com.yakisan.fakestore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.yakisan.fakestore.R
import com.yakisan.fakestore.components.StarRatingBar
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.navigation.Screen
import com.yakisan.fakestore.ui.theme.Dark2
import com.yakisan.fakestore.ui.theme.FakeStoreTheme
import com.yakisan.fakestore.ui.theme.White
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.Resource
import com.yakisan.fakestore.util.ResourceState
import com.yakisan.fakestore.util.ResourceState.LoadingState
import com.yakisan.fakestore.util.getTextTheme
import com.yakisan.fakestore.util.getTheme
import com.yakisan.fakestore.viewmodel.ProductViewModel

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: Int,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val productById by productViewModel.product.observeAsState() //Product by id

    LaunchedEffect(productId) {
        productViewModel.fetchProductById(productId)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
    ) {


        when (val resource = productById) {
            is Resource.Loading -> {
                LoadingState()
            }

            is Resource.Success -> {
                val product = resource.data!!
                ProductDetailCard(product = product, navController)

            }

            is Resource.Error -> {
                ResourceState.ErrorState(errorMessage = "Ürün çekilemedi")
            }

            else -> {}
        }

    }

}


@Composable
fun ProductDetailCard(product: Product, navController: NavController) {
    //Card
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.small2)
            .wrapContentHeight()
            .shadow(
                shape = RoundedCornerShape(10),
                elevation = 2.dp,
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Dark2 else getTheme()
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.medium2),
        ) {
            Icon(
                modifier = Modifier
                    .size(MaterialTheme.dimens.logoSize / 2)
                    .clickable {
                        navController.navigate(Screen.HomeScreen.route)
                    },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back icon",
                tint = getTextTheme()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(5))
                    .background(color = White),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.large * 4)
                        .padding(MaterialTheme.dimens.small2),
                    model = product.image,
                    placeholder = painterResource(id = R.drawable.logo),
                    error = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Flags",
                    contentScale = ContentScale.Fit,
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            Text(
                text = product.title.toString(),
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                color = getTextTheme()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            Text(
                text = product.category.toString().uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = getTextTheme()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            Text(
                text = product.description.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = getTextTheme()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            product.rating.rate?.let { rate ->
                product.rating.count?.let { count ->
                    StarRatingBar(rating = rate, count = count)
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            Text(
                text = "$ ${product.price.toString()}",
                style = MaterialTheme.typography.headlineSmall,
                color = getTextTheme()
            )


        }


    }
}

