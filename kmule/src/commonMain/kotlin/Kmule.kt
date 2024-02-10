import external.ExternalToolsInterface
import kotlinx.coroutines.flow.Flow
import network.NetworkToolsInterface

expect object Kmule : ExternalToolsInterface, NetworkToolsInterface {
    override fun openSpotify(spotifyShowId: String?)

    override fun openYouTube(channelId: String?)

    override fun openInstagram(profileId: String?)

    override fun openWebPage(url: String)

    override fun openCallApp(phoneNumber: String?)

    override fun openWhatsApp(phoneNumber: String?)

    override fun openMaps(latitude: Double?, longitude: Double?, label: String?)

    override fun openEmail(email: String?)

    override fun openSettings()

    override val networkStatus: Flow<Boolean>
}
