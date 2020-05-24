package com.simplekjl.theapp.data.model

//
// Created by  on 5/24/20.
//

/**
 * Pagination object to communicate offset and limit from domain layer
 */
data class Pagination(val offset: Int, val limit: Int)

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