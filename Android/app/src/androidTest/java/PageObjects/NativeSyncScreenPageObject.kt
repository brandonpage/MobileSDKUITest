package PageObjects

import android.support.test.uiautomator.UiSelector
import org.junit.Assert

/**
 * Created by bpage on 2/24/18.
 */
class NativeSyncScreenPageObject : BasePageObject() {

    fun logout() {
        var logoutButton = device.findObject(UiSelector().resourceId(app.packageName + ":id/logout_button"))
        logoutButton.waitForExists(timeout)
        logoutButton.click()
    }

    fun fetchContacts() {
        var fetchContactsButton = device.findObject(UiSelector().resourceId(app.packageName + ":id/fetch_contacts"))
        fetchContactsButton.waitForExists(timeout)
        fetchContactsButton.click()
    }

    fun fetch_accounts() {
        var fetchAccountsButton = device.findObject(UiSelector().resourceId(app.packageName + ":id/fetch_accounts"))
        fetchAccountsButton.waitForExists(timeout)
        fetchAccountsButton.click()
    }

    fun clear() {
        var clearButton = device.findObject(UiSelector().resourceId(app.packageName + ":id/clear"))
        clearButton.waitForExists(timeout)
        clearButton.click()
    }

    fun assertAppTitle() {
        Thread.sleep(timeout * 10)
        var titleBar = if (isOldDevice) {
            device.findObject(UiSelector().className("android.widget.TextView").index(0))
        }
        else {
            device.findObject(UiSelector().resourceId("android:id/action_bar_title"))
        }
        titleBar.waitForExists(timeout)
        Assert.assertEquals("App did not successfully login.", app.name, titleBar.text)
        //var contact = device.findObject(UiSelector().resourceId(packageName + ":id/obj_name").index(0))
        //Assert.assertEquals("Contacts did not load.", "EXPECTED CONTACT NAME", contact.text)??
    }
}