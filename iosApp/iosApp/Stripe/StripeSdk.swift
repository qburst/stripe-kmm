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
       
    
}


    
    
    
