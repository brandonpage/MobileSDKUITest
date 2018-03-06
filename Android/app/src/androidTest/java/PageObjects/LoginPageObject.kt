package PageObjects

import android.support.test.uiautomator.UiSelector
import android.util.Log

/**
 * Created by bpage on 2/21/18.
 */
class LoginPageObject(app: TestApplication) : BasePageObject() {
    val app = app

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

        // Get keyboard out of the way
        if (isOldDevice) {
            Log.i("uia", "Hitting back button to uncover password field")
            device.pressBack()
            Thread.sleep(timeout)
            passwordField.legacySetText(password)
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
            Log.i("uia", "Login button: try hitting back")
            device.pressBack()
            Thread.sleep(timeout)
        }
        assert(loginButton.waitForExists(timeout * 2))
        loginButton.click()
    }
}