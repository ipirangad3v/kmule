import android.content.Context
import exceptions.MissingAndroidContextException
import external.ExternalTools
import external.ExternalToolsInterface
import java.lang.ref.WeakReference

actual object Kmule : ExternalToolsInterface {
    private var context: WeakReference<Context>? = null
    private lateinit var externalTools: ExternalToolsInterface

    fun startKmule(context: () -> Context) =
        apply {
            val weakContext = WeakReference(context())
            this.context = weakContext
            externalTools = ExternalTools(weakContext)
            if (weakContext.get() == null) throw MissingAndroidContextException()
        }

    actual override fun openSpotify(spotifyShowId: String) =
        externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTubeChannel(channelId: String) =
        externalTools.openYouTubeChannel(channelId)

    actual override fun openInstagramProfile(profileId: String) =
        externalTools.openInstagramProfile(profileId)

    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)

    actual override fun openCallApp(phoneNumber: String?) = externalTools.openCallApp(phoneNumber)
}
