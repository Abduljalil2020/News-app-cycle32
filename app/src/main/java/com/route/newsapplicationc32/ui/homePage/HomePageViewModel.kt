package com.route.newsapplicationc32.ui.homePage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.newsapplicationc32.api.ApiManager
import com.route.newsapplicationc32.api.model.ArticlesItem
import com.route.newsapplicationc32.api.model.NewsResponse
import com.route.newsapplicationc32.api.model.SourcesItem
import com.route.newsapplicationc32.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageViewModel : ViewModel() {
    val newsSourceLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsListLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val messageLiveData = MutableLiveData<String>()
    val showLoadingLiveData = MutableLiveData<Boolean>()

    fun getSources() {
        showLoadingLiveData.value = true
        ApiManager.webServices()
            .getSources("us")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("here", "fail")
                    showLoadingLiveData.value = false
                    messageLiveData.value = t.localizedMessage
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    showLoadingLiveData.value = false

                    if ("ok".equals(response.body()?.status)) {
                        newsSourceLiveData.value = response.body()?.sources
                    } else {
                        messageLiveData.value = response.body()?.errorMessage
                    }
                    // newsSourceLiveData.postValue(  response.body().sources);
                }
            })
    }

    fun getNewsBySourceId(id: String?) {
        showLoadingLiveData.value = true
        ApiManager.webServices()
            .getNewsBySourceId(id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    showLoadingLiveData.value = false
                    messageLiveData.value = t.localizedMessage
//                    showMessage(title = null, message = t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    showLoadingLiveData.value = false
                    if ("ok".equals(response.body()?.status)) {
                        // adapter.changeData(response.body()?.articles ?: listOf())
                        newsListLiveData.value = response.body()?.articles
                    } else {
                        messageLiveData.value = response.body()?.errorMessage
                    }
                }
            })
    }
}