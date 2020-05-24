package com.simplekjl.theapp.domain

import com.simplekjl.theapp.domain.model.Pagination
import com.simplekjl.theapp.domain.model.PokemonDetails
import io.reactivex.Completable
import io.reactivex.Observable

//
// Created by  on 5/24/20.
//

interface PokemonDomainRepository {
    fun login(): Completable // fake login
    fun logout(): Completable // delete fake data
    fun getAllPokemon(pagination: Pagination): Observable<List<PokemonDetails>>
}