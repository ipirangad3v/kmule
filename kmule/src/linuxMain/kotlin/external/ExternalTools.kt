package external

import kotlinx.cinterop.ExperimentalForeignApi
import platform.posix.pclose
import platform.posix.popen
import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL

internal class ExternalTools : ExternalToolsInterface {
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

    @OptIn(ExperimentalForeignApi::class)
    override fun openWebPage(url: String) {
        try {
            val command = "xdg-open $url"
            popen(command, "r")?.let { file ->
                pclose(file)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}