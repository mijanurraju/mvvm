package com.iventurebd.mymvvm.data.network.model

import com.iventurebd.mymvvm.data.database.movie.MovieEntity


data class MovieApiResponse(
    val page: Long,
    val results: List<MovieEntity>,
    val total_results: Long,
    val total_pages: Long
)
