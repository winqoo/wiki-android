package com.winqoo.wikia.ui.wikis.section

import com.winqoo.wikia.extension.applySchedulers
import com.winqoo.wikia.service.api.QueryParams
import com.winqoo.wikia.service.repository.WikiaRepositoryInterface
import com.winqoo.wikia.ui.common.base.BasePresenter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class SectionPresenter @Inject
constructor(private val wikiaRepository: WikiaRepositoryInterface) : BasePresenter<SectionView>() {

    private val pageSize = 10
    private var currentPage = 1
    private var hasNextPage = true
    var params = QueryParams()

    fun fetchWikis() {
        if (!hasNextPage) {
            return
        }
        params.addQueryParam(QueryParams.WIKIS_LIMIT, pageSize.toString())
        params.addQueryParam(QueryParams.WIKIS_BATCH, currentPage.toString())
        compositeDisposable.add(wikiaRepository.getListOfExpandedWikis(params.queryMap)
                .applySchedulers()
                .doOnSubscribe({
                    view?.setPaginationLoading(true)
                    view?.hideError()
                    view?.hideEmptyContent()
                    if (currentPage == 1) {
                        view?.hideContent()
                        view?.showProgress()
                    }
                })
                .subscribeBy(
                        onSuccess = {
                            it.let {
                                hasNextPage = it.next ?: 0 >= 1
                                currentPage = (it.currentBatch ?: 0) + 1

                                it.items.let{
                                    if(it!!.isNotEmpty()){
                                        view?.addWikis(it.toList() ?: ArrayList())
                                        view?.setPaginationEnd(!hasNextPage)
                                        view?.setPaginationLoading(false)
                                        view?.showContent()
                                    }  else{
                                        view?.showEmptyContent()
                                    }
                                }
                            }
                            view?.hideProgress()
                        },

                        onError = {
                            view?.hideProgress()
                            view?.showError(it)
                        }

                )
        )

    }
}