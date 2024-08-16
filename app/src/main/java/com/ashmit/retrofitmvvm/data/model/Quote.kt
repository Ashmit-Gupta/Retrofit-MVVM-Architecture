package com.ashmit.retrofitmvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quote")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val quoteId: Int = 0,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
)