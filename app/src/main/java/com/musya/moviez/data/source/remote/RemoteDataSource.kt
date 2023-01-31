package com.musya.moviez.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.musya.moviez.data.source.remote.response.*
import com.musya.moviez.data.source.remote.vo.ApiResponse
import com.musya.moviez.utils.ApiServices
import com.musya.moviez.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieAPiServices: ApiServices) {

   fun getMovies(): LiveData<ApiResponse<MoviesResponse>>{
       EspressoIdlingResource.increment()
       val resultMoviesResponse = MutableLiveData<ApiResponse<MoviesResponse>>()
       CoroutineScope(IO).launch {
           try {
               val response = movieAPiServices.getMovies().await()
               resultMoviesResponse.postValue(ApiResponse.success(response))
           } catch (e: IOException){
               e.printStackTrace()
               resultMoviesResponse.postValue(
                   ApiResponse.error(
                       e.message.toString(),
                       MoviesResponse(0, mutableListOf())
                   )
               )
           }
       }
       EspressoIdlingResource.decrement()
       return  resultMoviesResponse
   }

    fun getMovieDetail( idMovie: Int): LiveData<ApiResponse<MovieDetailResponse>>{
        EspressoIdlingResource.increment()
        val resultMovieDetail = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        CoroutineScope(IO).launch {
            try {
                val response = movieAPiServices.getMovieDetail(idMovie).await()
                resultMovieDetail.postValue(ApiResponse.success(response))
            } catch (e: IOException){
                e.printStackTrace()
                resultMovieDetail.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        MovieDetailResponse("", "", mutableListOf(), 0.0, 123, 0, "", "")
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieDetail
    }

    fun getTvShow(): LiveData<ApiResponse<TvResponse>>{
        EspressoIdlingResource.increment()
        val resultTvShowResponse = MutableLiveData<ApiResponse<TvResponse>>()
        CoroutineScope(IO).launch {
            try {
                val response = movieAPiServices.getTvShow().await()
                resultTvShowResponse.postValue(ApiResponse.success(response))
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowResponse
    }

    fun getTvShowDetail(idTvShow: Int): LiveData<ApiResponse<TvDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultTvDetail = MutableLiveData<ApiResponse<TvDetailResponse>>()
        CoroutineScope(IO).launch {
            try {
                val response = movieAPiServices.getTvDetail(idTvShow).await()
                resultTvDetail.postValue(ApiResponse.success(response))
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvDetail
    }

    fun getCast(id: Int, isSeries: Boolean): LiveData<ApiResponse<CastsResponse>> {
        EspressoIdlingResource.increment()
        val resultCastResponse = MutableLiveData<ApiResponse<CastsResponse>>()
        CoroutineScope(IO).launch {
            try {
                val response = if (isSeries){
                    movieAPiServices.getTvCasts(id).await()
                } else {
                    movieAPiServices.getMovieCasts(id).await()
                }
                resultCastResponse.postValue(ApiResponse.success(response))
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return resultCastResponse
    }

    fun getEpisodes(idTv: Int): LiveData<ApiResponse<EpisodesResponse>> {
        EspressoIdlingResource.increment()
        val resultEpisodeResponse = MutableLiveData<ApiResponse<EpisodesResponse>>()
        CoroutineScope(IO).launch {
            try {
                val response = movieAPiServices.getEpisodes(idTv, 1).await()
                resultEpisodeResponse.postValue(ApiResponse.success(response))
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return resultEpisodeResponse
    }
}