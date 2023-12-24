package com.yakisan.fakestore.util


//Filtering general list method
fun <T> filterList(list: List<T>, query: String, getFeatureInList: (T) -> String?): List<T> {
    val lowerCaseQuery = query.lowercase()
    return list.filter {
        getFeatureInList(it)?.lowercase()?.contains(lowerCaseQuery) == true
    }
}
