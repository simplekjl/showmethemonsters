package com.simplekjl.theapp.domain.interactors

import com.simplekjl.theapp.data.remote.PokemonRemoteRepository
import io.reactivex.Observable

//
// Created by  on 5/25/20.
//

class LogoutCompletable(private val repository: PokemonRemoteRepository) {
    fun logout(): Observable<Any> {
        return Observable.just(Unit)
    }
}