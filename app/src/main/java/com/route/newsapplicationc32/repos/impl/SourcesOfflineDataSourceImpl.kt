package com.route.newsapplicationc32.repos.impl

import android.content.SharedPreferences
import com.route.newsapplicationc32.api.model.SourcesItem
import com.route.newsapplicationc32.repos.interfaces.SourcesOffLineDataSource

class SourcesOfflineDataSourceImpl(val sharedPref: SharedPreferences) : SourcesOffLineDataSource {

    override suspend fun cacheSources(sources: List<SourcesItem>) {
        //sharedPref.edit("sources_list",sources.toString());
        // save sources list in room db
    }

    override suspend fun getSources(): List<SourcesItem> {
        // get all sources from room db
        return listOf()
    }
}