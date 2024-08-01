//
//  DemoG.swift
//  iosApp
//
//  Created by abdul basith on 31/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI


@objc public class DemoG1: NSObject, ObservableObject  {
   
    @Published @objc public var isPaymentSheetPresented: Bool = true
    @objc public static let shared = DemoG1()
    
    
    @objc public static func updatePaymentSheetPresented(isPresented: Bool) {
        DispatchQueue.main.async {
            DemoG1.shared.isPaymentSheetPresented = isPresented
        }
    }
   
}


@objc public class DemoG1Updater: NSObject {
    @objc public static func updatePaymentSheetPresented(isPresented: Bool) {
        DispatchQueue.main.async {
            DemoG1.shared.isPaymentSheetPresented = isPresented
        }
    }
}
