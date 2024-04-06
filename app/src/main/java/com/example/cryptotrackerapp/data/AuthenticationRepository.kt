package com.example.cryptotrackerapp.data

import com.example.cryptotrackerapp.model.User
import com.example.cryptotrackerapp.service.AuthenticationService

class AuthenticationRepository(private val authService: AuthenticationService) {
    fun registerUser(user: User, email: String, password: String, onComplete: (String?) -> Unit) {
        authService.createUserWithEmailAndPassword(email, password) { userId ->
            onComplete(userId)
        }
    }
}