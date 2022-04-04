package me.aravi.notes.utils

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}