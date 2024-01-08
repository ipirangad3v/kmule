import android.content.Context
import exceptions.MissingAndroidContextException
import external.ExternalTools
import external.ExternalToolsInterface
import java.lang.ref.WeakReference
import network.NetworkStatusObserver
import network.NetworkToolsInterface

actual object Kmule : ExternalToolsInterface, NetworkToolsInterface {
    private var context: WeakReference<Context>? = null
    private lateinit var externalTools: ExternalToolsInterface
    private val networkStatusObserver = NetworkStatusObserver()
    actual override val networkStatus = networkStatusObserver.networkStatus


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
}
