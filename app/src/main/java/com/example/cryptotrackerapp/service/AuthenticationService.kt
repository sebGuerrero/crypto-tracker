package com.example.cryptotrackerapp.service

interface AuthenticationService {
    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onComplete: (String?) -> Unit
    )
}