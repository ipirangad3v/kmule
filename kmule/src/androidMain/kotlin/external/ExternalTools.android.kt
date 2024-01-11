package external

import Constants.INSTAGRAM_URL
import Constants.SPOTIFY_URL
import Constants.YOUTUBE_URL
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import exceptions.MissingAndroidContextException
import java.lang.ref.WeakReference

internal class ExternalTools(private val context: WeakReference<Context>?) :
    ExternalToolsInterface {
    private val packageManager: PackageManager
        get() = getContextOrThrow().packageManager

    override fun openSpotify(spotifyShowId: String?) {
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(if (spotifyShowId != null) "${SPOTIFY_URL}show/$spotifyShowId" else SPOTIFY_URL)
            )
        handleIntent(
            intent,
            intent
        )
    }

    override fun openYouTube(channelId: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_URL + (channelId ?: "")))
        handleIntent(
            intent,
            intent
        )
    }

    override fun openInstagram(profileId: String?) {
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(INSTAGRAM_URL + (profileId ?: ""))
            )
        handleIntent(
            intent,
            intent
        )
    }

    override fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        handleIntent(intent, intent)
    }

    override fun openCallApp(phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phoneNumber ?: ""}"))
        handleIntent(
            intent,
            intent
        )
    }

    private fun getContextOrThrow(): Context {
        return context?.get() ?: throw MissingAndroidContextException()
    }

    private fun handleIntent(
        intent: Intent,
        fallback: Intent? = null
    ) {
        getContextOrThrow()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        fallback?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (intent.resolveActivity(packageManager) != null) {
            context?.get()?.startActivity(intent)
        } else {
            context?.get()?.startActivity(fallback)
        }
    }
}
