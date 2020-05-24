package com.simplekjl.theapp.data

import com.simplekjl.theapp.data.model.PokemonResponseRaw
import io.reactivex.Observable

//
// Created by  on 5/24/20.
//

interface PokemonService {
    fun getAllPokemon(offset: Int, limit: Int): Observable<PokemonResponseRaw>
}