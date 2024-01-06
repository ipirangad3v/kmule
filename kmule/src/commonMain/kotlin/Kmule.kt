import external.ExternalToolsInterface
import network.NetworkToolsInterface

expect object Kmule : ExternalToolsInterface, NetworkToolsInterface {
    override fun openSpotify(spotifyShowId: String?)

    override fun openYouTube(channelId: String?)

    override fun openInstagram(profileId: String?)

    override fun openWebPage(url: String)

    override fun openCallApp(phoneNumber: String?)
}
