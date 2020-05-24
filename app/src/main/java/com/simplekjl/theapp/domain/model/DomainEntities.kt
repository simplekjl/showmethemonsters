package com.simplekjl.theapp.domain.model

//
// Created by  on 5/24/20.
//
/**
 * Pokemon model domain response
 */
data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: ArrayList<PokemonDetails>
)

data class PokemonDetails(val name: String, val url: String)