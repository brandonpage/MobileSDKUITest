package com.salesforce.mobilesdk.mobilesdkuitest.Login

import PageObjects.*
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.util.Log
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/**
 * Created by bpage on 2/2/18.
 */

@RunWith(AndroidJUnit4::class)
class LoginTests {

    var app = TestApplication()
    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    var timeout:Long = 30000
    var failedLoginMessage = "App did not successfully login."
    var username = "circleci@mobilesdk.com"
    var password = "test1234"

    @Before
    fun setupTestApp() {
        app.launch()
    }

    @Test
    fun testLogin() {
        var loginPage = LoginPageObject(app)
        loginPage.setUsername(username)
        loginPage.setPassword(password)
        loginPage.tapLogin()
        AuthorizationPageObject().tapAllow()

        when (app.type) {
            AppType.NATIVE_JAVA, AppType.NATIVE_KOTLIN ->
                NativeSyncScreenPageObject(app).assertAppTitle()
            AppType.HYBRID_LOCAL -> {
                var title = device.findObject(UiSelector().className("android.view.View").descriptionContains("Users"))
                title.waitForExists(timeout)
                Assert.assertEquals(failedLoginMessage, "Users", title.contentDescription)
            }
            AppType.HYBRID_REMOTE -> {
                Thread.sleep(timeout * 3)
                var title = device.findObject(UiSelector().className("android.view.View").descriptionContains("Salesforce Mobile SDK Test"))
                title.waitForExists(timeout * 2)
                Assert.assertEquals(failedLoginMessage, "Salesforce Mobile SDK Test", title.contentDescription)
            }
            AppType.REACT_NATIVE -> {
                Thread.sleep(timeout)
                var title = device.findObject(UiSelector().className("android.widget.TextView").index(0))
                title.waitForExists(timeout)
                Assert.assertEquals(failedLoginMessage, "Mobile SDK Sample App", title.text)
            }
        }
    }
}