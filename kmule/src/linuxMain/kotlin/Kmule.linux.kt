import Constants.INSTAGRAM_PROFILE_URL
import Constants.SPOTIFY_SHOW_URL
import Constants.YOUTUBE_CHANNEL_URL

actual object Kmule {
    actual fun openSpotify(spotifyShowId: String) {
        val spotifyUrl = "$SPOTIFY_SHOW_URL$spotifyShowId"
        openWebPage(spotifyUrl)
    }

    actual fun openYouTubeChannel(channelId: String) {
        val youtubeUrl = "$YOUTUBE_CHANNEL_URL$channelId"
        openWebPage(youtubeUrl)
    }

    actual fun openInstagramProfile(profileId: String) {
        val instagramUrl = "$INSTAGRAM_PROFILE_URL$profileId"
        openWebPage(instagramUrl)
    }

    private fun openWebPage(url: String) {
        //TODO
//        try {
//            val runtime = Runtime.getRuntime()
//            // Usando `xdg-open` para abrir a URL no navegador padrão no Linux
//            runtime.exec("xdg-open $url")
//        } catch (e: IOException) {
//            // Tratar exceções, como quando o comando não pode ser executado
//            e.printStackTrace()
//        }
    }
}