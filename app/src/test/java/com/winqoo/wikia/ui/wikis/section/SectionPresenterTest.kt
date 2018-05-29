package com.winqoo.wikia.ui.wikis.section

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.data.models.ExpandedWikiaListResponse
import com.winqoo.wikia.service.common.NoConnectivityException
import com.winqoo.wikia.service.repository.WikiaRepository
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SectionPresenterTest {

    private val view: SectionView = mock()

    private val testScheduler = TestScheduler()

    private var wikiaRepository: WikiaRepository = mock()

    private lateinit var presenter: SectionPresenter

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

        presenter = SectionPresenter(wikiaRepository)
        presenter.attachView(view)
    }

    @Test
    fun fetchWikis_Success() {

        val response = ExpandedWikiaListResponse()
        response.batches = 1
        response.currentBatch = 1
        response.total = 10
        response.currentBatch = 1
        response.items = arrayOf(ExpandedWikiaItem(), ExpandedWikiaItem())

        val single: Single<ExpandedWikiaListResponse> = Single.create { emitter ->
            emitter.onSuccess(response)
        }

        whenever(wikiaRepository.getListOfExpandedWikis(any())).thenReturn(single)

        presenter.fetchWikis()

        testScheduler.triggerActions()

        verify(view, Mockito.times(1)).hideError()
        verify(view, Mockito.times(1)).hideEmptyContent()
        verify(view, Mockito.times(1)).hideContent()
        verify(view, Mockito.times(1)).showProgress()
        verify(view).addWikis(response.items?.toList()
                ?: ArrayList())
        verify(view).showContent()
        verify(view).hideProgress()

    }

    @Test
    fun fetchWikis_Success_Empty() {
        val response = ExpandedWikiaListResponse()
        response.batches = 1
        response.currentBatch = 1
        response.total = 10
        response.currentBatch = 1
        response.items = arrayOf()

        val single: Single<ExpandedWikiaListResponse> = Single.create { emitter ->
            emitter.onSuccess(response)
        }

        whenever(wikiaRepository.getListOfExpandedWikis(any())).thenReturn(single)

        presenter.fetchWikis()

        testScheduler.triggerActions()

        verify(view, Mockito.times(1)).hideError()
        verify(view, Mockito.times(1)).hideEmptyContent()
        verify(view, Mockito.times(1)).hideContent()
        verify(view, Mockito.times(1)).showProgress()
        verify(view).showEmptyContent()
        verify(view).hideProgress()
    }

    @Test
    fun fetchWikis_Success_Fail_No_Connection() {

        val error = NoConnectivityException()
        val single: Single<ExpandedWikiaListResponse> = Single.create { emitter ->
            emitter.onError(error)
        }

        whenever(wikiaRepository.getListOfExpandedWikis(any())).thenReturn(single)
        presenter.fetchWikis()

        testScheduler.triggerActions()

        verify(view, Mockito.times(1)).hideError()
        verify(view, Mockito.times(1)).hideEmptyContent()
        verify(view, Mockito.times(1)).hideContent()
        verify(view, Mockito.times(1)).showProgress()
        verify(view).hideProgress()
        verify(view).showError(error)
    }

    @Test
    fun fetchWikis_Success_Fail_Server_Error() {

        val error = Exception()
        val single: Single<ExpandedWikiaListResponse> = Single.create { emitter ->
            emitter.onError(error)
        }

        whenever(wikiaRepository.getListOfExpandedWikis(any())).thenReturn(single)
        presenter.fetchWikis()

        testScheduler.triggerActions()

        verify(view, Mockito.times(1)).hideError()
        verify(view, Mockito.times(1)).hideEmptyContent()
        verify(view, Mockito.times(1)).hideContent()
        verify(view, Mockito.times(1)).showProgress()
        verify(view).hideProgress()
        verify(view).showError(error)
    }

}