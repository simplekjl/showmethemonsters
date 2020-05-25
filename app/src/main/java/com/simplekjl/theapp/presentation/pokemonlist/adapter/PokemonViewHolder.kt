package com.simplekjl.theapp.presentation.pokemonlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.theapp.R
import com.simplekjl.theapp.domain.model.PokemonDetails

//
// Created by  on 5/25/20.
//

class PokemonViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.pokemon_item_layout, parent, false)) {
    // variables inside the item layout


    var pokemonName: TextView = itemView.findViewById(R.id.pokemonName)

    fun setItem(pokemon: PokemonDetails) {
        pokemonName.text = pokemon.name
    }
}