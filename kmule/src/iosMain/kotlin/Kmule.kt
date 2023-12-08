import external.ExternalTools
import external.ExternalToolsInterface
import platform.UIKit.UIApplication

actual object Kmule : ExternalToolsInterface {
    private val application = UIApplication.sharedApplication
    private val externalTools = ExternalTools(application)

    actual override fun openSpotify(spotifyShowId: String) = externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTubeChannel(channelId: String) = externalTools.openYouTubeChannel(channelId)

    actual override fun openInstagramProfile(profileId: String) = externalTools.openInstagramProfile(profileId)

    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)
}
