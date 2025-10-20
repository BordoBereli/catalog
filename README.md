# Catalog App

Catalog is a sample Android application built to demonstrate a modern, clean, and scalable architecture using the latest Android development tools. The app fetches a list of products from a dummy API and displays them in a simple list.

This project serves as a practical guide for implementing key concepts such as Clean Architecture, a multi-module setup, dependency injection with Koin, and a fully declarative UI with Jetpack Compose.

## ✨ Screenshots

*(You can add screenshots of your app here)*

| Loading State | Success State | Error State |
|:---:|:---:|:---:|
| <img src="art/loading_screen.png" width="250"/> | <img src="art/success_screen.png" width="250"/> | <img src="art/error_screen.png" width="250"/> |

## 🛠 Tech Stack & Architecture

This project follows the principles of **Clean Architecture** with a multi-module approach to separate concerns and ensure a scalable and maintainable codebase.

### Architecture

The data flow is unidirectional, following the MVVM pattern in the presentation layer and abstracting business logic into use cases.

**[ UI (Compose) ] -> [ ViewModel ] -> [ UseCase ] -> [ Repository ] -> [ DataSource (API/DB) ]**

### Key Libraries & Technologies

-   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for building a declarative UI.
    -   **Material 3:** For modern Material Design components.
    -   **Compose Navigation:** For navigating between screens.
-   **Dependency Injection:** [Koin](https://insert-koin.io/) with KSP annotations for managing dependencies.
-   **Asynchronous Programming:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html) for managing background tasks and reactive data streams.
-   **Networking:**
    -   [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for Android and Java.
    -   [OkHttp](https://square.github.io/okhttp/): The underlying HTTP client for interceptors and logging.
    -   [Moshi](https://github.com/square/moshi): A modern JSON library for Android, with KSP-based code generation for high performance.
-   **Architecture Components:**
    -   **ViewModel:** To store and manage UI-related data in a lifecycle-conscious way.
-   **Build System:**
    -   [Gradle with Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html).
    -   [Gradle Version Catalogs](https://docs.gradle.org/current/userguide/platforms.html) (`libs.versions.toml`) for centralized dependency management.

### Module Structure

The project is divided into several modules, each with a distinct responsibility:

-   **:app**
    -   The main application module that integrates all other modules.
    -   Handles application-level setup, such as Koin initialization and navigation graph setup.
-   **:core**
    -   Contains shared code and utilities used across multiple modules.
    -   `:core:ui`: Shared Composables, themes, colors, etc.
    -   `:core:common`: Common utility classes, constants, or extensions.
-   **:feature:catalog**
    -   A self-contained feature module.
    -   Contains the UI (Composables), ViewModel, and feature-specific Koin module.
-   **:domain**
    -   The core business logic layer. It is a pure Kotlin module with no Android framework dependencies.
    -   `:domain:catalog`: Defines UseCases (e.g., `GetProductsUseCase`) and Repository interfaces.
    -   `:domain:common`: Defines core domain models used across features.
-   **:data**
    -   The data layer, responsible for providing data to the domain layer.
    -   `:data:catalog`: Implements the repository interface, manages remote (API) and local (database) data sources. Contains Retrofit service definitions and DTOs (Data Transfer Objects).

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

*   **Android Studio:** Make sure you have the latest stable version of [Android Studio](https://developer.android.com/studio) installed.
*   **JDK 17 or higher:** Android Studio Iguana and higher come with a bundled JDK, which is sufficient.

### Installation & Setup

1.  **Clone the repository:**
2.  **Build & Run the project.**
    
    