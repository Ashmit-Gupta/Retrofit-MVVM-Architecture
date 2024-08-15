package com.ashmit.retrofitmvvm.data.model

import androidx.room.Entity

//@Entity()
data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)