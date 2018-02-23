package PageObjects

import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector

/**
 * Created by bpage on 2/23/18.
 */

class AuthorizationPageObject {
    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    var isOldDevice: Boolean = (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)
    var timeout:Long = if (isOldDevice) 30000 else 5000
    var app = TestApplication()

    init {
        if (isOldDevice) {
            app.reloadWebview()
        }
    }

    fun tapAllow() {
        var allowButton = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.Button").index(0))
        }
        else {
            device.findObject(UiSelector().resourceId("oaapprove"))
        }
        allowButton.waitForExists(timeout)
        allowButton.click()
    }

    fun tapDeny() {

    }
}