import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL
import java.awt.Desktop
import java.net.URI

actual object Kmule {

    actual fun openSpotify(spotifyShowId: String) {
        val spotifyUrl = "$SPOTIFY_SHOW_URL$spotifyShowId"
        openWebPage(spotifyUrl)
    }

    actual fun openYouTubeChannel(channelId: String) {
        val youtubeUrl = "$YOUTUBE_CHANNEL_URL$channelId"
        openWebPage(youtubeUrl)
    }

    actual fun openInstagramProfile(profileId: String) {
        val instagramUrl = "$INSTAGRAM_PROFILE_URL$profileId"
        openWebPage(instagramUrl)
    }

    private fun openWebPage(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(URI(url))
            }
        }
    }

}