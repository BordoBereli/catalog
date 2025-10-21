package com.kutluoglu.feature.catalog.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.kutluoglu.*.catalog", "com.kutluoglu.*.catalog.*")
object CatalogFeatureModule