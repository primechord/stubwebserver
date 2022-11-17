package com.primechord.stubwebserver.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.primechord.stubwebserver.base.BaseTest
import com.primechord.stubwebserver.screens.MainScreen
import com.primechord.stubwebserver.stub.AnyContainer
import com.primechord.stubwebserver.stub.mockResponses
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FreeStyleTests : BaseTest() {

    companion object {
        private const val REPOS_REQUEST_PATH = "/repos"
    }

    @Test
    fun withError() {
        server.mockResponses(
            REPOS_REQUEST_PATH to AnyContainer(
                MockResponse()
                    .setResponseCode(500)
            )
        )

        run {
            MainScreen {
                getDataButton {
                    isDisplayed()
                    click()
                }

                mainTextView {
                    isDisplayed()
                    containsText("null")
                }
            }
        }
    }
}
