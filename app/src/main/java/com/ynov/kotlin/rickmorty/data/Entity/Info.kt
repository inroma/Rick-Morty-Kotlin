package com.ynov.kotlin.rickmorty.data.Entity

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)