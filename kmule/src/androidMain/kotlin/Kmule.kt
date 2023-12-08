import android.content.Context
import external.ExternalTools
import external.ExternalToolsInterface
import java.lang.ref.WeakReference

actual object Kmule : ExternalToolsInterface {
    private var context: WeakReference<Context>? = null
    private val externalTools: ExternalToolsInterface by lazy {
        ExternalTools(context)
    }

    fun startKmule(context: () -> Context) =
        apply {
            this.context = WeakReference(context())
        }

    actual override fun openSpotify(spotifyShowId: String) = externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTubeChannel(channelId: String) = externalTools.openYouTubeChannel(channelId)

    actual override fun openInstagramProfile(profileId: String) = externalTools.openInstagramProfile(profileId)

    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)
}
