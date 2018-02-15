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
import kotlin.concurrent.thread

/**
 * Created by bpage on 2/2/18.
 */

@RunWith(AndroidJUnit4::class)
class BasicLogin {

    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    var packageName = ""

    @Before
    fun setupTestApp() {
        packageName = InstrumentationRegistry.getArguments().get("packageName") as String
        // Uncomment this to run in Android Studio
        //packageName = "com.salesforce.react_native"

        device.pressHome()
        var context = InstrumentationRegistry.getContext()
        var intent = context.packageManager.getLaunchIntentForPackage(packageName as String?)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)

        if (packageName == "com.salesforce.react_native") {
            device.wait(Until.hasObject(By.res("android:id/alertTitle")), 5000)
            device.pressBack()
            device.wait(Until.hasObject(By.pkg("com.android.settings").depth(0)), 5000)

            var allowSwitch = device.findObject(UiSelector().packageName("com.android.settings").resourceId("android:id/switch_widget"))
            allowSwitch.click()
            Log.i("swittttch", "is checkable: " + allowSwitch.isCheckable)
            device.pressBack()
            context.startActivity(intent)
        }

        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), 5000)
    }

    @Test
    fun login() {
        var overflowMenu = device.findObject(UiSelector().className("android.widget.ImageButton").description("More options"))
        overflowMenu.click()
        var changeServer = device.findObject(UiSelector().resourceId("android:id/title").text("Change Server"))
        changeServer.click()
        var addConnection = device.findObject(UiSelector().className("android.widget.Button").text("Add Connection"))
        addConnection.click()
        var connectionName = device.findObject(UiSelector().resourceId(packageName + ":id/sf__picker_custom_label"))
        connectionName.click()
        connectionName.setText("Mobile2")
        var connectionUrl = device.findObject(UiSelector().resourceId(packageName + ":id/sf__picker_custom_url"))
        connectionUrl.click()
        connectionUrl.setText("https://mobile2.t.salesforce.com")
        var addConnectionApply = device.findObject(UiSelector().resourceId(packageName + ":id/sf__apply_button"))
        addConnectionApply.click()
        var connectionApply = device.findObject(UiSelector().resourceId(packageName + ":id/sf__apply_button"))
        connectionApply.click()

        device.wait(Until.hasObject(By.res("username")), 5000)
        var username = device.findObject(UiSelector().resourceId("username"))
        username.click()
        username.setText("bpage@salesforce.com")

        var password = device.findObject(UiSelector().resourceId("password"))
        password.click()
        password.setText("test1234")

        var login = device.findObject(UiSelector().resourceId("Login"))
        login.click()

        device.wait(Until.hasObject(By.res("oaapprove")), 10000)
        var allowButton = device.findObject(UiSelector().resourceId("oaapprove"))
        allowButton.click()
    }
}