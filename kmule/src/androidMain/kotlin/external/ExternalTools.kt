package external

import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL
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


    override fun openSpotify(spotifyShowId: String) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SPOTIFY_SHOW_INTENT + spotifyShowId))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(SPOTIFY_SHOW_URL + spotifyShowId))
        handleIntent(
            intent, webIntent
        )
    }

    override fun openYouTubeChannel(channelId: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_CHANNEL_URL + channelId))
        handleIntent(
            intent, intent
        )
    }

    override fun openInstagramProfile(profileId: String) {
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(INSTAGRAM_PROFILE_URL + profileId)

        )
        handleIntent(
            intent, intent
        )
    }

    override fun openWebPage(url: String) {
        handleIntent(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun openCallApp(phoneNumber: String?) {
        handleIntent(
            Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phoneNumber ?: ""}}"))
        )
    }

    private fun getContextOrThrow(): Context {
        return context?.get() ?: throw MissingAndroidContextException()
    }

    private fun handleIntent(intent: Intent, fallback: Intent? = null) {
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
