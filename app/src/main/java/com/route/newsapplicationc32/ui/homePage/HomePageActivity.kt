package com.route.newsapplicationc32.ui.homePage

import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.route.newsapplicationc32.R
import com.route.newsapplicationc32.api.ApiManager
import com.route.newsapplicationc32.api.model.NewsResponse
import com.route.newsapplicationc32.api.model.SourcesItem
import com.route.newsapplicationc32.api.model.SourcesResponse
import com.route.notesapplicationc32.Base.BaseActivity
import kotlinx.android.synthetic.main.content_home_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageActivity : BaseActivity() {

    val adapter = NewsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        setSupportActionBar(findViewById(R.id.toolbar))
        getSources()
        recycler_view.adapter = adapter

        val user = User(name = "Mohamed", id = 20)
        val gsonObject = Gson()
        val userJsonString = gsonObject.toJson(user)
        val user2 = gsonObject.fromJson(userJsonString, User::class.java)

    }

    data class User(
        val id: Int,
        val name: String
    )

    fun getSources() {
        showLoadingDialog(getString(R.string.loading))
        ApiManager.webServices()
            .getSources("us")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    hideLoadingDialge()
                    Log.e("here", "fail")
                    showMessage(title = null, message = t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    hideLoadingDialge()
                    if ("ok".equals(response.body()?.status)) {
                        showSourcesInTabLayout(response.body()?.sources)
                    } else {
                        showMessage(title = null, message = response.body()?.errorMessage)
                    }
                }
            })
    }

    private fun showSourcesInTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach { source ->
            val tab = tablayout.newTab()
            tab.text = source?.name
            tab.tag = source
            tablayout.addTab(tab)
        }
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                getNewsBySourceId(source.id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as SourcesItem
                getNewsBySourceId(source.id)
            }
        })
        tablayout.getTabAt(0)?.select()
    }

    private fun getNewsBySourceId(id: String?) {
        showLoadingDialog(getString(R.string.loading))
        ApiManager.webServices()
            .getNewsBySourceId(id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    hideLoadingDialge()
                    showMessage(title = null, message = t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    hideLoadingDialge()
                    if ("ok".equals(response.body()?.status)) {
                        adapter.changeData(response.body()?.articles ?: listOf())
                    } else {
                        showMessage(title = null, message = response.body()?.errorMessage)
                    }
                }
            })
    }
}