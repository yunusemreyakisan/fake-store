package com.yakisan.fakestore.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.yakisan.fakestore.ui.theme.Dark2
import com.yakisan.fakestore.ui.theme.Greyscale100
import com.yakisan.fakestore.ui.theme.Greyscale600
import com.yakisan.fakestore.ui.theme.Greyscale900
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.getTextTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTextField(
    enabled: Boolean = true,
    placeholder: String,
    value: String,
    focusManager: FocusManager,
    onValueChange: (String) -> Unit,
    keyBoardOnClick: () -> Unit,
    leadingIcon: @Composable () -> Unit?
) {
    val interactionSource = remember { MutableInteractionSource() }
    //TextField
    OutlinedTextField(
        enabled = enabled,
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.medium2),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = if (isSystemInDarkTheme()) Dark2 else Greyscale100,
            disabledTextColor = if (isSystemInDarkTheme()) Dark2 else Greyscale100,
            focusedIndicatorColor = if (isSystemInDarkTheme()) Dark2 else Greyscale600,
            unfocusedIndicatorColor = if (isSystemInDarkTheme()) Dark2 else Greyscale100,
            disabledIndicatorColor = if (isSystemInDarkTheme()) Dark2 else Greyscale100,
            cursorColor = Greyscale900
        ),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password).copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyBoardOnClick()
                focusManager.clearFocus() // IMEAction.Search gerçekleştiğinde focusu temizle
            }
        ),
        shape = RoundedCornerShape(25),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = getTextTheme()),
        placeholder = {
            Text(placeholder)
        },
        leadingIcon = {
            leadingIcon()
        }
    )
}