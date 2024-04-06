package com.example.cryptotrackerapp

import com.example.cryptotrackerapp.data.AuthenticationRepository
import com.example.cryptotrackerapp.model.LoginUIState
import com.example.cryptotrackerapp.model.User
import com.example.cryptotrackerapp.viewmodel.LoginViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class LoginViewModelTests {

    private lateinit var viewModel: LoginViewModel

    @Mock
    private val mockRepository: AuthenticationRepository = Mockito.mock(AuthenticationRepository::class.java)

    @Before
    fun setUp() {
        viewModel = LoginViewModel(mockRepository)
    }

    @Test
    fun registerUser_success() {
        // Given
        val user = User("nameTest", "lastNameTest")
        val email = "email@email.com"
        val password = "password"

//        Mockito.doAnswer { invocation ->
//            val callback: (String?) -> Unit = invocation.getArgument(3)
//            callback("testId")
//        }
//            .`when`(mockRepository)
//            .registerUser(user, email, password, onComplete = { "testId" } )

        Mockito.`when`(
            mockRepository.registerUser(user, email, password, { })
        ).thenAnswer { invocation ->
            val callback: (String?) -> Unit = invocation.getArgument(3)
            callback("testId")
        }

        // When
        viewModel.registerUser(user,  email, password)

        // Then
        assertEquals(
            LoginUIState(user.name, user.lastname),
            viewModel.uiState
        )
    }

    @Test
    fun registerUser_failure() {

    }
}