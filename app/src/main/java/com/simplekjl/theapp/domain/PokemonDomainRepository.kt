package com.simplekjl.theapp.domain

import com.simplekjl.theapp.domain.model.Pagination
import com.simplekjl.theapp.domain.model.PokemonDetails
import io.reactivex.Observable

//
// Created by  on 5/24/20.
//

interface PokemonDomainRepository {
    fun login(): Observable<Any> // fake login
    fun logout(): Observable<Any> // delete fake data
    fun getAllPokemon(pagination: Pagination): Observable<Pair<Int,List<PokemonDetails>>>
}