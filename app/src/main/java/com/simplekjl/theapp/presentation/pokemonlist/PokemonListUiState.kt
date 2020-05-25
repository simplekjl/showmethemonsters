package com.simplekjl.theapp.presentation.pokemonlist

import com.simplekjl.theapp.domain.model.PokemonDetails

//
// Created by  on 5/25/20.
//

sealed class PokemonListUiState {
    // states can be more declarative in case we know what data we need to pass back
    object Loading : PokemonListUiState()
    data class Success(val data: List<PokemonDetails>) : PokemonListUiState()
    object Error : PokemonListUiState()
}