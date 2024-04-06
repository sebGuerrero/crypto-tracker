package com.example.cryptotrackerapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cryptotrackerapp.model.LoginUIState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import androidx.compose.runtime.getValue
import com.example.cryptotrackerapp.data.AuthenticationRepository
import com.example.cryptotrackerapp.model.User

class LoginViewModel(private val authRepository: AuthenticationRepository): ViewModel() {
    var uiState by mutableStateOf(LoginUIState())
    var errorMessage by mutableStateOf(String())

    val TAG = "LoginViewModel"
    private lateinit var auth: FirebaseAuth

    fun registerUser(user: User, email: String, password: String) {
        authRepository.registerUser(user, email, password) { userId ->
            val id = userId?.let { notNilId ->
//                saveUser(notNilId, user)
                uiState = LoginUIState(user.name, user.lastname)
            } ?: run {
                errorMessage = "Error registering the User"
            }
        }
    }

    private fun saveUser(userId: String, user: User) {
        uiState = LoginUIState(user.name, user.lastname)
//        val db = Firebase.firestore
//        db.collection("users").document(userId).set(user).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                uiState = LoginUIState(user.name, user.lastname)
//            } else {
//                errorMessage = "Error almacenando data del usuario"
//            }
//        }
    }

    fun readUser() {
        val db = Firebase.firestore
        val docRef = db.collection("users").document("QLSAUTPPDvcU5gNuJ6Z2JAW9C1Y2")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites()) {
                "Local"
            } else {
                "Server"
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "$source data: ${snapshot.data}")

                val name = snapshot.get("name").toString()
                val lastname = snapshot.get("lastname").toString()

                uiState = LoginUIState(name, lastname)

            } else {
                Log.d(TAG, "$source data: null")
            }
        }
    }
}