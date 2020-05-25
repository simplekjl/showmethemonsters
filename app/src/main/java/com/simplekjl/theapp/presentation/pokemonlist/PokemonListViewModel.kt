package com.simplekjl.theapp.presentation.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplekjl.theapp.domain.PokemonDomainRepository
import com.simplekjl.theapp.domain.model.Pagination
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonListViewModel(
    private val repository: PokemonDomainRepository
) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
    private var _pokemonListUiState: MutableLiveData<PokemonListUiState> = MutableLiveData()
    val pokemonListUiState: LiveData<PokemonListUiState>
        get() = _pokemonListUiState

    private var offset = 0
    private var limit = 20
    private var totalResults = 1


    fun getAllPokemons(itemCount: Int) {
        if (itemCount < totalResults) {
            val pagination = Pagination(offset, limit)
            compositeDisposable.add(
                repository.getAllPokemon(pagination).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())
                    .subscribe(
                        {
                            totalResults = it.first // total of pokemons
                            _pokemonListUiState.value =
                                PokemonListUiState.Success(it.second) // list
                        },
                        { _pokemonListUiState.value = PokemonListUiState.Error },
                        {
                            offset += 20 // just incrementing the offset
                        },
                        { _pokemonListUiState.value = PokemonListUiState.Loading })
            )
        }
    }

    fun clearSubscriptions() {
        compositeDisposable.clear()
    }
}
