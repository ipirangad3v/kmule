import android.content.Context
import exceptions.MissingAndroidContextException
import external.ExternalTools
import external.ExternalToolsInterface
import sensors.CommonFlow
import sensors.SensorDataInterface
import sensors.SensorToolsInterface
import sensors.SensorsTools
import java.lang.ref.WeakReference

actual object Kmule : ExternalToolsInterface, SensorToolsInterface {
    private var context: WeakReference<Context>? = null
    private lateinit var externalTools: ExternalToolsInterface
    private lateinit var sensorTools: SensorToolsInterface

    fun startKmule(context: () -> Context) =
        apply {
            val weakContext = WeakReference(context())
            this.context = weakContext
            externalTools = ExternalTools(weakContext)
            sensorTools = SensorsTools(weakContext)
            if (weakContext.get() == null) throw MissingAndroidContextException()
        }

    actual override fun openSpotify(spotifyShowId: String?) = externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTube(channelId: String?) = externalTools.openYouTube(channelId)

    actual override fun openInstagram(profileId: String?) = externalTools.openInstagram(profileId)

    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)

    actual override fun openCallApp(phoneNumber: String?) = externalTools.openCallApp(phoneNumber)

    override val accelerometerData: CommonFlow<SensorDataInterface?>
        get() = sensorTools.accelerometerData
    override val isAccelerometerEnabled: Boolean
        get() = sensorTools.isAccelerometerEnabled

    override fun startAccelerometer() = sensorTools.startAccelerometer()

    override fun stopAccelerometer() = sensorTools.stopAccelerometer()
}
