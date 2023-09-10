package edu.uchicago.gerber.androidretro.presentation.screens

data class Paginate(
    val query: String = "",
    val maxResult: Int = 10,
    val startIndex: Int = 1,
)
