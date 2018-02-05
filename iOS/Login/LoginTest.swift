//
//  LoginTest.swift
//  MobileSDKUITest
//
//  Created by Brandon Page on 2/2/18.
//

import XCTest

class LoginTest: XCTestCase {
    
    override func setUp() {
        super.setUp()
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testExample() {
        // This setup should be moved to a XCUIApplication class
        // Restting the app also needs to be implemented
        let bundle_string = ProcessInfo.processInfo.environment["TEST_APP_BUNDLE"]!
        let app = XCUIApplication(bundleIdentifier: bundle_string)
        app.launch()
        
        // Username
        let userName = app.descendants(matching: .textField).element
        userName.press(forDuration: 0.5)
        userName.typeText("bpage3@salesforce.com")
        
        // Password
        let passwordField = app.descendants(matching: .secureTextField).element
        passwordField.press(forDuration: 0.5)
        sleep(1)
        passwordField.typeText("test123456")
        
        // Tap Login
        let webViewPredicate = NSPredicate(format: "label BEGINSWITH[cd] 'Login'")
        let webElements = app.otherElements.webViews.otherElements.matching(webViewPredicate).element
        webElements.otherElements.children(matching: .button).element(boundBy: 0).press(forDuration: 0.5)
        
        // Tap Allow
        sleep(7)
        app.buttons.element(matching: NSPredicate(format: "label CONTAINS 'Allow'")).press(forDuration: 0.5)
    }
    
}
