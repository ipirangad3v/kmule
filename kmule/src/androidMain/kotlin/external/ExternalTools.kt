package external

import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL
import exceptions.MissingAndroidContextException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import java.lang.ref.WeakReference

internal class ExternalTools(private val context: WeakReference<Context>?) :
    ExternalToolsInterface {

    private val packageManager: PackageManager
        get() = getContextOrThrow().packageManager

    private fun getContextOrThrow(): Context {
        return context?.get() ?: throw MissingAndroidContextException()
    }

    override fun openSpotify(spotifyShowId: String) {
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

    override fun openYouTubeChannel(channelId: String) {
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

    override fun openInstagramProfile(profileId: String) {
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

    override fun openWebPage(url: String) {
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