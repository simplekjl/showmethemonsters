package com.simplekjl.theapp.data.model

//
// Created by  on 5/24/20.
//
/**
 * Pokemon model response
 */
data class PokemonResponseRaw(
    val count: Int,
    val next: String,
    val previous: String,
    val results: ArrayList<PokemonDetailsRaw>
)

/**
 * Pokemon details model
 */
data class PokemonDetailsRaw(val name: String, val url: String)