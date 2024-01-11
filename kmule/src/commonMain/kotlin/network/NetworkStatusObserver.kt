package network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NetworkStatusObserver {
    private val _networkStatus = MutableStateFlow(false)
    val networkStatus: Flow<Boolean> get() = _networkStatus.asStateFlow()

    init {
        observeNetworkStatus()
    }

    private fun observeNetworkStatus(retrieveTimeMillis: Long = 5000) {
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val isNetworkAvailable = checkNetworkStatus()
                _networkStatus.value = isNetworkAvailable
                delay(retrieveTimeMillis) // Verificar o status da rede a cada 5 segundos
            }
        }
    }

    suspend fun checkNetworkStatus(): Boolean {
        return try {
            val client =
                HttpClient {
                }
            val response = client.get("https://www.google.com").toString()
            response.isNotEmpty()
        } catch (e: Throwable) {
            // Log the error if needed
            false
        }
    }
}
