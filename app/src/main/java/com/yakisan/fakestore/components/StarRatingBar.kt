package com.yakisan.fakestore.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yakisan.fakestore.ui.theme.Red
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.getTextTheme

@Composable
fun StarRatingBar(
    rating: Double,
    count: Int,
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Red,
                modifier = Modifier
                    .height(MaterialTheme.dimens.logoSize / 4)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.small1))

            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = getTextTheme()
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.small1))

            Text(
                text = "($count)",
                style = MaterialTheme.typography.bodySmall,
                color = getTextTheme()
            )
        }
    }
}