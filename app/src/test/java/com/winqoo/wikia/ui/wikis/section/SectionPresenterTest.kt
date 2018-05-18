package com.winqoo.wikia.ui.wikis.section

import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.service.api.QueryParams
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SectionPresenterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    private lateinit var view: SectionView

    private var wikiaRepository =  Mockito.spy(BaseWikiaRepository())

    private lateinit var presenter: SectionPresenter

    var params = QueryParams()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = SectionPresenter(wikiaRepository)
        presenter.attachView(view)
    }

    @Test
    fun fetchWikis_Success() {

        presenter.fetchWikis()

        val inOrder: InOrder = Mockito.inOrder(view)

        inOrder.verify(view, Mockito.times(1)).hideError()
        inOrder.verify(view, Mockito.times(1)).hideEmptyContent()
        inOrder.verify(view, Mockito.times(1)).hideContent()
        inOrder.verify(view, Mockito.times(1)).showProgress()
        inOrder.verify(view, Mockito.times(1)).addWikis(wikiaRepository.items?.toList()
                ?: ArrayList<ExpandedWikiaItem>())
        inOrder.verify(view, Mockito.times(1)).showContent()

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