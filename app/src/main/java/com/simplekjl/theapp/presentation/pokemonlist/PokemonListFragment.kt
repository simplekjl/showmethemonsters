package com.simplekjl.theapp.presentation.pokemonlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simplekjl.theapp.R
import com.simplekjl.theapp.presentation.login.LoginFragment
import com.simplekjl.theapp.presentation.login.LoginUiState
import com.simplekjl.theapp.presentation.preferences.SharePreferencesHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonListFragment : Fragment() {

    private var _pokemonListUiState: MutableLiveData<PokemonListStates> = MutableLiveData()
    val pokemonListUiState: LiveData<PokemonListStates>
        get() = _pokemonListUiState


    companion object {
        fun newInstance() =
            PokemonListFragment()
    }

    private val mPref: SharePreferencesHelper by inject()
    private val viewModel: PokemonListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pokemon_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeToLiveData()
    }

    private fun setupRecyclerView(){

    }

    private fun subscribeToLiveData(){
        viewModel.
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_logout -> {
            mPref.setAccessToken(null)
            mPref.setUserLoginStatus(false)
            navigateToLogin()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToLogin() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, LoginFragment.newInstance())
            ?.commitNow()
    }
}
