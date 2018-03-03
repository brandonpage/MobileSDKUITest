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
    var failedLoginMessage = "App did not successfully testLogin."

    @Before
    fun setupTestApp() {
        app.launch()
    }

    @Test
    fun testLogin() {
        var loginPage = LoginPageObject()
        loginPage.setUsername("circleci@mobilesdk.com")
        loginPage.setPassword("test1234")
        loginPage.tapLogin()
        AuthorizationPageObject().tapAllow()

        device.takeScreenshot(File("/sdcard/testLogin.png"))
        when (app.type) {
            AppType.NATIVE_JAVA, AppType.NATIVE_KOTLIN ->
                NativeSyncScreenPageObject().assertAppTitle()
            AppType.HYBRID_LOCAL -> {
                var title = device.findObject(UiSelector().className("android.view.View").descriptionContains("Users"))
                title.waitForExists(timeout)
                Assert.assertEquals(failedLoginMessage, "Users", title.contentDescription)
            }
            AppType.HYBRID_REMOTE -> {
                Thread.sleep(30000)
                var wview = device.findObject(UiSelector().className("android.webkit.WebView").index(0))

                if (wview.exists()) {
                    Log.i("uia", "just webview: " + wview.contentDescription)
                }
                else {
                    Log.i("uia", "not just webview?")
                }
                var title = device.findObject(UiSelector().className("android.view.View").descriptionContains("Salesforce Mobile SDK Test"))
                title.waitForExists(timeout)
                Log.i("uia", "title: " + title.contentDescription)
                Assert.assertEquals(failedLoginMessage, "Salesforce Mobile SDK Test", title.contentDescription)
            }
            AppType.REACT_NATIVE -> {
                Thread.sleep(30000)
                var title = device.findObject(UiSelector().className("android.widget.TextView").index(0))
                title.waitForExists(timeout)
                Assert.assertEquals(failedLoginMessage, "Mobile SDK Sample App", title.text)
            }
        }
    }
}