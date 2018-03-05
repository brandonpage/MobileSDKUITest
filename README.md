Android: [![CircleCI](https://circleci.com/gh/brandonpage/MobileSDKUITest/tree/master.svg?style=svg)](https://circleci.com/gh/brandonpage/MobileSDKUITest/tree/master)
# Salesforce MobileSDK UI Tests

This repo contains designed to validate the functionality of apps created using the MobileSDK CLI tools [forcedroid](https://www.npmjs.com/package/forcedroid), [forceios](https://www.npmjs.com/package/forceios), and [forcehybrid](https://www.npmjs.com/package/forcehybrid).  Android and iOS test frameworks exist in their own directories and use separate technologies (UIAutomator and XCUITest, respectively).  However, they share a common fastlane file for end-to-end execution:
1.  `install.sh`
2.  From the .circleci folder: `fastlane <os> type:<AppType>`
       
       ex: `fastlane android type:native` or `fastlane ios type:react_native`