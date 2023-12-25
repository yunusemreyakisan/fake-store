package com.yakisan.fakestore.model

import com.google.gson.annotations.SerializedName

data class Category(
    var name: String? = null,
)

var categories = listOf<Category>(
    Category("All"),
    Category("Electronics"),
    Category("Jewelery"),
    Category("Men's Clothing"),
    Category("Women's Clothing")
)