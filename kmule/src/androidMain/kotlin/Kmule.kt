import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import java.lang.ref.WeakReference

actual object Kmule {
    private var context: WeakReference<Context>? = null
    private val packageManager: PackageManager
        get() = getContextOrThrow().packageManager

    fun startKmule(context: () -> Context) {
        this.context = WeakReference(context())
    }

    private fun getContextOrThrow(): Context {
        return context?.get() ?: throw MissingAndroidContextException()
    }

    actual fun openSpotify(spotifyShowId: String) {
        getContextOrThrow()
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(SPOTIFY_SHOW_INTENT + spotifyShowId)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        if (intent.resolveActivity(packageManager) != null) {
            context?.get()!!.startActivity(intent)
        } else {
            // Se o Spotify não estiver instalado, abra a página web do show
            val webIntent =
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("$SPOTIFY_SHOW_URL$spotifyShowId"),
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            context?.get()!!.startActivity(webIntent)
        }
    }

    actual fun openYouTubeChannel(channelId: String) {
        getContextOrThrow()
        val youtubeUrl = YOUTUBE_CHANNEL_URL + channelId
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        if (intent.resolveActivity(packageManager) != null) {
            context?.get()!!.startActivity(intent)
        } else {
            // Abre a URL no navegador se nenhum aplicativo correspondente for encontrado
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            context?.get()!!.startActivity(webIntent)
        }
    }

    actual fun openInstagramProfile(profileId: String) {
        getContextOrThrow()
        val instagramUrl = INSTAGRAM_PROFILE_URL + profileId
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        if (intent.resolveActivity(packageManager) != null) {
            context?.get()!!.startActivity(intent)
        } else {
            // Abre a URL no navegador se nenhum aplicativo correspondente for encontrado
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            context?.get()!!.startActivity(webIntent)
        }
    }

    actual fun openWebPage(url: String) {
        getContextOrThrow()
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

        if (intent.resolveActivity(packageManager) != null) {
            context?.get()!!.startActivity(intent)
        } else {
            // Abre a URL no navegador se nenhum aplicativo correspondente for encontrado
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            context?.get()!!.startActivity(webIntent)
        }
    }
}
