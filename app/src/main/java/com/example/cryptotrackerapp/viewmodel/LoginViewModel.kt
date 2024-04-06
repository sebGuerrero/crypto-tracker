package com.example.cryptotrackerapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    val TAG = "LoginViewModel"
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
                        TAG,
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

                uiState.value = LoginUIState(name, lastname)

            } else {
                Log.d(TAG, "$source data: null")
            }
        }
    }
}