package com.musya.moviez.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.musya.moviez.data.source.remote.vo.ApiResponse
import com.musya.moviez.data.source.remote.vo.StatusResponse
import com.musya.moviez.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    abstract fun loadFromDB(): LiveData<ResultType>

    abstract fun shouldFetch(data: ResultType?): Boolean

    abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource){ newData->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse){ response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.status){
                StatusResponse.SUCCESS ->
                    CoroutineScope(IO).launch {
                        response.body?.let { saveCallResult(it) }
                        Log.d("BOUND 1", response.status.name)

                        withContext(Main){
                            result.addSource(loadFromDB()){ newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    Log.d("BOUND 2", response.status.name)
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
                StatusResponse.EMPTY -> Log.d("BOUND 3", response.status.name)
            }

        }

    }

    private fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}