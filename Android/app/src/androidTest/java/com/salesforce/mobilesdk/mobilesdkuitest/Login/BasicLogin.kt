package com.salesforce.mobilesdk.mobilesdkuitest.Login

import PageObjects.AuthorizationPageObject
import PageObjects.LoginPageObject
import PageObjects.SyncScreenPageObject
import PageObjects.TestApplication
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.*
import org.junit.Before
import org.junit.Test
import org.junit.Assert
import org.junit.runner.RunWith

/**
 * Created by bpage on 2/2/18.
 */

@RunWith(AndroidJUnit4::class)
class BasicLogin {

    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    var app = TestApplication()

    @Before
    fun setupTestApp() {
        // Test Apps have no logout button so clear data
        //Runtime.getRuntime().exec("adb shell pm clear packageName")
        app.launch()
    }

    @Test
    fun login() {
        var loginPage = LoginPageObject()
        loginPage.setUsername("bpage@mobilesdk.com")
        loginPage.setPassword("test1234")
        loginPage.tapLogin()

        AuthorizationPageObject().tapAllow()
        SyncScreenPageObject().assertAppTitle()
    }
}