import external.ExternalTools
import external.ExternalToolsInterface
import kotlinx.cinterop.ExperimentalForeignApi

actual object Kmule : ExternalToolsInterface {
    private val externalTools by lazy { ExternalTools() }

    actual override fun openSpotify(spotifyShowId: String) = externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTubeChannel(channelId: String) = externalTools.openYouTubeChannel(channelId)

    actual override fun openInstagramProfile(profileId: String) = externalTools.openInstagramProfile(profileId)

    @OptIn(ExperimentalForeignApi::class)
    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)
}
