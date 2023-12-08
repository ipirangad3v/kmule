package external

import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_APP_URL
import Constants.YOUTUBE_CHANNEL_URL
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal class ExternalTools(private val application: UIApplication) : ExternalToolsInterface {
    override fun openSpotify(spotifyShowId: String) {
        val spotifyUrl = NSURL(string = SPOTIFY_SHOW_INTENT + spotifyShowId)

        if (canOpenUrl(spotifyUrl)) {
            application.openURL(spotifyUrl)
        } else {
            // Se o Spotify não estiver instalado, abra a página web do show
            val webUrl = NSURL(string = SPOTIFY_SHOW_URL + spotifyShowId)
            application.openURL(webUrl)
        }
    }

    override fun openYouTubeChannel(channelId: String) {
        val appUrl = NSURL(string = YOUTUBE_APP_URL + channelId)
        val youtubeUrl = NSURL(string = YOUTUBE_CHANNEL_URL + channelId)

        if (canOpenUrl(appUrl)) {
            application.openURL(appUrl)
        } else {
            // Abre a URL no navegador Safari se o aplicativo do YouTube não estiver disponível
            application.openURL(youtubeUrl)
        }
    }

    override fun openInstagramProfile(profileId: String) {
        val instagramUrl = NSURL(string = INSTAGRAM_PROFILE_URL + profileId)
        if (canOpenUrl(instagramUrl)) {
            application.openURL(instagramUrl)
        } else {
            // Abre a URL no navegador Safari se o aplicativo do Instagram não estiver disponível
            application.openURL(instagramUrl)
        }
    }

    override fun openWebPage(url: String) {
        val webUrl = NSURL(string = url)
        if (canOpenUrl(webUrl)) {
            application.openURL(webUrl)
        }
    }

    private fun canOpenUrl(url: NSURL): Boolean = application.canOpenURL(url)
}
