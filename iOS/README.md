#  Mobile SDK UI Tests for iOS

These tests are designed to validate the functionality of apps created using the MobileSDK CLI tool [forceios](https://www.npmjs.com/package/forceios).  Unless otherwise specified these test should work with any generic app whether the app was created with native Objective-C/Swift, Cordova, or React Native code.

Basic usage:
1.  Build the app(s) to be tested on a specific device.
2.  Run the tests here agianst the same device and supply the test app's bundle id as a parameter to the arg `TEST_APP_BUNDLE`.

ex:  `xcodebuild -scheme MobileSDKUITest -sdk iphonesimulator -destination 'platform=iOS Simulator,name=iPhone 8,OS=11.2' TEST_APP_BUNDLE='com.mycompany.native-swiftiosApp' test`
