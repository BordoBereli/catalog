package com.kutluoglu.feature.catalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kutluoglu.feature.catalog.CatalogListScreen
import com.kutluoglu.feature.catalog.detail.ProductDetailScreen

sealed class Screen(val route: String) {
    data object CatalogList : Screen("catalog_list")
    data object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}

fun NavGraphBuilder.catalogGraph(navController: NavController) {
    composable(Screen.CatalogList.route) {
        CatalogListScreen(onProductClick = { productId ->
            navController.navigate(Screen.ProductDetail.createRoute(productId))
        })
    }
    composable(Screen.ProductDetail.route,
        arguments = listOf(navArgument("productId") { type = NavType.IntType })
    ) { backStackEntry ->
//        val productId = backStackEntry.arguments?.getString("productId")
        ProductDetailScreen(
            onNavigateUp = { navController.navigateUp() }
        )
//        ProductDetailScreen(
//            productId = productId
//            ?: throw IllegalArgumentException("Product ID not provided"),
//            onNavigateBack = { navController.popBackStack() },
//            onNavigateUp = { navController.navigateUp() }
//        )
    }
}