package com.winqoo.wikia.service.api

import java.util.*

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
open class QueryParams {
    var queryMap = HashMap<String, String>()
        internal set

    fun addQueryParam(key: String, value: String) {
        queryMap[key] = value
    }

    fun addQueryParams(params: Set<Map.Entry<String, String>>) {
        params.forEach { entry: Map.Entry<String, String> -> queryMap[entry.key] = entry.value }
    }

    companion object {
        val WIKIS_HUB = "hub"
        val WIKIS_LANG = "lang"
        val WIKIS_LIMIT = "limit"
        val WIKIS_BATCH = "batch"
    }
}