package com.simplekjl.theapp.domain.mapper

import com.simplekjl.theapp.data.model.PokemonDetailsRaw
import com.simplekjl.theapp.domain.model.PokemonDetails

//
// Created by  on 5/24/20.
//

class PokemonResponseMapper {
    fun mapRawListToDomain(raw: ArrayList<PokemonDetailsRaw>): List<PokemonDetails> {
        val newList = mutableListOf<PokemonDetails>()
        raw.forEach {
            val item = PokemonDetails(safeStringMapping(it.name), safeStringMapping(it.url))
            newList.add(item)
        }
        return newList
    }

    /**
     * This method can be improved if we use generics, maybe for using integers or other types,
     * even if we use an extension function
     */
    private fun safeStringMapping(value: String): String {
        return if (value.isNullOrBlank()) {
            " " // empty string
        } else {
            value
        }
    }
}