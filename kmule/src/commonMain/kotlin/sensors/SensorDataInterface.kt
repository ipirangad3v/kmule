package sensors

interface SensorDataInterface {
    val heading: Double
    val sensor: AccelerometerInterface
    val gravity: AccelerometerInterface?
}
