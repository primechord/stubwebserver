package com.primechord.stubwebserver.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.primechord.stubwebserver.base.BaseTest
import com.primechord.stubwebserver.screens.MainScreen
import com.primechord.stubwebserver.stub.MockUtils
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DispatcherStyleTests : BaseTest() {

    companion object {
        private const val REPOS_REQUEST_PATH = "/repos"
        private const val REPOS_RESPONSE_ASSET = "repos.json"
    }

    private fun MockWebServer.stubRepos(reposResponseAsset: String) {
        val mockUtils = MockUtils()
        val requestResponseMap = mockUtils.baseRequestResponseMap
        requestResponseMap[REPOS_REQUEST_PATH] = mockUtils.successResponse(reposResponseAsset)
        dispatcher = mockUtils.createDispatcher(requestResponseMap)
    }

    @Test
    fun withExtension() {
        server.stubRepos(REPOS_RESPONSE_ASSET)

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText("expected text")
                }
            }
        }
    }

    @Test
    fun pureDispatcher() {
        server.dispatcher = object : Dispatcher() {
            val mockUtils = MockUtils()
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    request.path?.contains(REPOS_REQUEST_PATH) == true -> {
                        mockUtils.successResponse {
                            mockUtils.jsonUtil.readJSONFromAsset(REPOS_RESPONSE_ASSET)
                        }
                    }
                    else -> mockUtils.failNotFoundResponseMock()
                }
            }
        }

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText("expected text")
                }
            }
        }
    }
}
