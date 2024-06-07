package com.dicoding.tenmoviesgermany

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val year: Int,
    val description: String,
    val photo: Int
) : Parcelable