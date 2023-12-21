import external.ExternalTools
import external.ExternalToolsInterface
import platform.UIKit.UIApplication
import sensors.CommonFlow
import sensors.SensorDataInterface
import sensors.SensorToolsInterface
import sensors.SensorsTools

actual object Kmule : ExternalToolsInterface, SensorToolsInterface {
    private val application = UIApplication.sharedApplication
    private val externalTools = ExternalTools(application)
    private val sensorsTools = SensorsTools()

    actual override fun openSpotify(spotifyShowId: String?) = externalTools.openSpotify(spotifyShowId)

    actual override fun openYouTube(channelId: String?) = externalTools.openYouTube(channelId)

    actual override fun openInstagram(profileId: String?) = externalTools.openInstagram(profileId)

    actual override fun openWebPage(url: String) = externalTools.openWebPage(url)

    actual override fun openCallApp(phoneNumber: String?) = externalTools.openCallApp(phoneNumber)

    override val accelerometerData: CommonFlow<SensorDataInterface?>
        get() = sensorsTools.accelerometerData
    override val isAccelerometerEnabled: Boolean
        get() = sensorsTools.isAccelerometerEnabled

    override fun startAccelerometer() = sensorsTools.startAccelerometer()

    override fun stopAccelerometer() = sensorsTools.stopAccelerometer()
}
