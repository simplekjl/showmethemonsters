package com.simplekjl.theapp.presentation.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.simplekjl.theapp.R
import com.simplekjl.theapp.presentation.pokemonlist.PokemonListFragment
import com.simplekjl.theapp.presentation.preferences.SharePreferencesHelper
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() =
            LoginFragment()
    }

    private val mPref: SharePreferencesHelper by inject()
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // subscribe to view model live datas
        subscribeLiveData()
        // attempt login with the keyboard
        setupUiItems()

    }

    private fun setupUiItems() {
        etPassword.setOnEditorActionListener { textView, id, keyEvent ->
            if (id == R.id.etPassword || id == EditorInfo.IME_NULL) {
                validateCredentials()
                true
            }
            false
        }
        loginBtn.setOnClickListener { validateCredentials() }
        etPassword.doAfterTextChanged {
            if (!TextUtils.isEmpty(etUsername.text) && it.toString().isNotEmpty()) {
                loginBtn.isEnabled = true
            }
        }
        etUsername.doAfterTextChanged {
            if (!TextUtils.isEmpty(etPassword.text) && it.toString().isNotEmpty()) {
                loginBtn.isEnabled = true
            }
        }
        loginBtn.isEnabled = false
    }

    private fun subscribeLiveData() {
        viewModel.credentialsLiveData.observe(viewLifecycleOwner, Observer { renderErrors(it) })
        viewModel.loginUiState.observe(viewLifecycleOwner, Observer { renderUiState(it) })
    }

    private fun renderErrors(state: VerificationUIState) {
        when (state) {
            VerificationUIState.ValidCredentials -> {
                removeErrors()
                doLogin()
            }
            is VerificationUIState.InValidCredentials -> {
                showErrors(state.errors)
            }

        }
    }

    private fun doLogin() {
        hideKeyboardFrom(context, view)
        val email: String = etUsername.text.toString()
        val password: String = etPassword.text.toString()
        viewModel.login(email, password)
    }

    private fun removeErrors() {
        username_text_input_layout.error = null
        password_text_input_layout.error = null
    }

    private fun showErrors(errorList: List<LoginUIElements>) {
        removeErrors()
        if (errorList.isNotEmpty()) {
            hideOverLay()
        }
        errorList.forEach { element ->
            when (element) {
                LoginUIElements.Email -> {
                    username_text_input_layout.error = getString(R.string.email_error)
                    if (errorList.last() == element) etUsername.requestFocus()
                }
                LoginUIElements.Password -> {
                    password_text_input_layout.error = getString(R.string.password_error)
                    if (errorList.last() == element) etPassword.requestFocus()
                }
                LoginUIElements.ReEnterPassword -> {
                    // leave blank just as example of scalability
                }
            }
        }
    }

    private fun renderUiState(state: LoginUiState) {
        when (state) {
            LoginUiState.Loading -> {
                // show loading spinner
                showLoading()
            }
            LoginUiState.Error -> {
                showError()
            }
            LoginUiState.Success -> {
                saveFakeToken()
                navigateToHome()
            }

        }
    }

    private fun saveFakeToken(){
        mPref.setAccessToken("token")
        mPref.setUserLoginStatus(true)
    }

    private fun navigateToHome() {
        // navigate to the next screen we can improve this having activity on Result or jetpack components
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, PokemonListFragment.newInstance())
            ?.commitNow()
    }

    private fun showLoading() {
        // disabling login button
        progress_overlay.isVisible = true
    }

    private fun hideOverLay() {
        progress_overlay.isVisible = false
    }

    private fun showError() {
        hideOverLay()
        Toast.makeText(context, getString(R.string.general_error_msg), Toast.LENGTH_SHORT)
            .show()
    }

    private fun validateCredentials() {
        val email: String = etUsername.text.toString()
        val password: String = etPassword.text.toString()
        viewModel.validateCredentials(email, password)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearSubscriptions()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.clearSubscriptions()
    }

    private fun hideKeyboardFrom(context: Context?, view: View?) {
        val imm: InputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
