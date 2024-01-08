package network

import kotlinx.coroutines.flow.Flow

internal interface NetworkToolsInterface {
    val networkStatus: Flow<Boolean>
}
