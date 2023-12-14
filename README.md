# KMULE - Kotlin Multiplatform Utilities Library For Everyone

<img src="https://github.com/ipirangad3v/kmule/blob/main/images/kmule.png"/>

## What is it?

KMULE is a comprehensive, open-source library designed to enhance the Kotlin Multiplatform development experience. It
provides a wide range of tools and utilities that simplify common programming tasks, fostering cross-platform
consistency and code reusability.

## Project Configuration

To configure your Kotlin Multiplatform project to use the KMULE library, follow these steps:

1. Create a new Kotlin Multiplatform project or open an existing one.
2. Ensure that your project is set up to use Gradle.

## adding dependency

To add `Kmule` to your project, follow:

1. Open the `build.gradle.kts` file (or `build.gradle` if you are using groovy) of your project.
2. Add the Sonatype repository on your project:
   ```groovy
   repositories {
       mavenCentral()
       maven { url = "https://s01.oss.sonatype.org/content/repositories/releases/" }
   }
   ```
3. Add the dependency on the commonMain dependecies of your project:
```groovy
sourceSets{
commonMain.dependencies {
    implementation("digital.thon:kmule:<KMULE_VERSION>")
}

}

```

## Usage

Currently, the KMULE library offers several useful functions that can be used directly in the `commonMain` module. These functions include:

- `openSpotify(showId: String)`: Opens Spotify and directs to the specific show.
- `openYoutube(youtubeChannel: String)`: Opens YouTube and directs to the specified channel.
- `openInstagram(instagramProfile: String)`: Opens Instagram and directs to the specified profile.
- `openWebPage(url: String)`: Opens a web page in the default browser.

Each implementation of these functions is optimized to work natively on different platforms. For example, on Android, these functions use `Intent` to open the corresponding apps, while on iOS, they will try to open the apps directly.

This approach ensures a consistent and integrated user experience across all platforms supported by the KMULE library (ios,jvm,android,IOS,linux).

Here is a basic example of how to use the KMULE library in your project:

```kotlin

import Kmule.openInstagramProfile
import Kmule.openSpotify
import Kmule.openYouTubeChannel

object NavigationHelper {
    fun getNavigationItems(): List<ClickableMenuItem> =
        listOf(
            ClickableMenuItem(
                screen =
                ExternalScreen {
                    openSpotify(SPOTIFY_SHOW_ID)
                },
                resourceId = "podcast.png",
                name = "Podcast"
            ),
            ClickableMenuItem(
                screen =
                ExternalScreen {
                    openYouTubeChannel(YOUTUBE_CHANNEL_ID)
                },
                resourceId = "youtube.png",
                name = "Youtube"
            ),
            ClickableMenuItem(
                screen =
                ExternalScreen {
                    openInstagramProfile(INSTAGRAM_PROFILE_ID)
                },
                resourceId = "instagram.png",
                name = "Instagram"
            ),
        )
}

```

These are currently the features supported by KMULE, new ones coming soon.

## Android Specific Configuration

To ensure that the KMULE library works correctly in an Android app, you need to initialize it in the `onCreate` method
of the `Application` class. Add the following code to your `Application` class:

```kotlin
class App : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()


        // Application context configuration and KMULE initialization
        appContext = applicationContext
        startKmule { appContext }
    }
}
```

This configuration ensures that the KMULE library has access to the application context when necessary.