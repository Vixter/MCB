# MCB — Marvel Characters & Movie Browser

> Historical Android learning project from 2015–2016. This repository is no longer maintained and does not represent my current engineering practices.

MCB is an experimental Android content browser that combines Marvel character data with movie data from [The Movie Database (TMDB)](https://www.themoviedb.org/). It was built to explore multiple REST APIs, authenticated requests, list-based Android interfaces, pagination, image loading, and local persistence.

## Implemented features

- Load and display a list of Marvel characters
- Browse popular movies from TMDB
- Discover recently released movies
- Open a movie details screen with artwork, score, and overview
- Load more popular movies while scrolling
- Cache movie records in SQLite
- Fall back to cached popular movies when offline
- Navigate between content sections with a Material navigation drawer
- Display a custom splash screen and bundled typeface

## What I explored

- Integrating two external APIs in one Android application
- Modeling nested Marvel and TMDB JSON responses
- Creating Retrofit service interfaces
- Signing Marvel API requests with timestamped MD5 hashes
- Adding TMDB authentication with an OkHttp interceptor
- Building lists with `RecyclerView`
- Implementing endless scrolling
- Loading remote artwork with Fresco
- Persisting API data with `SQLiteOpenHelper`
- Detecting connectivity and providing a basic cached fallback

## Historical stack

- Java
- Android SDK 23, minimum SDK 15
- Android Support Library
- Retrofit 2 beta
- OkHttp 2
- Gson
- Fresco
- RecyclerView and CardView
- SQLite
- MaterialSearchView
- Gradle 2.8

## Project structure

```text
app/src/main/java/ru/winfected/mcb/
├── db/          # Local movie cache
├── model/
│   ├── marvel/  # Marvel response models
│   └── themoviedb/
├── network/
│   ├── marvel/  # Marvel authentication and endpoints
│   └── themoviedb/
├── ui/
│   ├── marvel/  # Character list
│   └── themoviedb/ # Movie lists, search, and details
└── utils/       # Date helpers
```

## Security note

This historical repository originally stored Marvel and TMDB credentials in source-controlled resources. They must be considered compromised and must not be reused. Anyone experimenting with the code should create their own credentials and inject them outside version control, such as through local Gradle properties or another secret-management mechanism.

## Building today

The project targets a 2015–2016 Android toolchain and is not expected to build unchanged with a current Android Studio installation. Modernization would require updated Gradle and Android plugins, migration to AndroidX, current networking dependencies, HTTPS endpoints, and new locally supplied API credentials.

## Known limitations

- The Comics navigation item is present, but its screen was not implemented.
- The search UI references a `MovieSearchFragment` that is missing from the repository, so the project does not compile as committed without restoring or replacing that screen.
- The Marvel character list binds character names but does not finish binding artwork or a details flow.
- Offline storage is limited to part of the movie experience.
- Error and empty-state handling are minimal.
- Dependencies, API integrations, and Android APIs are obsolete.
- Automated tests are only generated project placeholders.

The repository is retained as a record of early Android development and API-integration practice.

Marvel characters and related marks belong to Marvel. This project uses the TMDB API but is not endorsed or certified by TMDB.
