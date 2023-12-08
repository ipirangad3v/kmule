import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL
import kotlinx.cinterop.ExperimentalForeignApi
import platform.posix.pclose
import platform.posix.popen

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

    @OptIn(ExperimentalForeignApi::class)
    actual fun openWebPage(url: String) {
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
