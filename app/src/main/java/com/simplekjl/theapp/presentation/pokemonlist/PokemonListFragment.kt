package com.simplekjl.theapp.presentation.pokemonlist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.theapp.R
import com.simplekjl.theapp.domain.model.PokemonDetails
import com.simplekjl.theapp.presentation.login.LoginFragment
import com.simplekjl.theapp.presentation.pokemonlist.adapter.PokemonAdapter
import com.simplekjl.theapp.presentation.preferences.SharePreferencesHelper
import kotlinx.android.synthetic.main.pokemon_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonListFragment : Fragment() {


    companion object {
        fun newInstance() =
            PokemonListFragment()
    }

    private val pokemonAdapter = PokemonAdapter()
    private val mPref: SharePreferencesHelper by inject()
    private val viewModel: PokemonListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setHasOptionsMenu(true)
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
        homeToolbar.setOnMenuItemClickListener { onOptionsItemSelected(it) }
        viewModel.getAllPokemons(pokemonAdapter.itemCount)
    }

    private fun setupRecyclerView() {
        val llm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        pokemonRv.layoutManager = llm
        pokemonRv.adapter = pokemonAdapter
        pokemonRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = llm.itemCount
                if (totalItemCount == llm.findLastVisibleItemPosition() + 1) {
                    viewModel.getAllPokemons(pokemonAdapter.itemCount)
                }
            }
        })
    }

    private fun subscribeToLiveData() {
        viewModel.pokemonListUiState.observe(viewLifecycleOwner, Observer {
            renderUiState(it)
        })
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
        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, LoginFragment.newInstance())
            ?.commitNow()
    }

    private fun renderUiState(state: PokemonListUiState) {
        // variables to avoid repeated state when loading more data
        val shouldShowToast = pokemonAdapter.itemCount != 0
        val shouldShowLoading = pokemonAdapter.itemCount != 0
        when (state) {
            is PokemonListUiState.Loading -> {
                showLoading(shouldShowLoading)
            }
            is PokemonListUiState.Success -> {
                showPokemons(state.data)
            }
            is PokemonListUiState.Error -> {
                showError(shouldShowToast)
            }
        }
    }


    private fun showLoading(shouldShowLoading: Boolean) {
        if (!shouldShowLoading) {
            progressBar.isVisible = true
            errorMessage.isVisible = false
            pokemonRv.isVisible = false
        }
    }

    private fun showPokemons(list: List<PokemonDetails>) {
        // hiding views
        progressBar.isVisible = false
        errorMessage.isVisible = false
        pokemonRv.isVisible = true
        pokemonAdapter.updatePokemonList(list)
    }

    private fun showError(shouldShowToast: Boolean) {
        if (shouldShowToast) {
            Toast.makeText(context, R.string.sorry_error, Toast.LENGTH_SHORT).show()
        } else {
            progressBar.isVisible = false
            errorMessage.isVisible = true
            pokemonRv.isVisible = false
            retryButton.setOnClickListener {
                viewModel.getAllPokemons(pokemonAdapter.itemCount)
            }
        }
    }


    override fun onDetach() {
        super.onDetach()
        viewModel.clearSubscriptions()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearSubscriptions()
    }
}
