package com.winqoo.wikia.ui.wikis.section

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.data.models.ExpandedWikiaListResponse
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

        val reponse = ExpandedWikiaListResponse()
        reponse.batches = 1
        reponse.currentBatch = 1
        reponse.total = 10
        reponse.currentBatch = 1
        reponse.items = arrayOf(ExpandedWikiaItem(), ExpandedWikiaItem())

        val single: Single<ExpandedWikiaListResponse> = Single.create { emitter ->
            emitter.onSuccess(reponse)
        }

        whenever(wikiaRepository.getListOfExpandedWikis(any())).thenReturn(single)

        presenter.fetchWikis()

        testScheduler.triggerActions()

        verify(view, Mockito.times(1)).hideError()
        verify(view, Mockito.times(1)).hideEmptyContent()
        verify(view, Mockito.times(1)).hideContent()
        verify(view, Mockito.times(1)).showProgress()
        verify(view).addWikis(reponse.items?.toList()
                ?: ArrayList() )
        verify(view).showContent()
        verify(view).hideProgress()

    }

    @Test
    fun fetchWikis_Success_Empty() {
    }

    @Test
    fun fetchWikis_Success_Fail_No_Connection() {
    }

    @Test
    fun fetchWikis_Success_Fail_Server_Error() {

    }

}