package com.winqoo.wikia.service.repository

import com.winqoo.wikia.data.models.ExpandedWikiaListResponse
import com.winqoo.wikia.data.models.UnexpandedWikiaListResponse
import io.reactivex.Single

interface WikiaRepository {

    fun getListOfUnexpandedWikis(map: Map<String, String>): Single<UnexpandedWikiaListResponse>

    fun getListOfExpandedWikis(map: Map<String, String>): Single<ExpandedWikiaListResponse>

}