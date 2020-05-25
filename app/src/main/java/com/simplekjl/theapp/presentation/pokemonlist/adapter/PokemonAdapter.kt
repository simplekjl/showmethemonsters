package com.simplekjl.theapp.presentation.pokemonlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.theapp.domain.model.PokemonDetails

//
// Created by  on 5/25/20.
//

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    private var pokemonList = mutableListOf<PokemonDetails>()

    fun updatePokemonList(newList: List<PokemonDetails>) {
        pokemonList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(inflater, parent)

    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setItem(pokemonList[position])
    }

}