package com.salesforce.mobilesdk.mobilesdkuitest.Login

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

/**
 * Created by bpage on 2/2/18.
 */

@RunWith(AndroidJUnit4::class)
class BasicLogin {

    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun setupTestApp() {
        var packageName = InstrumentationRegistry.getArguments().get("packageName")
        // Uncomment this to run in Android Studio
        //packageName = "com.mycompany"

        device.pressHome()
        var context = InstrumentationRegistry.getContext()
        var intent = context.packageManager.getLaunchIntentForPackage(packageName as String?)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), 5000)
    }

    @Test
    fun login() {
        var username = device.findObject(UiSelector().resourceId("username"))
        username.click()
        username.setText("bpage3@salesforce.com")

        var password = device.findObject(UiSelector().resourceId("password"))
        password.click()
        password.setText("test123456")

        var login = device.findObject(UiSelector().resourceId("Login"))
        login.click()

        device.wait(Until.hasObject(By.res("oaapprove")), 10000)
        var allowButton = device.findObject(UiSelector().resourceId("oaapprove"))
        allowButton.click()
    }
}