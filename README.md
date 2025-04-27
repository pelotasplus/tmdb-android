# Simple TBDM client app

Libraries used:
 * Dagger Hilt for DI
 * Compose UI for UI
 * Flow for ractive programming
 * Simple ad-hoc MVI-alike framework for Google ViewModels
 * Compose Navigation for ... navigation
 * Retrofit and OkHttp for API access
 * KotlinX.serialization for JSON parsing

To build:
 * Open project in Android Studio
 * Add API access key to pl.pelotasplus.tmdb.data.di.DataModule#provideAuthorizationToken
 * And just run it

App architecture:
 * Following clean architecture to some extent.
 * Three main packages -- data, domain, and features (for the presentation layer)
 * No interactors/use cases and just Repositories directly used in ViewModels
 * Separat models for data and domain layers
