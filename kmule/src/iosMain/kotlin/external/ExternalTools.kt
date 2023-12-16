package external

import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_IOS_APP_URL
import Constants.YOUTUBE_CHANNEL_URL
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal class ExternalTools(private val application: UIApplication) : ExternalToolsInterface {
    override fun openSpotify(spotifyShowId: String) {
        val spotifyUrl = NSURL(string = SPOTIFY_SHOW_INTENT + spotifyShowId)
        val webUrl = NSURL(string = SPOTIFY_SHOW_URL + spotifyShowId)

        handleIntent(spotifyUrl, webUrl)
    }

    override fun openYouTubeChannel(channelId: String) {
        val appUrl = NSURL(string = YOUTUBE_IOS_APP_URL + channelId)
        val youtubeUrl = NSURL(string = YOUTUBE_CHANNEL_URL + channelId)

        handleIntent(
            appUrl, youtubeUrl
        )
    }

    override fun openInstagramProfile(profileId: String) {
        val instagramUrl = NSURL(string = INSTAGRAM_PROFILE_URL + profileId)
        handleIntent(instagramUrl, instagramUrl)
    }

    override fun openWebPage(url: String) {
        val webUrl = NSURL(string = url)
        handleIntent(webUrl)
    }

    override fun openCallApp(phoneNumber: String?) {
        val callUrl = NSURL(string = "tel:${phoneNumber ?: ""}}")
        handleIntent(callUrl)
    }

    private fun handleIntent(intent: NSURL, fallback: NSURL? = null) {
        if (canOpenUrl(intent)) {
            application.openURL(intent)
        } else {
            // Abre a URL no navegador Safari se o aplicativo do YouTube não estiver disponível
            if (fallback != null) {
                application.openURL(fallback)
            }
        }
    }

    private fun canOpenUrl(url: NSURL): Boolean = application.canOpenURL(url)
}
