package com.karimali.mazad.domain.helpers

import kotlinx.coroutines.*

abstract class NetworkCallHandler {

    suspend fun <T> performNetworkOp(
        networkCall: suspend () -> T,
        onData: suspend (T) -> Unit,
        onError: suspend (Exception?) -> Unit,
        dispatcherOn: CoroutineDispatcher = Dispatchers.IO,
        dispatcherSwitcher: CoroutineDispatcher = Dispatchers.Main
    ) {
        try {
            coroutineScope {

                val data = async(dispatcherOn) {
                    networkCall()
                }
                withContext(dispatcherSwitcher) {
                    onData(data.await())
                }
            }
        } catch (e: Exception) {
            withContext(dispatcherSwitcher) {
                e.printStackTrace()
                onError(e)
            }
        }
    }

}