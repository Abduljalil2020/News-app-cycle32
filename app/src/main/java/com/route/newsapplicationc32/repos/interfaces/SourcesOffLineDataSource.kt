package com.route.newsapplicationc32.repos.interfaces

import com.route.newsapplicationc32.api.model.SourcesItem

interface SourcesOffLineDataSource {

    suspend fun cacheSources(sources: List<SourcesItem>)
    suspend fun getSources(): List<SourcesItem>

}