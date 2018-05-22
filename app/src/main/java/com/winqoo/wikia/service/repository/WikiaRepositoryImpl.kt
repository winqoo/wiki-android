package com.winqoo.wikia.service.repository

import com.winqoo.wikia.data.models.ExpandedWikiaListResponse
import com.winqoo.wikia.data.models.UnexpandedWikiaListResponse
import com.winqoo.wikia.service.api.WikiaApi
import io.reactivex.Single

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class WikiaRepositoryImpl(private val wikiaApi: WikiaApi) : WikiaRepository {

    override fun getListOfUnexpandedWikis(map: Map<String, String>): Single<UnexpandedWikiaListResponse> {
        return wikiaApi.getUnexpandedWikis(map)
    }

    override fun getListOfExpandedWikis(map: Map<String, String>): Single<ExpandedWikiaListResponse> {
        return wikiaApi.getExpandedWikis(map)
    }

}