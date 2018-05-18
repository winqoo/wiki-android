package com.winqoo.wikia.service.api

import com.winqoo.wikia.data.models.ExpandedWikiaListResponse
import com.winqoo.wikia.data.models.UnexpandedWikiaListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
interface WikiaApi {

    @GET("List")
    fun getUnexpandedWikis(@QueryMap queryMap: Map<String, String>): Single<UnexpandedWikiaListResponse>

    @GET("List?expand=1")
    fun getExpandedWikis(@QueryMap queryMap: Map<String, String>): Single<ExpandedWikiaListResponse>

}