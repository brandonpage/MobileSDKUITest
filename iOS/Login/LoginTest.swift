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
        let app = TestApplication()
        let loginPage = LoginPageObject(testApp: app)
        let authPage = AuthorizationPageObject(testApp: app)
        
        app.launch()
        loginPage.setUsername(name: "bpage@mobilesdk.com")
        loginPage.setPassword(password: "test1234")
        loginPage.tapLogin()
        authPage.tapAllow()
        
        // Assert App loads
        switch app.bundle_string {
        case "com.salesforce.native-iosApp", "com.salesforce.native-swift-iosApp":
            XCTAssert(app.navigationBars["Mobile SDK Sample App"].waitForExistence(timeout: 30), "App did not load.")
            //XCTAssert(app.tables.staticTexts["CONTACT NAME HERE"].waitForExistence(timeout: 10), "Contact did not load.")
        case "com.salesforce.hybrid_local", "com.salesforce.hybrid_remote":
            //TODO: implment
            XCTAssert(true, "App did not load.")
        case "com.salesforce.react-native-iosApp":
            //TODO: implment
            XCTAssert(true, "App did not load.")
        default:
            XCTAssert(false, "App type no recognized.")
        }
        
        
    }
}
