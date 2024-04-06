package com.example.cryptotrackerapp

import com.example.cryptotrackerapp.data.AuthenticationRepository
import com.example.cryptotrackerapp.model.LoginUIState
import com.example.cryptotrackerapp.model.User
import com.example.cryptotrackerapp.viewmodel.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever

@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTests {

    private lateinit var viewModel: LoginViewModel
    private lateinit var authRepository: AuthenticationRepository

    @Before
    fun setUp() {
        authRepository = Mockito.mock()

        viewModel = LoginViewModel(authRepository)
    }

    @Test
    fun registerUser_success() = runTest {
        // Given
        val user = User("nameTest", "lastNameTest")
        val email = "email@email.com"
        val password = "password"
        val userId = "testUserId"

        whenever(authRepository.registerUser(eq(user), eq(email), eq(password), any()))
            .thenAnswer {
                (it.arguments[3] as (String?) -> Unit).invoke(userId)
            }

        // When
        viewModel.registerUser(user, email, password)

        // Then
        assertEquals(user.name, viewModel.uiState.name)
        assertEquals(user.lastname, viewModel.uiState.lastname)
    }

    @Test
    fun registerUser_failure() {

    }
}