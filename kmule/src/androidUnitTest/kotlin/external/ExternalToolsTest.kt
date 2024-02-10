package external

import Constants.INSTAGRAM_URL
import Constants.SPOTIFY_URL
import Constants.YOUTUBE_URL
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ExternalToolsTest {
    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockPackageManager: PackageManager

    private lateinit var externalTools: ExternalTools

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(mockContext.packageManager).thenReturn(mockPackageManager)
        externalTools = ExternalTools(WeakReference(mockContext))
    }

    @Test
    fun testOpenSpotify() {
        externalTools.openSpotify("123")
        verifyIntent(SPOTIFY_URL + "show/123")
    }

    @Test
    fun testOpenSpotifyWithNullId() {
        externalTools.openSpotify(null)
        verifyIntent(SPOTIFY_URL)
    }

    @Test
    fun testOpenYouTube() {
        externalTools.openYouTube("channel123")
        verifyIntent(YOUTUBE_URL + "channel123")
    }

    @Test
    fun testOpenYouTubeWithNullId() {
        externalTools.openYouTube(null)
        verifyIntent(YOUTUBE_URL)
    }

    @Test
    fun testOpenInstagram() {
        externalTools.openInstagram("profile123")
        verifyIntent(INSTAGRAM_URL + "profile123")
    }

    @Test
    fun testOpenInstagramWithNullId() {
        externalTools.openInstagram(null)
        verifyIntent(INSTAGRAM_URL)
    }

    @Test
    fun testOpenWebPage() {
        externalTools.openWebPage("https://www.example.com")
        verifyIntent("https://www.example.com")
    }

    @Test
    fun testOpenCallApp() {
        externalTools.openCallApp("123456789")
        verifyIntent("tel:123456789", Intent.ACTION_DIAL)
    }

    @Test
    fun testOpenWhatsApp() {
        externalTools.openWhatsApp("123456789")
        verifyIntent("https://wa.me/123456789")
    }

    @Test
    fun testOpenWhatsAppWithNullNumber() {
        externalTools.openWhatsApp(null)
        verifyIntent("https://wa.me/")
    }

    @Test
    fun testOpenMaps() {
        externalTools.openMaps(1.0, 2.0)
        verifyIntent("geo:1.0,2.0")
    }

    @Test
    fun testOpenEmail() {
        externalTools.openEmail("anthoni.ipiranga@gmail.com")
        verifyIntent("mailto:anthoni.ipiranga@gmail.com", Intent.ACTION_SENDTO)
    }

    private fun verifyIntent(
        url: String,
        action: String = Intent.ACTION_VIEW
    ) {
        val expectedIntent = Intent(action, Uri.parse(url))

        // ArgumentCaptor para capturar o Intent passado para o método startActivity
        val argumentCaptor = ArgumentCaptor.forClass(Intent::class.java)

        // Verifica se o método startActivity foi chamado com o Intent esperado
        verify(mockContext).startActivity(argumentCaptor.capture())

        // Obtém o Intent capturado
        val capturedIntent = argumentCaptor.value

        // Certifica-se de que o Intent capturado não é nulo antes de comparar detalhes
        assertNotNull("captured intent cannot be null", capturedIntent)

        // Comparação dos detalhes do Intent capturado com o esperado
        assertEquals(expectedIntent.action, capturedIntent.action)
        assertEquals(expectedIntent.data, capturedIntent.data)
    }
}
