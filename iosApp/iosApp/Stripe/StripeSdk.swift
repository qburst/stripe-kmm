//  Stripesdk.swift
//  iosApp
//
//  Created by abdul basith on 05/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

import Stripe
import StripePaymentSheet
import UIKit
import ComposeApp


public class StripeSdk : SharedStripeRepository {
    
    private var paymentSheet: PaymentSheet?
    private var paymentSheetClientSecret: String?
    
    
    public func initialise(publishableKey: String, clientSecret: String) throws {
        
        let publishableKey: String? = publishableKey
        let clientSecret:String? = clientSecret
        
        StripeAPI.defaultPublishableKey = publishableKey
        configurePaymentSheet(clientSecret: clientSecret!)
        
        DispatchQueue.main.async {
            self.presentPayment { result in
                switch result {
                case .success:
                    print("Payment succeeded")
                case .failure(let error):
                    print("Payment failed: \(error.localizedDescription)")
                }
            }
        }
    }
    
    public func configurePaymentSheet(clientSecret: String) {
        var configuration = PaymentSheet.Configuration()
        configuration.merchantDisplayName = "TECHZIA"
        paymentSheetClientSecret = clientSecret
        paymentSheet = PaymentSheet(paymentIntentClientSecret: clientSecret, configuration: configuration)
    }
    
    
    private func presentPayment(completion: @escaping (Result<Void, Error>) -> Void) {

        guard let paymentSheet = paymentSheet else {
            let error = NSError(domain: "StripeSDKNewError", code: 0, userInfo: [NSLocalizedDescriptionKey: "PaymentSheet not configured."])
            completion(.failure(error))
            return
        }
        
        paymentSheet.present(from: UIApplication.shared.windows.first!.rootViewController!) { paymentResult in
            switch paymentResult {
            case .completed:
                completion(.success(()))
            case .canceled:
                let error = NSError(domain: "StripeSDKNewError", code: 1, userInfo: [NSLocalizedDescriptionKey: "Payment canceled."])
                completion(.failure(error))
            case .failed(let error):
                completion(.failure(error))
            }
        }
    }
}

