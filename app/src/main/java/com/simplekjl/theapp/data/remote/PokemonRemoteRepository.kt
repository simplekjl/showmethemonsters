package com.simplekjl.theapp.data.remote

import com.simplekjl.theapp.data.model.PokemonResponseRaw
import io.reactivex.Observable

//
// Created by  on 5/24/20.
//

interface PokemonRemoteRepository {
    fun getPokemonListFromNetwork(offset: Int, limit: Int): Observable<PokemonResponseRaw>
}