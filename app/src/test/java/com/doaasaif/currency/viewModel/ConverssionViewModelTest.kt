package com.doaasaif.currency.viewModel

import androidx.lifecycle.Observer
import androidx.test.platform.app.InstrumentationRegistry
import com.doaasaif.currency.R
import com.doaasaif.currency.module.networkmodule.ApiStatus
import com.doaasaif.currency.module.networkmodule.RertofitAPIClient
import com.doaasaif.data.remote.ApiService
import com.doaasaif.domain.repo.CurrencyConverssionRepo
import com.doaasaif.domain.usecase.CurrencyConverssionUsecase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import java.security.AccessController.getContext


class ConverssionViewModelTest {
    private val converssionUseCase: CurrencyConverssionUsecase = mockk()
     private lateinit var viewModel: ConverssionViewModel

    @Mock
    var observer: Observer<ApiStatus>? = null

    @Mock
    var apiService: ApiService? = null

    @Before
    fun setup() {
      viewModel = ConverssionViewModel(InstrumentationRegistry.getInstrumentation().context, converssionUseCase)
    }
    @Test
    fun `should emit error object when api response error`() {
        val message = "Error from api"
        coEvery {
            converssionUseCase.getSymbols( "536843dd248e541cb0947e2c7b66b394")
        } throws IllegalAccessException(message)

        viewModel.getSymbols("536843dd248e541cb0947e2c7b66b394" )

        coVerify {
            converssionUseCase.getSymbols("536843dd248e541cb0947e2c7b66b394" )
        }
        Assert.assertEquals(InstrumentationRegistry.getInstrumentation().context.getString(R.string.api_error), viewModel.error.value)

    }


}
