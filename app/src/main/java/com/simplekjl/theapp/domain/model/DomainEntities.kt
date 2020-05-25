package com.simplekjl.theapp.domain.model

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