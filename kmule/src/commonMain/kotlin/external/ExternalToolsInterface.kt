package external

internal interface ExternalToolsInterface {
    fun openSpotify(spotifyShowId: String)

    fun openYouTubeChannel(channelId: String)

    fun openInstagramProfile(profileId: String)

    fun openWebPage(url: String)

    fun openCallApp(phoneNumber: String?)
}
