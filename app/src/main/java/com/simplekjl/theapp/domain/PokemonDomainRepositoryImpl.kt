package com.simplekjl.theapp.domain

import com.simplekjl.theapp.domain.interactors.LoginCompletable
import com.simplekjl.theapp.domain.interactors.LogoutCompletable
import com.simplekjl.theapp.domain.interactors.RetrieveAllPokemons
import com.simplekjl.theapp.domain.model.Pagination
import com.simplekjl.theapp.domain.model.PokemonDetails
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Class that allows the communication between domain and data layer using interactors given
 * the specific case
 */
class PokemonDomainRepositoryImpl(
    private val retrieveAllPokemons: RetrieveAllPokemons,
    private val loginCompletable: LoginCompletable,
    private val logoutCompletable: LogoutCompletable
) :
    PokemonDomainRepository {
    override fun login(): Completable = loginCompletable.login()

    override fun logout(): Completable = logoutCompletable.logout()

    override fun getAllPokemon(pagination: Pagination): Observable<List<PokemonDetails>> =
        retrieveAllPokemons.getAllPokemons(pagination)
}