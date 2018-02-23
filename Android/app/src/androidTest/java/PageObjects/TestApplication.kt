package PageObjects

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import android.content.Intent
import android.os.Build
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiSelector
import android.support.test.uiautomator.Until
import android.util.Log

/**
 * Created by bpage on 2/21/18.
 */

class TestApplication {
    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    var packageName = InstrumentationRegistry.getArguments().get("packageName") as String
    var isOldDevice: Boolean = (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)
    var timeout:Long = if (isOldDevice) 30000 else 5000

    fun launch() {
        // Uncomment this to run in Android Studio
        //var packageName = "com.salesforce.react_native"
        //packageName = "com.salesforce.samples.smartsyncexplorer"

        device.pressHome()
        var context = InstrumentationRegistry.getContext()
        var intent = context.packageManager.getLaunchIntentForPackage(packageName as String?)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)

        // WIP
        /*if (packageName == "com.salesforce.react_native") {
            device.wait(Until.hasObject(By.res("android:id/alertTitle")), 5000)
            device.pressBack()
            device.wait(Until.hasObject(By.pkg("com.android.settings").depth(0)), 5000)

            var allowSwitch = device.findObject(UiSelector().packageName("com.android.settings").resourceId("android:id/switch_widget"))
            allowSwitch.click()
            Log.i("swittttch", "is checkable: " + allowSwitch.isCheckable)
            device.pressBack()
            context.startActivity(intent)
        }*/
    }

    internal fun reloadWebview() {
        // Refresh needed to load element tree on API 22
        var overflowMenu = device.findObject(UiSelector().className("android.widget.ImageButton").description("More options"))
        overflowMenu.waitForExists(timeout)
        overflowMenu.click()
        var reloadButton = device.findObject(UiSelector().resourceId("android:id/title").text("Reload"))
        reloadButton.waitForExists(timeout)
        reloadButton.click()
        Thread.sleep(timeout)
    }
}