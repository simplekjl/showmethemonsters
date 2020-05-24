package com.simplekjl.theapp.di

import android.app.Application
import com.simplekjl.theapp.BuildConfig
import com.simplekjl.theapp.data.PokemonService
import com.simplekjl.theapp.data.remote.PokemonRemoteRepository
import com.simplekjl.theapp.data.remote.PokemonRemoteRepositoryImpl
import com.simplekjl.theapp.domain.interactors.LoginCompletable
import com.simplekjl.theapp.domain.interactors.LogoutCompletable
import com.simplekjl.theapp.domain.interactors.RetrieveAllPokemons
import com.simplekjl.theapp.domain.mapper.PokemonResponseMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//
// Created by simplekjl on 5/24/20.
//

class PokemonApp : Application() {
    val appModule = module {
        single<Retrofit> {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(0, TimeUnit.SECONDS)
            builder.connectTimeout(5, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                builder.addInterceptor(interceptor)
            }

            val client = builder.build()

            Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
        }
        single<PokemonService> { get<Retrofit>().create(PokemonService::class.java) }
        factory<PokemonRemoteRepository> { PokemonRemoteRepositoryImpl(get()) }
        factory { PokemonResponseMapper() }
        // interactors
        factory { RetrieveAllPokemons(get(), get()) }
        factory { LogoutCompletable(get()) }
        factory { LoginCompletable(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@PokemonApp)
            modules(appModule)
        }
    }

}