package com.kutluoglu.catalog

import android.app.Application
import com.kutluoglu.data.catalog.di.CatalogDataModule
import com.kutluoglu.domain.catalog.di.DomainCatalogModule
import com.kutluoglu.feature.catalog.di.CatalogFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class MyApp : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@MyApp)
			modules(
				DomainCatalogModule.module,
				CatalogDataModule.module,
				CatalogFeatureModule.module
			)
		}
	}
}