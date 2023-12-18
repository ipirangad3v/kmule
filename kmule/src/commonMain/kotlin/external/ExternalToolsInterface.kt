package external

internal interface ExternalToolsInterface {
    fun openSpotify(spotifyShowId: String? = null)

    fun openYouTube(channelId: String? = null)

    fun openInstagram(profileId: String? = null)

    fun openWebPage(url: String)

    fun openCallApp(phoneNumber: String? = null)
}
