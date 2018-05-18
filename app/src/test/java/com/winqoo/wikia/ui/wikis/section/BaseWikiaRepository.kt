package com.winqoo.wikia.ui.wikis.section

import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.data.models.ExpandedWikiaListResponse
import com.winqoo.wikia.data.models.UnexpandedWikiaListResponse
import com.winqoo.wikia.service.repository.WikiaRepositoryInterface
import io.reactivex.Single

class BaseWikiaRepository : WikiaRepositoryInterface{
    val items =   arrayOf(ExpandedWikiaItem())
     fun bindResponse() : ExpandedWikiaListResponse {
        val response = ExpandedWikiaListResponse()
         response.items = items
        return response
    }
    override fun getListOfUnexpandedWikis(map: Map<String, String>): Single<UnexpandedWikiaListResponse> {
       return Single.just(UnexpandedWikiaListResponse())
    }

    override fun getListOfExpandedWikis(map: Map<String, String>): Single<ExpandedWikiaListResponse> {

        return Single.just(bindResponse())
    }
}