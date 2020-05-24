package com.simplekjl.theapp.data.remote

import com.simplekjl.theapp.data.PokemonService
import com.simplekjl.theapp.data.model.PokemonResponseRaw
import io.reactivex.Observable

//
// Created by  on 5/24/20.
//

class PokemonRemoteRepositoryImpl(private val service: PokemonService) : PokemonRemoteRepository {
    override fun getPokemonListFromNetwork(
        offset: Int,
        limit: Int
    ): Observable<PokemonResponseRaw> {
        return service.getAllPokemon(offset, limit)
    }

}