package PageObjects

import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice

/**
 * Created by bpage on 2/24/18.
 */
open class BasePageObject {
    var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    var isOldDevice: Boolean = (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)
    var timeout:Long = if (isOldDevice) 30000 else 5000
    var app = TestApplication()
}