package com.lorenzofonseca.data

import com.lorenzofonseca.data.model.DiscoverResponse
import com.lorenzofonseca.data.model.MovieDetailResponse
import com.lorenzofonseca.data.model.MovieResultResponse
import com.lorenzofonseca.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    suspend fun getDiscover(
        @Query("sort_by") sortBy: String
    ): Response<DiscoverResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Response<MovieDetailResponse>

    @GET("search/movie")
    suspend fun getSearchResults(@Query("query") query: String): Response<SearchResponse>

}