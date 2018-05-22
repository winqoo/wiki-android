package com.winqoo.wikia.service.repository

import com.winqoo.wikia.data.models.ExpandedWikiaListResponse
import com.winqoo.wikia.data.models.UnexpandedWikiaListResponse
import com.winqoo.wikia.service.api.WikiaApi
import io.reactivex.Single
import retrofit2.Retrofit

class WikiaRepositoryNew(retrofit: Retrofit) : WikiaApi {

    var api: WikiaApi = retrofit.create<WikiaApi>(WikiaApi::class.java)

    override fun getUnexpandedWikis(queryMap: Map<String, String>): Single<UnexpandedWikiaListResponse> {
        return api.getUnexpandedWikis(queryMap)
    }

    override fun getExpandedWikis(queryMap: Map<String, String>): Single<ExpandedWikiaListResponse> {

        var response = api.getExpandedWikis(queryMap)
        return response
    }
}