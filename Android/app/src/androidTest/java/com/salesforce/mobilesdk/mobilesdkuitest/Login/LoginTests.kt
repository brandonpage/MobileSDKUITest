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
    var timeout:Long = 1000 * 5
    var failedLoginMessage = "App did not successfully basicLogin."

    @Before
    fun setupTestApp() {
        // Some Test Apps have no logout button so clear data
        // Runtime.getRuntime().exec("adb shell pm clear packageName")
        app.launch()
    }

    @Test
    fun basicLogin() {
        var loginPage = LoginPageObject()
        loginPage.setUsername("circleci@mobilesdk.com")
        loginPage.setPassword("test1234")
        loginPage.tapLogin()
        AuthorizationPageObject().tapAllow()

        device.takeScreenshot(File("/sdcard/basicLogin.png"))
        when (app.type) {
            AppType.NATIVE_JAVA, AppType.NATIVE_KOTLIN ->
                NativeSyncScreenPageObject().assertAppTitle()
            AppType.HYBRID_LOCAL -> {
                var title = device.findObject(UiSelector().className("android.view.View").descriptionContains("Users"))
                title.waitForExists(timeout)
                Assert.assertEquals(failedLoginMessage, "Users", title.contentDescription)
            }
            AppType.HYBRID_REMOTE -> {
                var title = device.findObject(UiSelector().className("android.view.View").index(1))
                title.waitForExists(timeout)
                Assert.assertEquals(failedLoginMessage, "Salesforce Mobile SDK Test", title.contentDescription)
            }
            AppType.REACT_NATIVE -> {
                var title = device.findObject(UiSelector().className("android.widget.TextView").index(0))
                title.waitForExists(timeout)
                Assert.assertEquals(failedLoginMessage, "Mobile SDK Sample App", title.contentDescription)
            }
        }
    }
}