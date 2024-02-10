package external

import Constants.INSTAGRAM_URL
import Constants.SPOTIFY_SHOW_INTENT
import Constants.SPOTIFY_URL
import Constants.YOUTUBE_IOS_APP_URL
import Constants.YOUTUBE_URL
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.Foundation.NSURL
import platform.MapKit.MKLaunchOptionsDirectionsModeDriving
import platform.MapKit.MKLaunchOptionsDirectionsModeKey
import platform.MapKit.MKMapItem
import platform.MapKit.MKPlacemark
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

@OptIn(ExperimentalForeignApi::class)
internal class ExternalTools(private val application: UIApplication) : ExternalToolsInterface {
    override fun openSpotify(spotifyShowId: String?) {
        val spotifyUrl =
            NSURL(
                string = if (spotifyShowId != null) "$SPOTIFY_SHOW_INTENT:show:$spotifyShowId" else SPOTIFY_SHOW_INTENT
            )
        val webUrl =
            NSURL(string = if (spotifyShowId != null) SPOTIFY_URL + "show/$spotifyShowId" else SPOTIFY_URL)

        handleIntent(spotifyUrl, webUrl)
    }

    override fun openYouTube(channelId: String?) {
        val appUrl =
            NSURL(string = if (channelId != null) YOUTUBE_IOS_APP_URL + "channel/" + channelId else YOUTUBE_IOS_APP_URL)
        val youtubeUrl =
            NSURL(string = if (channelId != null) YOUTUBE_URL + "channel/" + channelId else YOUTUBE_URL)

        handleIntent(
            appUrl,
            youtubeUrl
        )
    }

    override fun openInstagram(profileId: String?) {
        val instagramUrl = NSURL(string = INSTAGRAM_URL + (profileId ?: ""))
        handleIntent(instagramUrl, instagramUrl)
    }

    override fun openWebPage(url: String) {
        handleIntent(NSURL(string = url))
    }

    override fun openCallApp(phoneNumber: String?) {
        val callUrl = NSURL(string = "tel:${phoneNumber ?: ""}}")
        handleIntent(callUrl)
    }

    override fun openWhatsApp(phoneNumber: String?) {
        val whatsappUrl = NSURL(string = "https://wa.me/${phoneNumber ?: ""}")
        handleIntent(whatsappUrl)
    }

    override fun openMaps(latitude: Double?, longitude: Double?, label: String?) {
        if (latitude == null || longitude == null) {
            val mapItem = MKMapItem.mapItemForCurrentLocation()
            label?.let { mapItem.name = it }
            mapItem.openInMapsWithLaunchOptions(launchOptions = mapOf(MKLaunchOptionsDirectionsModeKey to MKLaunchOptionsDirectionsModeDriving))

        } else {
            val coordinate = CLLocationCoordinate2DMake(latitude, longitude)
            val mapItem = MKMapItem(placemark = MKPlacemark(coordinate = coordinate, addressDictionary = null))
            label?.let { mapItem.name = it }
            mapItem.openInMapsWithLaunchOptions(launchOptions = mapOf(MKLaunchOptionsDirectionsModeKey to MKLaunchOptionsDirectionsModeDriving))
        }

    }

    override fun openEmail(email: String?) {
        val emailUrl = NSURL(string = "mailto:${email ?: ""}")
        handleIntent(emailUrl)
    }

    override fun openSettings() {
        val settingsUrl = NSURL(string = UIApplicationOpenSettingsURLString)
        handleIntent(settingsUrl)
    }

    private fun handleIntent(
        intent: NSURL,
        fallback: NSURL? = null
    ) {
        if (canOpenUrl(intent)) {
            application.openURL(intent)
        } else {
            // Abre a URL no navegador Safari se o aplicativo do YouTube não estiver disponível
            if (fallback != null) {
                application.openURL(fallback)
            }
        }
    }

    private fun canOpenUrl(url: NSURL): Boolean = application.canOpenURL(url)
}
