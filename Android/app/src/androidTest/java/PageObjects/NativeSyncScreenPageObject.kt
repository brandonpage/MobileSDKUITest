package PageObjects

import android.support.test.uiautomator.UiSelector
import org.junit.Assert

/**
 * Created by bpage on 2/24/18.
 */
class NativeSyncScreenPageObject(app: TestApplication) : BasePageObject() {
    val app = app

    fun assertAppTitle() {
        var titleBar = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.TextView").index(0))
        }
        else {
            device.findObject(UiSelector().resourceId("android:id/action_bar_title"))
        }
        titleBar.waitForExists(timeout * 2)
        Assert.assertEquals("App did not successfully testLogin.", app.name, titleBar.text)
    }
}