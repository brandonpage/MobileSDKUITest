package PageObjects

import android.support.test.uiautomator.UiSelector
import android.util.Log
import org.junit.Assert

/**
 * Created by bpage on 3/6/18.
 */

class ReactNativeAppPageObject : BasePageObject() {

    init {
        timeout *= 3
    }

    fun assertAppLoads() {
        //if (isArm) {
        //    Thread.sleep(timeout * 15)
        //}
        //else {
            Thread.sleep(timeout * 5)
        //}

        // pacakge com.android.settings

        // re com.android.settings:id/title text Apps (app data & media content)
        


        var alertWindow = device.findObject(UiSelector().resourceId("android:id/alertTitle"))
        if (alertWindow.exists()) {
            Log.i("uia", "React Native requesting overlay permission.")
            // Tap Continue Button
            device.findObject(UiSelector().resourceId("android:id/button1")).click()
            Thread.sleep(timeout)
        }

        val title = device.findObject(UiSelector().className("android.widget.TextView").index(0))
        title.waitForExists(timeout * 5)
        Assert.assertEquals("App did not successfully login.", "Mobile SDK Sample App", title.text)
    }
}