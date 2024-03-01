package com.example.testapp.authentication

import com.example.testapp.model.AuthStateDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthState {


    private var _authStateDetails: AuthStateDetails? = null
    @Singleton
    @Provides
    fun login(authStateDetails: AuthStateDetails) : AuthStateDetails{
        _authStateDetails = authStateDetails
        return  _authStateDetails!!
    }

    @Singleton
    @Provides
    fun getAuthDetails() : AuthStateDetails{
        return _authStateDetails!!
    }

}