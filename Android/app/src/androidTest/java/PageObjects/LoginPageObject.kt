package PageObjects

import android.support.test.uiautomator.UiSelector
import android.util.Log
import android.view.accessibility.AccessibilityWindowInfo
import android.support.test.InstrumentationRegistry



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
        var username = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.EditText").index(2))
        }
        else {
            device.findObject(UiSelector().resourceId("username"))
        }

        Log.i("uia", "Waiting for username filed to be present.")
        assert(username.waitForExists(timeout * 2))
        if (isOldDevice) {
            username.legacySetText(name)
            Thread.sleep(30000)
        }
        else {
            username.text = name
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
            passwordField.legacySetText(password)
            Thread.sleep(30000)
        }
        else {
            passwordField.text = password
        }
    }

    fun tapLogin() {
        var loginButton = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.Button").index(0))
        }
        else {
            device.findObject(UiSelector().resourceId("Login"))
        }

        if (isOldDevice) {
            for (window in InstrumentationRegistry.getInstrumentation().uiAutomation.windows) {
                if (window.type == AccessibilityWindowInfo.TYPE_INPUT_METHOD) {
                    Log.i("uia", "keyboard present. tapping back button.")
                    device.pressBack()
                    Thread.sleep(15000)
                }
                else {
                    Log.i("uia", "keyboard not present.")
                }
            }
        }

        Log.i("uia", "waiting for login button.")
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

    private fun applyConnection () {
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

    fun openServerPage () {
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

    fun clearCookies () {
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