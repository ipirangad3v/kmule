package sensors

internal data class AccelerometerData(
    override val x: Double,
    override val y: Double,
    override val z: Double
) : AccelerometerInterface
