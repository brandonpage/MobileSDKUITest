//
//  AuthorizationPageObject.swift
//  MobileSDKUITest
//
//  Created by Brandon Page on 2/21/18.
//

import Foundation
import XCTest

class AuthorizationPageObject: XCUIScreen {
    var app:XCUIApplication
    
    init(testApp: XCUIApplication) {
        app = testApp
    }
    
    func tapAllow() {
        pressButton(lable: "Allow")
    }
    
    func tapDeny() {
        pressButton(lable: "Deny")
    }
    
    private func pressButton(lable: String) {
        let button = app.buttons.element(matching: NSPredicate(format: "label CONTAINS '" + lable + "'"))
        _ = assert(button.waitForExistence(timeout: 60))
        sleep(5)
        button.press(forDuration: 0.5)
    }
}
