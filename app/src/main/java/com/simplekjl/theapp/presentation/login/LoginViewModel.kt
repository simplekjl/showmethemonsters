package com.simplekjl.theapp.presentation.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplekjl.theapp.domain.PokemonDomainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LoginViewModel(private val repository: PokemonDomainRepository) : ViewModel() {

    private var _credentialsLiveData: MutableLiveData<VerificationUIState> = MutableLiveData()
    val credentialsLiveData: LiveData<VerificationUIState>
        get() = _credentialsLiveData
    private var _loginUiState: MutableLiveData<LoginUiState> = MutableLiveData()
    val loginUiState: LiveData<LoginUiState>
        get() = _loginUiState

    private val compositeDisposable = CompositeDisposable()

    fun validateCredentials(email: String, password: String) {
        // notify the UI we are processing data
        val errorList = mutableListOf<LoginUIElements>()
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            errorList.add(LoginUIElements.Password)
        }
        if (!TextUtils.isEmpty(email) && !isEmailValid(email)) {
            errorList.add(LoginUIElements.Email)
        }
        if (errorList.isEmpty()) {
            // we perform the login action
            _credentialsLiveData.value = VerificationUIState.ValidCredentials
        } else {
            _credentialsLiveData.value = VerificationUIState.InValidCredentials(errorList)
        }
    }

    fun login(email: String, password: String) {
        compositeDisposable.add(
            repository
                .login()
                .delay(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation()).subscribe(
                    { _loginUiState.value = LoginUiState.Success },
                    { _loginUiState.value = LoginUiState.Error },
                    { /**Nothing to do here for now **/ },
                    { _loginUiState.value = LoginUiState.Loading }
                )
        )
    }

    // method can use the Android extensions for validation of email but I would just do a show case
    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    // method can be improved with a REGEX but in this case I just made a showcase of the validation
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    fun clearSubscriptions() {
        compositeDisposable.clear()
    }
}
