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
 
    var cardFieldView: CardFieldView? = nil
    var cardFormView: CardFormView? = nil
    private var paymentSheet: PaymentSheet?
    private var paymentSheetClientSecret: String?
    
    var urlScheme: String? = nil
    var merchantIdentifier: String? = nil
        
    
    public func initialise(params: [String : Any]) throws {
        
        let publishableKey = params["publishableKey"] as! String
        let appInfo = params["appInfo"] as! NSDictionary
        let stripeAccountId = params["stripeAccountId"] as? String
        let params3ds = params["threeDSecureParams"] as? NSDictionary
        let urlScheme = params["urlScheme"] as? String
        let merchantIdentifier = params["merchantIdentifier"] as? String
        
        if let params3ds = params3ds {
            configure3dSecure(params3ds)
        }
        
        self.urlScheme = urlScheme
        
        STPAPIClient.shared.publishableKey = publishableKey
        StripeAPI.defaultPublishableKey = publishableKey
        STPAPIClient.shared.stripeAccount = stripeAccountId
        
        
        let name = appInfo["name"] as? String ?? ""
        let partnerId = appInfo["partnerId"] as? String ?? ""
        let version = appInfo["version"] as? String ?? ""
        let url = appInfo["url"] as? String ?? ""
        
        STPAPIClient.shared.appInfo = STPAppInfo(name: name, partnerId: partnerId, version: version, url: url)
        self.merchantIdentifier = merchantIdentifier
        
        // payment sheet
//                configurePaymentSheet(clientSecret: "pi_1Pmwp4KJ38Q1wp9dnXC14PII_secret_AU0T3J2l0EHhYK5bbEDXcdC8h")
//
//                       DispatchQueue.main.async {
//                           self.presentPayment { result in
//                               switch result {
//                               case .success:
//                                   print("Payment succeeded")
//                               case .failure(let error):
//                                   print("Payment failed: \(error.localizedDescription)")
//                               }
//                           }
//                       }
        
//        DispatchQueue.main.async {
//            guard let window = UIApplication.shared.windows.first,
//                  let rootViewController = window.rootViewController else {
//                print("StripeSdkBridgeError")
//                return
//            }
//            self.presentCardFieldView(from: rootViewController)
//            
//        }
        
//        DispatchQueue.main.async {
//                guard let window = UIApplication.shared.windows.first,
//                      let rootViewController = window.rootViewController else {
//                    print("StripeSdkBridgeError")
//                    return
//                }
//
//                // Render CardFormView
//                self.renderCardFormView(on: rootViewController) { result in
//                    switch result {
//                    case .success(let cardParams):
//                        if let params = cardParams {
//                            print("Card Params: \(params)")
//                            // Handle card parameters (e.g., pass to another method or process)
//                        } else {
//                            print("No card parameters available")
//                        }
//                    case .failure(let error):
//                        print("Error presenting CardFormView: \(error.localizedDescription)")
//                        // Handle error
//                    }
//                }
//            }
        
    }
    
    func configure3dSecure(_ params: NSDictionary) {
        let threeDSCustomizationSettings = STPPaymentHandler.shared().threeDSCustomizationSettings
        let uiCustomization = Mappers.mapUICustomization(params)
        threeDSCustomizationSettings.uiCustomization = uiCustomization
    }
    
    
    public func createPaymentMethod(params: [String : Any], options: [String : Any], onSuccess: @escaping ([String : Any]) -> Void, onError: @escaping (KotlinThrowable) -> Void) throws {
        
        let type = Mappers.mapToPaymentMethodType(type: params["paymentMethodType"] as? String)
        guard let paymentMethodType = type else {
            onError(KotlinThrowable(message:  Errors.createError(ErrorType.Failed, "You must provide paymentMethodType").description))
            return
        }
        
        var paymentMethodParams: STPPaymentMethodParams?
        let factory = PaymentMethodFactory.init(
            paymentMethodData: params["paymentMethodData"] as? NSDictionary,
            options: options as NSDictionary,
            cardFieldView: cardFieldView,
            cardFormView: cardFormView
        )
        
        do {
            paymentMethodParams = try factory.createParams(paymentMethodType: paymentMethodType)
        } catch  {
            onError(KotlinThrowable(message:  Errors.createError(ErrorType.Failed, error.localizedDescription).description))
            return
        }
        
        if let paymentMethodParams = paymentMethodParams {
            STPAPIClient.shared.createPaymentMethod(with: paymentMethodParams) { paymentMethod, error in
                if let createError = error {
                    onError(KotlinThrowable(message:  Errors.createError(ErrorType.Failed, createError.localizedDescription).description))
                    
                } else {
                    onSuccess(Mappers.mapFromPaymentMethod(paymentMethod) as! [String : Any])
                }
            }
        } else {
            onError(KotlinThrowable(message:  Errors.createError(ErrorType.Unknown, "Unhandled error occured").description))
        }
    }
    

    
    // blow block code mainly for testing purpose will be removed when sdk going for live
    
    // payment sheet main ----- start ------------------
    
    
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
    
    
    // payment sheet end --------------------------------------------
    
    
    // temp for create payment view --- start --------------
    public func renderCardFieldView(on viewController: UIViewController, completion: @escaping (Result<STPPaymentMethodParams?, Error>) -> Void) {
        // Initialize CardFieldView
        if cardFieldView == nil {
            
            let cardFieldFrame = CGRect(x: 0, y: 0, width: viewController.view.frame.width - 40, height: 200)
            self.cardFieldView = CardFieldView(frame: cardFieldFrame)
            self.cardFieldView?.center = viewController.view.center
        }
        
        
        if let cardFieldView = self.cardFieldView {
            viewController.view.addSubview(cardFieldView)
            // Optional: You may want to call this after adding the view
            cardFieldView.layoutIfNeeded()
        } else {
            print("Failed to initialize CardFieldView")
        }
    }
    
    
    public func presentCardFieldView(from viewController: UIViewController) {
        
        self.renderCardFieldView(on: viewController) { result in
            switch result {
            case .success(let cardParams):
                if let params = cardParams {
                    print("Card Params: \(params)")
                } else {
                    print("No card parameters available")
                }
            case .failure(let error):
                print("Error presenting CardFieldView: \(error.localizedDescription)")
            }
        }
        
    }
    
    
    

    public func renderCardFormView(on viewController: UIViewController, completion: @escaping (Result<STPPaymentMethodCardParams?, Error>) -> Void) {
        // Initialize CardFormView if it doesn't already exist
        if cardFormView == nil {
            let cardFormFrame = CGRect(x: 20, y: 100, width: viewController.view.frame.width - 40, height: 200)
            cardFormView = CardFormView(frame: cardFormFrame)
            cardFormView?.center = viewController.view.center
        }

        if let cardFormView = self.cardFormView {
            // Configure CardFormView
            cardFormView.autofocus = true
            cardFormView.cardStyle = ["backgroundColor": UIColor.red] // Customize as needed
            
            // Add CardFormView to the view controller's view
            viewController.view.addSubview(cardFormView)
            cardFormView.layoutIfNeeded()
            
      
            
        } else {
            print("Failed to initialize CardFormView")
            completion(.failure(NSError(domain: "StripeSDKError", code: 0, userInfo: [NSLocalizedDescriptionKey: "CardFormView initialization failed."])))
        }
    }
    
    
    
    
  
    // temp for create payment view --- end --------------
    
    
    
}


    
    
    
