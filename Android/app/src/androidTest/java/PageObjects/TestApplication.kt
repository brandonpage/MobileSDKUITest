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
    var packageName = InstrumentationRegistry.getArguments().get("packageName") as String
    var name = packageName.split(".").last() + "_androidApp"
    var type = when (packageName.split(".").last()) {
            "native_java" -> AppType.NATIVE_JAVA
            "native_kotlin" -> AppType.NATIVE_KOTLIN
            "hybrid_local" -> AppType.HYBRID_LOCAL
            "hybrid_remote" -> AppType.HYBRID_REMOTE
            "react_native" -> AppType.REACT_NATIVE
            else -> {
                throw IllegalArgumentException("Unknown App Type")
            }
    }

    fun launch() {
        // Uncomment this to run in Android Studio
        //var packageName = "com.salesforce.react_native"
        //packageName = "com.salesforce.samples.smartsyncexplorer"

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
}