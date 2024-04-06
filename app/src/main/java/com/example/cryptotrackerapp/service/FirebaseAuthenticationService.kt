package com.example.cryptotrackerapp.service

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class FirebaseAuthenticationService: AuthenticationService {

    private val auth: FirebaseAuth = Firebase.auth

    override fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onComplete: (String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful && auth.currentUser != null) {
                    onComplete(auth.currentUser!!.uid)
                } else {
                    onComplete(null)
                }
            }
    }
}