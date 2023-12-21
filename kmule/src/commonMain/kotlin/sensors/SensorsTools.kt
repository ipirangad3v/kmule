package sensors

internal expect class SensorsTools : SensorToolsInterface {
    override val accelerometerData: CommonFlow<SensorDataInterface?>
    override val isAccelerometerEnabled: Boolean

    override fun startAccelerometer()

    override fun stopAccelerometer()
}
