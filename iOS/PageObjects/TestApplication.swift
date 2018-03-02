//
//  TestApplication.swift
//  MobileSDKUITest
//
//  Created by Brandon Page on 2/21/18.
//

import Foundation
import XCTest

class TestApplication: XCUIApplication {
    var bundle_string = ""
    
    override init() {
        // Get the Test App Bundle from command line arg
        bundle_string = ProcessInfo.processInfo.environment["TEST_APP_BUNDLE"]!
        super.init(bundleIdentifier: bundle_string)
    }
    
    override func launch() {
        super.launch()
    }
}
