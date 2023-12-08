import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual object Kmule {
    actual fun openSpotify(spotifyShowId: String) {
        val spotifyUrl = NSURL(string = SPOTIFY_SHOW_INTENT + spotifyShowId)

        val application = UIApplication.sharedApplication
        if (application.canOpenURL(spotifyUrl)) {
            application.openURL(spotifyUrl)
        } else {
            // Se o Spotify não estiver instalado, abra a página web do show
            val webUrl = NSURL(string = SPOTIFY_SHOW_URL + spotifyShowId)
            application.openURL(webUrl)
        }
    }

    actual fun openYouTubeChannel(channelId: String) {
        val youtubeUrl = NSURL(string = YOUTUBE_CHANNEL_URL + channelId)

        val application = UIApplication.sharedApplication
        if (application.canOpenURL(youtubeUrl)) {
            application.openURL(youtubeUrl)
        } else {
            // Abre a URL no navegador Safari se o aplicativo do YouTube não estiver disponível
            application.openURL(youtubeUrl)
        }
    }

    actual fun openInstagramProfile(profileId: String) {
        val instagramUrl = NSURL(string = INSTAGRAM_PROFILE_URL + profileId)
        val application = UIApplication.sharedApplication
        if (application.canOpenURL(instagramUrl)) {
            application.openURL(instagramUrl)
        } else {
            // Abre a URL no navegador Safari se o aplicativo do Instagram não estiver disponível
            application.openURL(instagramUrl)
        }
    }
}