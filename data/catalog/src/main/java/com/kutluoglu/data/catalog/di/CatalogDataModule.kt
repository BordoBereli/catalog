package com.kutluoglu.data.catalog.di

import android.content.Context
import androidx.room.Room
import com.kutluoglu.data.catalog.local.CatalogDatabase
import com.kutluoglu.data.catalog.local.ProductDao
import com.kutluoglu.data.catalog.remote.CatalogApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@ComponentScan("com.kutluoglu.*.**")
object CatalogDataModule {
	@Single
	fun okHttp(): OkHttpClient =
		OkHttpClient.Builder()
			.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
			.build()

	@Single // Provide a Moshi instance
	fun moshi(): Moshi =
		Moshi.Builder()
			// Add the Kotlin adapter factory for reflection-based parsing as a fallback.
			// Even with codegen, this is good practice for types you might not control.
			.add(KotlinJsonAdapterFactory())
			.build()

	@Single
	fun retrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = // Inject Moshi here
		Retrofit.Builder()
			.baseUrl("https://dummyjson.com/")
			.client(okHttpClient)
			// Use the Moshi instance you just created
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.build()
	/*@Single
	fun retrofit(okHttpClient: OkHttpClient): Retrofit =
		Retrofit.Builder()
			.baseUrl("https://dummyjson.com/") // replace with real base URL
			.client(okHttpClient)
			.addConverterFactory(MoshiConverterFactory.create())
			.build()*/

	@Single
	fun catalogApi(retrofit: Retrofit): CatalogApi =
		retrofit.create(CatalogApi::class.java)

	@Single
	fun db(appContext: Context): CatalogDatabase =
		Room
			.databaseBuilder(appContext, CatalogDatabase::class.java, "catalog.db")
			.fallbackToDestructiveMigration(false)
			.build()

	@Single
	fun productDao(db: CatalogDatabase): ProductDao = db.productDao()
}