package com.simplekjl.theapp.domain.interactors

import com.simplekjl.theapp.data.remote.PokemonRemoteRepository
import io.reactivex.Observable

//
// Created by  on 5/25/20.
//

class LoginCompletable(private val repository: PokemonRemoteRepository) {
    fun login(): Observable<Any> {
        return Observable.just(Unit)
    }
}