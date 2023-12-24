package com.yakisan.fakestore.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.yakisan.fakestore.R
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.ui.theme.Dark2
import com.yakisan.fakestore.ui.theme.Greyscale500
import com.yakisan.fakestore.ui.theme.dimens

@Composable
fun ProductCard(
    product: Product
) {
    //Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = MaterialTheme.dimens.small2)
            .clip(shape = RoundedCornerShape(15)),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Dark2 else Greyscale500
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.medium2),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(MaterialTheme.dimens.large / 2),
                model = product.image,
                placeholder = painterResource(id = R.drawable.logo),
                error = painterResource(id = R.drawable.logo),
                contentDescription = "Flags",
                contentScale = ContentScale.Fit,
            )

            Text(text = product.title.toString())
        }


    }
}