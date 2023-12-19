package external

import Constants.INSTAGRAM_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_URL
import Constants.YOUTUBE_IOS_APP_URL
import Constants.YOUTUBE_URL
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal class ExternalTools(private val application: UIApplication) : ExternalToolsInterface {
    override fun openSpotify(spotifyShowId: String?) {
        val spotifyUrl =
            NSURL(string = if (spotifyShowId != null) "$SPOTIFY_SHOW_INTENT:show:$spotifyShowId" else SPOTIFY_SHOW_INTENT)
        val webUrl =
            NSURL(string = if (spotifyShowId != null) SPOTIFY_URL + "show/$spotifyShowId" else SPOTIFY_URL)

        handleIntent(spotifyUrl, webUrl)
    }

    override fun openYouTube(channelId: String?) {
        val appUrl =
            NSURL(string = if (channelId != null) YOUTUBE_IOS_APP_URL + "channel/" + channelId else YOUTUBE_IOS_APP_URL)
        val youtubeUrl =
            NSURL(string = if (channelId != null) YOUTUBE_URL + "channel/" + channelId else YOUTUBE_URL)

        handleIntent(
            appUrl, youtubeUrl
        )
    }

    override fun openInstagram(profileId: String?) {
        val instagramUrl = NSURL(string = INSTAGRAM_URL + (profileId ?: ""))
        handleIntent(instagramUrl, instagramUrl)
    }

    override fun openWebPage(url: String) {
        handleIntent(NSURL(string = url))
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
