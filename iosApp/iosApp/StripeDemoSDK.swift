//
//  StripeDemoSDK.swift
//  iosApp
//
//  Created by abdul basith on 30/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//
//
import Foundation
import Stripe
import StripePaymentSheet
import UIKit
//
//
@objc public class StripeDemoSDK: NSObject, ObservableObject {
        // MARK: - Properties
        
        @Published @objc public var isPaymentSheetPresented: Bool = false
        public var paymentSheet: PaymentSheet?
        @objc public var paymentSheetClientSecret: String?

        // MARK: - Initialization

        @objc public override init() {
            super.init()
            StripeAPI.defaultPublishableKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W"
            // Static client secret (for demonstration purposes)
            let clientSecret = "pi_1PibQbKJ38Q1wp9dRCmmV2EO_secret_foEQUROXkGXpxlTti07bcUaBy"
            configurePaymentSheet(clientSecret: clientSecret)
            print("Hello from StripeSDK")
        }

        // MARK: - Public Methods

        @objc public func showPaymentSheet() {
            isPaymentSheetPresented = true
        }

        @objc public func hidePaymentSheet() {
            isPaymentSheetPresented = false
        }

        @objc public func configurePaymentSheet(clientSecret: String) {
            var configuration = PaymentSheet.Configuration()
            configuration.merchantDisplayName = "TECHZIA"
            paymentSheetClientSecret = clientSecret
            paymentSheet = PaymentSheet(paymentIntentClientSecret: clientSecret, configuration: configuration)
        }

        @objc public func presentPaymentSheet(from viewController: UIViewController, completion: @escaping (Bool, NSError?) -> Void) {
            guard let paymentSheet = paymentSheet else {
                let error = NSError(domain: "StripeSDKNewError", code: 0, userInfo: [NSLocalizedDescriptionKey: "PaymentSheet not configured."])
                completion(false, error)
                return
            }

            DispatchQueue.main.async {
                paymentSheet.present(from: viewController) { paymentResult in
                    switch paymentResult {
                    case .completed:
                        completion(true, nil)
                    case .canceled:
                        let error = NSError(domain: "StripeSDKNewError", code: 1, userInfo: [NSLocalizedDescriptionKey: "Payment canceled."])
                        completion(false, error)
                    case .failed(let error):
                        completion(false, error as NSError)
                    }
                }
            }
        }
    }





//public class StripeDemoSDK: ObservableObject {
//    // MARK: - Properties
//    
//    @Published var isPaymentSheetPresented: Bool = false
//    public var paymentSheet: PaymentSheet?
//    public var paymentSheetClientSecret: String?
//    
//
//    // MARK: - Initialization
//    
//    // Initialize the StripeDemoSDK with a static client secret
//    init() {
//        StripeAPI.defaultPublishableKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W"
//        // Static client secret (for demonstration purposes)
//        let clientSecret = "pi_1PibQbKJ38Q1wp9dRCmmV2EO_secret_foEQUROXkGXpxlTti07bcUaBy"
//        configurePaymentSheet(clientSecret: clientSecret)
//    }
//    
//    
//    func showPaymentSheet() {
//            isPaymentSheetPresented = true
//    }
//    
//    func hidePaymentSheet() {
//            isPaymentSheetPresented = false
//        }
//    
//
//    // MARK: - Public Methods
//    
//    func configurePaymentSheet(clientSecret: String) {
//        var configuration = PaymentSheet.Configuration()
//        configuration.merchantDisplayName = "TECHZIA"
//        paymentSheetClientSecret = clientSecret
//        paymentSheet = PaymentSheet(paymentIntentClientSecret: clientSecret, configuration: configuration)
//    }
//
//    func presentPaymentSheet(from viewController: UIViewController, completion: @escaping (Result<Void, Error>) -> Void) {
//        guard let paymentSheet = paymentSheet else {
//            completion(.failure(NSError(domain: "StripeDemoSDKError", code: 0, userInfo: [NSLocalizedDescriptionKey: "PaymentSheet not configured."])))
//            return
//        }
//
//        DispatchQueue.main.async {
//            paymentSheet.present(from: viewController) { paymentResult in
//                switch paymentResult {
//                case .completed:
//                    completion(.success(()))
//                case .canceled:
//                    completion(.failure(NSError(domain: "StripeDemoSDKError", code: 1, userInfo: [NSLocalizedDescriptionKey: "Payment canceled."])))
//                case .failed(let error):
//                    completion(.failure(error))
//                }
//            }
//        }
//    }
//}
