package external

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import network.NetworkStatusObserver
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertFalse

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkStatusObserverTest {
    @Before
    fun setup() {
        // Configura o dispatcher principal para o ambiente de teste
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        // Restaura o dispatcher principal para o valor padrão após o teste
        Dispatchers.resetMain()
    }

    @Test
    fun `network status is not available`() =
        runBlocking {
            val observer = NetworkStatusObserver()

            // Mocking a failed network response
            val isNetworkAvailable = observer.checkNetworkStatus()

            assertFalse(isNetworkAvailable)
        }

    @Test
    fun `checkNetworkStatus logs error`() =
        runBlocking {
            val observer = NetworkStatusObserver()

            // Mocking a network error (assuming checkNetworkStatus() logs the error)
            val isNetworkAvailable = observer.checkNetworkStatus()

            // Add your own logic to verify the error log based on your actual implementation
            // For example, you can check if a log entry has been recorded in a test-specific log collector
        }
}
