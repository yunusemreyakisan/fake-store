package com.yakisan.fakestore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.yakisan.fakestore.R
import com.yakisan.fakestore.model.Product
import com.yakisan.fakestore.ui.theme.BackgroundRed
import com.yakisan.fakestore.ui.theme.Dark2
import com.yakisan.fakestore.ui.theme.Greyscale100
import com.yakisan.fakestore.ui.theme.Greyscale500
import com.yakisan.fakestore.ui.theme.Red
import com.yakisan.fakestore.ui.theme.TransparentRed
import com.yakisan.fakestore.ui.theme.White
import com.yakisan.fakestore.ui.theme.Yellow
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.getTextTheme
import com.yakisan.fakestore.util.getTheme

@Composable
fun ProductCard(
    product: Product,
    onItemClick: () -> Unit
) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    //Card
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                shape = RoundedCornerShape(15),
                elevation = 2.dp,
            )
            .clickable {
                onItemClick.invoke() //Clickable Product
            },
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Dark2 else getTheme()
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.medium2),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(15))
                    .background(color = White),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    modifier = Modifier.size(MaterialTheme.dimens.large),
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
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = getTextTheme()
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            Text(
                text = product.category.toString().uppercase(),
                style = MaterialTheme.typography.bodySmall,
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
                style = MaterialTheme.typography.titleSmall,
                color = getTextTheme()
            )


        }


    }
}



