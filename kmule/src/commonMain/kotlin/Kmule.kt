import external.ExternalToolsInterface
import sensors.SensorToolsInterface

expect object Kmule : ExternalToolsInterface, SensorToolsInterface {
    override fun openSpotify(spotifyShowId: String?)

    override fun openYouTube(channelId: String?)

    override fun openInstagram(profileId: String?)

    override fun openWebPage(url: String)

    override fun openCallApp(phoneNumber: String?)
}
