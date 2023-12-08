package external

import java.awt.Desktop
import java.net.URI
import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL

class ExternalTools: ExternalToolsInterface {
    override fun openSpotify(spotifyShowId: String) {
        val spotifyUrl = "$SPOTIFY_SHOW_URL$spotifyShowId"
        openWebPage(spotifyUrl)
    }

    override fun openYouTubeChannel(channelId: String) {
        val youtubeUrl = "$YOUTUBE_CHANNEL_URL$channelId"
        openWebPage(youtubeUrl)
    }

    override fun openInstagramProfile(profileId: String) {
        val instagramUrl = "$INSTAGRAM_PROFILE_URL$profileId"
        openWebPage(instagramUrl)
    }

    override fun openWebPage(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(URI(url))
            }
        }
    }
}