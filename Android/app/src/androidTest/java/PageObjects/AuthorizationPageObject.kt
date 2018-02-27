package PageObjects

import android.support.test.uiautomator.UiSelector
import android.util.Log

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
        Log.i("uia", "Waiting for username filed to be present.")
        assert(allowButton.waitForExists(timeout * 3))
        Thread.sleep(30000)
        allowButton.click()
    }

    fun tapDeny() {

    }
}