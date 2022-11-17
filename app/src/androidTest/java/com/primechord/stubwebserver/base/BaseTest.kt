package com.primechord.stubwebserver.base

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.primechord.stubwebserver.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule

abstract class BaseTest(
    protected val server: MockWebServer = MockWebServer(),
    builder: Kaspresso.Builder = Kaspresso.Builder.simple {
        beforeEachTest {
            server.start(8080)
        }
        afterEachTest {
            server.shutdown()
        }
    },
) : TestCase(builder) {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
}
