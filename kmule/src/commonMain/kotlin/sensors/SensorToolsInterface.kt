package sensors

interface SensorToolsInterface {
    val accelerometerData: CommonFlow<SensorDataInterface?>
    val isAccelerometerEnabled: Boolean

    fun startAccelerometer()

    fun stopAccelerometer()
}
