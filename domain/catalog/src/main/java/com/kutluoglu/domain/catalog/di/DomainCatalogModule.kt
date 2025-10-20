package com.kutluoglu.domain.catalog.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module


@Module
@ComponentScan("com.kutluoglu.*.catalog.**")
object DomainCatalogModule