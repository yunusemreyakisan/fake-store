package com.yakisan.fakestore.components

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yakisan.fakestore.core.Extension.noRippleClickable
import com.yakisan.fakestore.model.Category
import com.yakisan.fakestore.ui.theme.Dark2
import com.yakisan.fakestore.ui.theme.Greyscale100
import com.yakisan.fakestore.ui.theme.Primary900
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.getTextTheme

@Composable
fun CategoryButton(
    category: Category,
    isSelected: Boolean,
    onCategorySelected: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .noRippleClickable(onClick = onCategorySelected)
            .padding(bottom = MaterialTheme.dimens.small2)
            .border(
                width = 1.dp,
                color = if (isSelected) Primary900 else Color.Transparent,
                shape = RoundedCornerShape(15)
            )
            .clip(shape = RoundedCornerShape(15)),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Dark2 else Greyscale100
        )

    ) {
        category.name?.let { name ->
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.small2),
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSelected) Color.Red else getTextTheme(),
                textAlign = TextAlign.Center,
            )
        }
    }
}
