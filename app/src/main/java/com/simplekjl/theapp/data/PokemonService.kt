package com.simplekjl.theapp.data

import com.simplekjl.theapp.data.model.PokemonResponseRaw
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

//
// Created by  on 5/24/20.
//

interface PokemonService {
    @GET("pokemon/")
    fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<PokemonResponseRaw>
}