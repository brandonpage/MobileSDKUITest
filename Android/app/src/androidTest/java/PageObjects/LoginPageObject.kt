package PageObjects

import android.graphics.Rect
import android.support.test.uiautomator.UiSelector
import android.util.Log
import android.view.accessibility.AccessibilityWindowInfo
import android.support.test.InstrumentationRegistry
import android.opengl.ETC1.getHeight
import android.view.inputmethod.InputMethodManager


/**
 * Created by bpage on 2/21/18.
 */
class LoginPageObject : BasePageObject() {

    init {
        if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.EditText").index(2)).waitForExists(120000)
        }
    }

    fun setUsername(name: String) {
        var usernameField = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.EditText").index(2))
        }
        else {
            device.findObject(UiSelector().resourceId("username"))
        }

        Log.i("uia", "Waiting for username filed to be present.")
        assert(usernameField.waitForExists(timeout * 2))
        if (isOldDevice) {
            usernameField.legacySetText(name)

            while (usernameField.contentDescription != name) {
                Log.i("uia", "username not set.  sleeping...")
                Thread.sleep(5000)
            }
        }
        else {
            usernameField.text = name
        }
    }

    fun setPassword(password: String) {
        var passwordField = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.EditText").index(4))
        }
        else {
            device.findObject(UiSelector().resourceId("password"))
        }
        Log.i("uia", "Waiting for password filed to be present.")
        assert(passwordField.waitForExists(timeout))
        if (isOldDevice) {
            Log.i("uia", "Old device.  Looking for keyboard.")
            for (window in InstrumentationRegistry.getInstrumentation().uiAutomation.windows) {
                Log.i("uia", "window: " + window.type)
                if (window.type == AccessibilityWindowInfo.TYPE_INPUT_METHOD) {
                    Log.i("uia", "window**** keyboard present. tapping back button.")
                    //device.pressBack()
                    //Thread.sleep(15000)
                } else {
                    Log.i("uia", "window**** keyboard not present.")
                }
            }

            var webview = device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__oauth_webview"))
            if (webview.exists()) {
                var rect = webview.bounds
                Log.i("uia", "Rect: "+ rect.toString())
                if (rect.bottom > 1500) {
                    Log.i("uia", "webview****  keyboard present. tapping back button.")
                    //device.pressBack()
                    //Thread.sleep(15000)
                } else {
                    Log.i("uia", "webview****  keyboard not present.")
                }
            }

            // Get keyboard out of the way
            device.pressBack()
            Log.i("uia", "password field, hitting back button")
            Thread.sleep(25000)
            //passwordField.click()
            Thread.sleep(15000)
            passwordField.legacySetText(password)

            /*while (passwordField.contentDescription != password) {
                Log.i("uia", "password field: " + passwordField.contentDescription)
                Thread.sleep(7000)
            }*/

            //Thread.sleep(35000)
            //Log.i("uia", "password field, hitting back button AGAIN")
            //device.pressBack()
        }
        else {
            passwordField.text = password
        }
    }


    // android:id/button1

    // webview com.salesforce.samples.smartsyncexplorer:id/sf__oauth_webview
    fun tapLogin() {
        var loginButton = if (isOldDevice) {
            //use content description?
            device.findObject(UiSelector().className("android.widget.Button").index(0))
        }
        else {
            device.findObject(UiSelector().resourceId("LoginTests"))
        }

        Log.i("uia", "basicLogin button: try hitting back")
        device.pressBack()
        Thread.sleep(35000)


        /*var webView = device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__oauth_webview"))
        device.swipe(device.displayWidth/2, device.displayHeight/2, device.displayWidth - 20, device.displayHeight - 20, 1)
        Log.i("uia", "waiting for basicLogin button.")*/
        assert(loginButton.waitForExists(timeout * 2))
        loginButton.click()
    }

    fun setCustomDomain(domain: String) {
        var domainLink = if (isOldDevice) {
            device.findObject(UiSelector().className("android.view.View").index(8))
            // content description?
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("Use Custom Domain"))
        }
        domainLink.waitForExists(timeout)
        domainLink.click()

        var domainField = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.EditText").index(1))
        }
        else {
            //TODO: get resource id
            device.findObject(UiSelector().resourceId(""))
        }
        domainField.waitForExists(timeout)
        domainField.click()
        domainField.setText(domain)

        var continueButton = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.Button").index(5))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("continue"))
        }
        continueButton.click()
    }

    fun changeServer(serverName: String, serverURL: String) {
        openServerPage()
        var addConnection = if (isOldDevice) {
            device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__show_custom_url_edit"))
        }
        else {
            device.findObject(UiSelector().className("android.widget.Button").text("Add Connection"))
        }
        addConnection.waitForExists(timeout)
        addConnection.click()

        var connectionName = if (isOldDevice) {
            device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__picker_custom_label"))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("name"))
        }
        connectionName.waitForExists(timeout)
        connectionName.click()
        connectionName.setText(serverName)

        var connectionUrl = if (isOldDevice) {
            device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__picker_custom_url"))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("url"))
        }
        connectionUrl.click()
        connectionUrl.setText(serverURL)


        var addConnectionApply = if (isOldDevice) {
            device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__apply_button"))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("apply"))
        }
        addConnectionApply.click()
        applyConnection()
    }

    fun resetServers() {
        clickOverflowMenu()
        var resetButton = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.LinearLayout").index(0))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("reset"))
        }
        resetButton.waitForExists(timeout)
        resetButton.click()
    }

    fun choseServer(serverName: String) {
        openServerPage()
        var serverButton = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.RadioButton").text(serverName))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId(serverName))
        }
        serverButton.waitForExists(timeout)
        serverButton.click()
        applyConnection()
    }

    private fun applyConnection() {
        var connectionApply = if (isOldDevice) {
            device.findObject(UiSelector().resourceId(app.packageName + ":id/sf__apply_button"))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("add connection"))
        }
        connectionApply.waitForExists(timeout)
        connectionApply.click()
    }

    fun openServerPage() {
        clickOverflowMenu()
        var changeServer = if (isOldDevice) {
            device.findObject(UiSelector().resourceId("android:id/title").text("Change Server"))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("change server"))
        }
        changeServer.waitForExists(timeout)
        changeServer.click()
    }

    fun clearCookies() {
        clickOverflowMenu()
        var clearCookiesButton = if (isOldDevice) {
            device.findObject(UiSelector().resourceId("android:id/title").text("Clear Cookies"))
        }
        else {
            device.findObject(UiSelector().resourceId("Clear Cookies"))
        }
        clearCookiesButton.waitForExists(timeout)
        clearCookiesButton.click()
    }

    fun reloadPage() {
        clickOverflowMenu()
        var reloadButton = device.findObject(UiSelector().resourceId("android:id/title").text("Reload"))
        reloadButton.waitForExists(timeout)
        reloadButton.clickAndWaitForNewWindow()
    }

    private fun clickOverflowMenu() {
        var overflowMenu = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.ImageButton").description("More options"))
        }
        else {
            //TODO: check this
            device.findObject(UiSelector().resourceId("More options"))
        }
        overflowMenu.waitForExists(timeout)
        overflowMenu.click()
    }
}