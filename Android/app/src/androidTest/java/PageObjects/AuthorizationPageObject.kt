package PageObjects

import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector

/**
 * Created by bpage on 2/23/18.
 */

class AuthorizationPageObject : BasePageObject() {

    init {
        if (isOldDevice) {
            //app.reloadWebview()
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
        Thread.sleep(60000)
        allowButton.click()
    }

    fun tapDeny() {

    }
}