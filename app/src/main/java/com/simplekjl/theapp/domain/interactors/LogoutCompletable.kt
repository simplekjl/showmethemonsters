package com.simplekjl.theapp.domain.interactors

import com.simplekjl.theapp.domain.PokemonDomainRepository
import io.reactivex.Completable

//
// Created by  on 5/25/20.
//

class LogoutCompletable(private val repository: PokemonDomainRepository) {
    fun logout(): Completable {
        return Completable.complete()
    }
}