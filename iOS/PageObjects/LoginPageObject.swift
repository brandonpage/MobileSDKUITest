//
//  LoginPageObject.swift
//  MobileSDKUITest
//
//  Created by Brandon Page on 2/21/18.
//

import Foundation
import XCTest

class LoginPageObject: XCUIScreen {
    var app:XCUIApplication
    
    init(testApp: XCUIApplication) {
        app = testApp
    }
    
    func setUsername(name: String) -> Void {
        let nameField = app.descendants(matching: .textField).element
        _ = nameField.waitForExistence(timeout: 30)
        nameField.press(forDuration: 0.5)
        nameField.typeText(name)
    }
    
    func setPassword(password: String) -> Void {
        let passwordField = app.descendants(matching: .secureTextField).element
        _ = passwordField.waitForExistence(timeout: 5)
        passwordField.press(forDuration: 0.5)
        sleep(1)
        passwordField.typeText(password)
    }
    
    func tapLogin() -> Void {
        let webViewPredicate = NSPredicate(format: "label BEGINSWITH[cd] 'Login'")
        let webElements = app.otherElements.webViews.otherElements.matching(webViewPredicate).element
        webElements.otherElements.children(matching: .button).element(boundBy: 0).press(forDuration: 0.5)
    }
    
    // back button
    // XCUIApplication().navigationBars["Log In"].children(matching: .button).element(boundBy: 0).tap()
}
