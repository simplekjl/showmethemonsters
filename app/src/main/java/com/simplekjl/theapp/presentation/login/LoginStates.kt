package com.simplekjl.theapp.presentation.login

/**
 * Sealed classes that represent the different states in the UI
 */


sealed class LoginUiState {
    // states can be more declarative in case we know what data we need to pass back
    object Loading : LoginUiState()
    object Success : LoginUiState()
    object Error : LoginUiState()
}

sealed class VerificationUIState {
    object ValidCredentials : VerificationUIState()
    data class InValidCredentials(val errors: List<LoginUIElements>) : VerificationUIState()
}

sealed class LoginUIElements {
    object Email : LoginUIElements()
    object Password : LoginUIElements()
    object ReEnterPassword :
        LoginUIElements() // in case more elements are added we can handle with ease
}