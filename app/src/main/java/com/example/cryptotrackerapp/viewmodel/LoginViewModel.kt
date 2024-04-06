package com.example.cryptotrackerapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cryptotrackerapp.model.LoginUIState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

data class User(
    val name: String,
    val lastname: String
)
class LoginViewModel: ViewModel() {
    var uiState = mutableStateOf(LoginUIState())

    private lateinit var auth: FirebaseAuth

    fun registerUser(user: User, email: String, password: String) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    saveUser(currentUser?.uid ?: "no-id", user)
                } else {
                    Log.w(
                        "LoginViewModel", 
                        "registerUser:failure",
                        task.exception
                    )
                }
            }
    }

    private fun saveUser(userId: String, user: User) {
        val db = Firebase.firestore
        db.collection("users").document(userId).set(user)
    }
}