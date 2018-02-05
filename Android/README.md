#  Mobile SDK UI Tests for Android

These tests are designed to validate the functionality of apps created using the MobileSDK CLI tool [forcedroid](https://www.npmjs.com/package/forcedroid).  Unless otherwise specified these test should work with any generic app whether the app was created with native Java/Kptlin, Cordova, or React Native code.

Basic usage:
1.  Build the app(s) to be tested on a specific device.
2.  Run the tests here agianst the same device and supply the test app's package id as a parameter with the `-e` flag and the key `packageName`.

ex:  `adb shell am instrument -w -r -e debug false -e packageName com.mycompany com.salesforce.mobilesdk.mobilesdkuitest.test/android.support.test.runner.AndroidJUnitRunner`