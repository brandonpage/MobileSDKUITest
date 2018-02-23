package com.salesforce.mobilesdk.mobilesdkuitest.Login

import PageObjects.LoginPageObject
import PageObjects.TestApplication
import android.app.Instrumentation
import android.app.UiAutomation
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.util.Log
import org.junit.Assert
import kotlin.concurrent.thread

/**
 * Created by bpage on 2/2/18.
 */

@RunWith(AndroidJUnit4::class)
class BasicLogin {

    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    var app = TestApplication()
    var loginPage = LoginPageObject()

    @Before
    fun setupTestApp() {
        // Test Apps have no logout button so clear data
        //Runtime.getRuntime().exec("adb shell pm clear packageName")
        app.launch()
    }

    @Test
    fun login() {
        loginPage.setUsername("bpage@mobilesdk.com")
        loginPage.setPassword("test1234")
        loginPage.tapLogin()

        //device.wait(Until.hasObject(By.res("oaapprove")), 240000)
        //var allowButton = device.findObject(UiSelector().resourceId("oaapprove"))
        var allowButton = device.findObject(UiSelector().className("android.widget.Button").index(0))
        allowButton.waitForExists(5000)
        allowButton.click()

        when (app.packageName) {
            "com.salesforce.native_java", "com.salesforce.native_kotlin" -> {
                var titleBar = device.findObject(UiSelector().resourceId("android:id/action_bar_title"))
                titleBar.waitForExists(60000)
                Assert.assertEquals("App did not load.", "Contact List", titleBar.text)

                //var contact = device.findObject(UiSelector().resourceId(packageName + ":id/obj_name").index(0))
                //Assert.assertEquals("Contacts did not load.", "EXPECTED CONTACT NAME", contact.text)
            }
            "com.salesforce.hybrid_local", "com.salesforce.hybrid_local" -> {
                //TODO: implement
            }
            "com.salesforce.react_native" -> {
                //TODO: implement
            }
        }
    }
}