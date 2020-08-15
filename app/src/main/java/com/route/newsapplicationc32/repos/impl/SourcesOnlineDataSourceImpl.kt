package com.route.newsapplicationc32.repos.impl

import com.route.newsapplicationc32.api.WebServices
import com.route.newsapplicationc32.api.model.SourcesItem
import com.route.newsapplicationc32.repos.interfaces.SourcesOnLineDataSource

class SourcesOnlineDataSourceImpl(val webServices: WebServices) : SourcesOnLineDataSource {
    override suspend fun getSources(country: String): List<SourcesItem> {
        val response = webServices.getSources(country)
        return response.sources ?: listOf()
    }
}