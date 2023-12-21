package sensors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun observe(block: (T) -> Unit): Job {
        val job = SupervisorJob()

        return CoroutineScope(Dispatchers.Main + job).launch {
            origin.collect { value ->
                block(value)
            }
        }
    }
}
