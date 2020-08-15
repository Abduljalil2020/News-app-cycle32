package com.route.newsapplicationc32.repos.interfaces

import com.route.newsapplicationc32.api.model.SourcesItem

interface SourcesOnLineDataSource {

    suspend fun getSources(country: String): List<SourcesItem>
}