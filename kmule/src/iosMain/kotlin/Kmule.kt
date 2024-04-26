import external.ExternalTools
import external.ExternalToolsInterface
import platform.UIKit.UIApplication

actual object Kmule : ExternalToolsInterface {
    private val application = UIApplication.sharedApplication
    private val externalTools = ExternalTools(application)
    actual override fun openSpotify(spotifyShowId: String?) = externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTube(channelId: String?) = externalTools.openYouTube(channelId)

    actual override fun openInstagram(profileId: String?) = externalTools.openInstagram(profileId)

    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)

    actual override fun openCallApp(phoneNumber: String?) = externalTools.openCallApp(phoneNumber)
    actual override fun openWhatsApp(phoneNumber: String?) = externalTools.openWhatsApp(phoneNumber)

    actual override fun openMaps(latitude: Double?, longitude: Double?, label: String?) =
        externalTools.openMaps(latitude, longitude, label)

    actual override fun openEmail(email: String?) = externalTools.openEmail(email)
    actual override fun openSettings() = externalTools.openSettings()
}
