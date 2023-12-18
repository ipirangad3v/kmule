import external.ExternalTools
import external.ExternalToolsInterface
import platform.UIKit.UIApplication

actual object Kmule : ExternalToolsInterface {
    private val application = UIApplication.sharedApplication
    private val externalTools = ExternalTools(application)

    actual override fun openSpotify(spotifyShowId: String?) =
        externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTube(channelId: String?) =
        externalTools.openYouTube(channelId)

    actual override fun openInstagram(profileId: String?) =
        externalTools.openInstagram(profileId)

    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)

    actual override fun openCallApp(phoneNumber: String?) = externalTools.openCallApp(phoneNumber)
}
