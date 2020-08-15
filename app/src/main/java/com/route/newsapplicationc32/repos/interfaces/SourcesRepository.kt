package com.route.newsapplicationc32.repos.interfaces

import com.route.newsapplicationc32.api.model.SourcesItem

interface SourcesRepository {

    suspend fun getSources(countryId: String): List<SourcesItem>
}