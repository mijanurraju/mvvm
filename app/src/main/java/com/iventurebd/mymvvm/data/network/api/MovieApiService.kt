package com.iventurebd.mymvvm.data.network.api

import com.iventurebd.mymvvm.data.network.model.MovieApiResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular?")
    fun fetchMoviesByType(): Observable<MovieApiResponse>
}
