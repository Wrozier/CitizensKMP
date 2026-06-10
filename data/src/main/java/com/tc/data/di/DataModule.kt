package com.tc.data.di

import com.tc.data.repository.AccountRepositoryImpl
import com.tc.data.repository.AuthRepositoryImpl
import com.tc.domain.repository.AccountRepository
import com.tc.domain.repository.AuthRepository
import org.koin.dsl.module

val dataModule = module {
    single<AccountRepository> { AccountRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl() }
}
