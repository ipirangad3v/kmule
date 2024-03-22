import android.content.Context
import exceptions.MissingAndroidContextException
import external.ExternalTools
import external.ExternalToolsInterface
import network.NetworkStatusObserver
import network.NetworkToolsInterface
import java.lang.ref.WeakReference

actual object Kmule : ExternalToolsInterface, NetworkToolsInterface {
    private var context: WeakReference<Context>? = null
    private lateinit var externalTools: ExternalToolsInterface
    private val networkStatusObserver = NetworkStatusObserver()
    actual override val networkStatus = networkStatusObserver.networkStatus

    @Suppress("unused")
    //used by library users
    fun startKmule(context: () -> Context) =
        apply {
            val weakContext = WeakReference(context())
            this.context = weakContext
            externalTools = ExternalTools(weakContext)
            if (weakContext.get() == null) throw MissingAndroidContextException()
        }

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
