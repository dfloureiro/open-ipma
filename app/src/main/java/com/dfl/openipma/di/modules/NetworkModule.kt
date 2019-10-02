package com.dfl.openipma.di.modules

import android.content.Context
import com.dfl.dataipma.IpmaClient
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [ContextModule::class])
object NetworkModule {

    private const val IPMA_URL = "https://api.ipma.pt/"
    private const val cacheSize = (1 * 1024 * 1024).toLong()

    @Singleton
    @Provides
    @JvmStatic
    fun ipmaClient(
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): IpmaClient {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(IPMA_URL)
            .client(okHttpClient)
            .build()
            .create(IpmaClient::class.java)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun moshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun okHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder().cache(cache).build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun cache(@Named("application") context: Context): Cache {
        return Cache(context.cacheDir, cacheSize)
    }
}
