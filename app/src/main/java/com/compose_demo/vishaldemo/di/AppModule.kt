package com.compose_demo.vishaldemo.di

import android.content.Context
import com.compose_demo.vishaldemo.App
import com.compose_demo.vishaldemo.data.local.LocalDatabase
import com.compose_demo.vishaldemo.data.local.dao.DataDao
import com.compose_demo.vishaldemo.data.local.dao.DatabaseDao
import com.compose_demo.vishaldemo.data.network.api.Upsert
import com.compose_demo.vishaldemo.ui.navigation.Navigator
import com.compose_demo.vishaldemo.utils.Shareprefs
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import staff.management.system.repository.NetworkRepository
import staff.management.system.repository.NetworkRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApp(): App {
        return App()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()
    }


    @Singleton
    @Provides
    @Named("Backend")
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo.com")
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideDeleteApi(@Named("Backend") retrofit: Retrofit): Upsert {
        return retrofit.create(Upsert::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(networkRepository: NetworkRepositoryImpl): NetworkRepository {
        return networkRepository
    }

    @Singleton
    @Provides
    fun provideSharePref(@ApplicationContext context: Context): Shareprefs {
        return Shareprefs(context)
    }

    @Singleton
    @Provides
    fun provideNavigator(): Navigator {
        return Navigator()
    }

    @Singleton
    @Provides
    fun provideDatabaseDao(@ApplicationContext context: Context): DatabaseDao {
        return LocalDatabase.getInstance(context).databaseDao()
    }

    @Singleton
    @Provides
    fun provideDataDao(@ApplicationContext context: Context): DataDao {
        return LocalDatabase.getInstance(context).dataDao()
    }
}