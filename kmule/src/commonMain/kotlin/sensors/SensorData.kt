package sensors

internal data class SensorData(
    override val heading: Double,
    override val sensor: AccelerometerInterface,
    override val gravity: AccelerometerInterface? = null
) : SensorDataInterface
