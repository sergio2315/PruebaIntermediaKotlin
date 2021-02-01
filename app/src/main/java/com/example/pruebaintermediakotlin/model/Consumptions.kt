package com.example.pruebaintermediakotlin.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Consumptions_table")
data class Consumptions(@PrimaryKey(autoGenerate = true)
                        @NonNull
                        var id: Int = 0,
                        var item: String,
                        var price: Int=0,
                        var quantity: Int=0,
                        var total: Int)