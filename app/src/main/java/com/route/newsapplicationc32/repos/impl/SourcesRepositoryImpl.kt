package com.route.newsapplicationc32.repos.impl

import com.route.newsapplicationc32.api.model.SourcesItem
import com.route.newsapplicationc32.repos.interfaces.SourcesOffLineDataSource
import com.route.newsapplicationc32.repos.interfaces.SourcesOnLineDataSource
import com.route.newsapplicationc32.repos.interfaces.SourcesRepository

typealias Sources = List<SourcesItem>

class SourcesRepositoryImpl(
    val offlineDataSource: SourcesOffLineDataSource,
    val onlineDataSource: SourcesOnLineDataSource,
    val networkHandler: NetworkHandler
) : SourcesRepository {

    override suspend fun getSources(countryId: String): Sources {
        var sourcesList: List<SourcesItem>
        if (networkHandler.isOnline()) {
            sourcesList = onlineDataSource.getSources(countryId)
            offlineDataSource.cacheSources(sourcesList)
        } else {
            sourcesList = offlineDataSource.getSources()
        }

        return sourcesList
    }
}