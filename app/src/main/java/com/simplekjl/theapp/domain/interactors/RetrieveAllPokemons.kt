package com.simplekjl.theapp.domain.interactors

import com.simplekjl.theapp.data.remote.PokemonRemoteRepository
import com.simplekjl.theapp.domain.mapper.PokemonResponseMapper
import com.simplekjl.theapp.domain.model.Pagination
import com.simplekjl.theapp.domain.model.PokemonDetails
import io.reactivex.Observable

/**
 * Interactor that allow us to communicate the data layer with the domain layer
 */
class RetrieveAllPokemons(
    private val repository: PokemonRemoteRepository,
    private val mapper: PokemonResponseMapper
) {
    fun getAllPokemons(pagination: Pagination): Observable<List<PokemonDetails>> =
        repository.getPokemonListFromNetwork(pagination.offset, pagination.limit)
            .map { mapper.mapRawListToDomain(it.results) }
}