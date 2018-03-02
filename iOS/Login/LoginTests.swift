//
//  LoginTest.swift
//  MobileSDKUITest
//
//  Created by Brandon Page on 2/2/18.
//

import XCTest

class LoginTests: XCTestCase {
    
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
    
    func testLogin() {
        let app = TestApplication()
        let loginPage = LoginPageObject(testApp: app)
        let authPage = AuthorizationPageObject(testApp: app)
        
        app.launch()
        loginPage.setUsername(name: "circleci@mobilesdk.com")
        loginPage.setPassword(password: "test1234")
        loginPage.tapLogin()
        authPage.tapAllow()
        
        app.screenshot()
        // Assert App loads
        switch app.bundle_string {
        case "com.salesforce.native-iosApp", "com.salesforce.native-swift-iosApp":
            XCTAssert(app.navigationBars["Mobile SDK Sample App"].waitForExistence(timeout: 30), "App did not load.")
        case "com.salesforce.hybrid_local", "com.salesforce.hybrid_remote":
            //TODO: implment
            sleep(30)
            print(app.debugDescription)
            
            XCTAssert(true, "App did not load.")
        case "com.salesforce.react-native-iosApp":
            let titleElement = app.children(matching: .window).element(boundBy: 0).children(matching: .other).element.children(matching: .other)["Automated Process Brandon Page circleci Integration User Security User Chatter Expert Mobile SDK Sample App"].children(matching: .other)["Automated Process Brandon Page circleci Integration User Security User Chatter Expert Mobile SDK Sample App"].children(matching: .other)["Automated Process Brandon Page circleci Integration User Security User Chatter Expert Mobile SDK Sample App"].children(matching: .other)["Automated Process Brandon Page circleci Integration User Security User Chatter Expert Mobile SDK Sample App"].children(matching: .other)["Automated Process Brandon Page circleci Integration User Security User Chatter Expert Mobile SDK Sample App"].children(matching: .other)["Mobile SDK Sample App"].children(matching: .other)["Mobile SDK Sample App"].children(matching: .other)["Mobile SDK Sample App"]
            XCTAssert(titleElement.waitForExistence(timeout: 30), "App did not load.")
        default:
            XCTAssert(false, "App type no recognized.")
        }
    }
    
    // let circleciStaticText = app/*@START_MENU_TOKEN@*/.otherElements.matching(identifier: "Automated Process Brandon Page circleci Integration User Security User Chatter Expert").staticTexts["circleci"]/*[[".otherElements.matching(identifier: \"Automated Process Brandon Page circleci Integration User Security User Chatter Expert Mobile SDK Sample App\")",".scrollViews.otherElements[\"Automated Process Brandon Page circleci Integration User Security User Chatter Expert\"]",".otherElements[\"circleci\"].staticTexts[\"circleci\"]",".staticTexts[\"circleci\"]",".otherElements.matching(identifier: \"Automated Process Brandon Page circleci Integration User Security User Chatter Expert\")"],[[[-1,4,2],[-1,1,2],[-1,0,1]],[[-1,4,2],[-1,1,2]],[[-1,3],[-1,2]]],[0,0]]@END_MENU_TOKEN@*/
    ///MobileElement el1 = (MobileElement) driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Mobile SDK Sample App\"])[5]");
    //MobileElement el2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"circleci\"]");
}
