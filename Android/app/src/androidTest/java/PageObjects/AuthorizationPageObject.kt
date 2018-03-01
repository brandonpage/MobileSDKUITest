package PageObjects

import android.graphics.Point
import android.support.test.uiautomator.*
import android.util.Log

/**
 * Created by bpage on 2/23/18.
 */

class AuthorizationPageObject : BasePageObject() {

    init {
        if (isOldDevice) {
            Log.i("uia", "sleeping a while to let auth page load.")
            Thread.sleep(30000)
            //app.reloadWebview()
            //assert(device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__oauth_webview")).waitForExists(30000))
        }
    }

    fun tapAllow() {
        var allowButton = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.Button").index(0))
        }
        else {
            device.findObject(UiSelector().resourceId("oaapprove"))
        }

        /*var username = device.findObject(UiSelector().className("android.widget.EditText").index(2))
        if (username.exists()) {
            Log.i("uia", "still on login page?")
        }*/

        //var webView = device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__oauth_webview"))
        //var webView = device.findObject(UiSelector().className("android.webkit.WebView"))
        //webView.swipeUp(50)
        //webView.swipeDown(50)

        Log.i("uia", "Waiting for allow button to be present.")
        assert(allowButton.waitForExists(timeout * 3))
        //Thread.sleep(30000)


        var webview2 = device.wait(Until.findObject(By.clazz("android.webkit.WebView")), timeout)
        Log.i("uia", "swiping webview.  webview is scrollable? " + webview2.isScrollable)
        Log.i("uia", "try swipe down")
        //webview2.swipe(Direction.DOWN, 0.5f)

        //Thread.sleep(30000)
        //Log.i("uia", "try drag")
        // WINNER !!!!!!!
        webview2.drag(Point(100, 100))
        Thread.sleep(10000)
        /*Thread.sleep(30000)
        Log.i("uia", "try fling up")
        webview2.fling(Direction.UP)

        Thread.sleep(30000)
        Log.i("uia", "try fling down")
        webview2.fling(Direction.DOWN)

        Thread.sleep(30000)
        Log.i("uia", "try scroll down")
        webview2.scroll(Direction.DOWN, 0.5f)


        Thread.sleep(30000)
        Log.i("uia", "try scroll up")
        webview2.scroll(Direction.UP, 0.5f)*/

        allowButton.click()
    }

    fun tapDeny() {

    }
}