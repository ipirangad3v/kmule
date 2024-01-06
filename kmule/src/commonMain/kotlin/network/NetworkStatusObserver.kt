package network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    private fun observeNetworkStatus() {
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val isNetworkAvailable = checkNetworkStatus()
                _networkStatus.value = isNetworkAvailable
                kotlinx.coroutines.delay(5000) // Verificar o status da rede a cada 5 segundos
            }
        }
    }

    private suspend fun checkNetworkStatus(): Boolean {
        return try {
            val client = HttpClient() {
            }
            val response = client.get("https://www.google.com").toString()
            response.isNotEmpty()
        } catch (e: Throwable) {
            // Log the error if needed
            false
        }
    }
}


