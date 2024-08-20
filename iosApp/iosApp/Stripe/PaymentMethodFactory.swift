//
//  PaymentMethodFactory.swift
//  iosApp
//
//  Created by abdul basith on 08/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import StripePayments

class PaymentMethodFactory {
    var billingDetailsParams: STPPaymentMethodBillingDetails? = nil
    var paymentMethodData: NSDictionary? = nil
    var paymentMethodOptions: NSDictionary? = nil
    var cardFieldView: CardFieldView? = nil
    var cardFormView: CardFormView? = nil
    
    
    
    init(paymentMethodData: NSDictionary?, options: NSDictionary, cardFieldView: CardFieldView?, cardFormView: CardFormView?) {
           self.paymentMethodData = paymentMethodData
           self.billingDetailsParams = Mappers.mapToBillingDetails(billingDetails: paymentMethodData?["billingDetails"] as? NSDictionary)
           self.paymentMethodOptions = options
           self.cardFieldView = cardFieldView
           self.cardFormView = cardFormView
       }
    
    
    
    func createParams(paymentMethodType: STPPaymentMethodType) throws -> STPPaymentMethodParams? {
            do {
                switch paymentMethodType {
                case STPPaymentMethodType.card:
                    return try createCardPaymentMethodParams()
               

                default:
                    throw PaymentMethodError.paymentNotSupported
                }
            } catch {
                throw error
            }
        }
    
        
        private func createCardPaymentMethodParams() throws -> STPPaymentMethodParams {
            
                if let token = paymentMethodData?["token"] as? String {
                    let methodParams = STPPaymentMethodCardParams()
                    methodParams.token = token
                    return STPPaymentMethodParams(card: methodParams, billingDetails: billingDetailsParams, metadata: nil)
                }
                if let params = cardFieldView?.cardParams as? STPPaymentMethodParams {
                    if let postalCode = cardFieldView?.cardPostalCode{
                        if (billingDetailsParams == nil) {
                            let bd = STPPaymentMethodBillingDetails()
                            bd.address = STPPaymentMethodAddress()
                            bd.address?.postalCode = postalCode
                            billingDetailsParams = bd
                        } else {
                            billingDetailsParams?.address?.postalCode = postalCode
                        }
                    }
                    params.billingDetails = billingDetailsParams
                    return params
                }
                if let params = cardFormView?.cardParams as? STPPaymentMethodCardParams {
                    if let address = cardFormView?.cardForm?.cardParams?.billingDetails?.address {
                        if (billingDetailsParams == nil) {
                            let bd = STPPaymentMethodBillingDetails()
                            bd.address = STPPaymentMethodAddress()
                            bd.address?.postalCode = address.postalCode
                            bd.address?.country = address.country
                            billingDetailsParams = bd
                        } else {
                            billingDetailsParams?.address?.postalCode = address.postalCode
                            billingDetailsParams?.address?.country = address.country
                        }
                    }
                    return STPPaymentMethodParams(card: params, billingDetails: billingDetailsParams, metadata: nil)
                }

                throw PaymentMethodError.cardPaymentMissingParams
            }
    
    
}

enum PaymentMethodError: Error {
    case cardPaymentMissingParams
    case epsPaymentMissingParams
    case idealPaymentMissingParams
    case paymentNotSupported
    case sofortPaymentMissingParams
    case cardPaymentOptionsMissingParams
    case bancontactPaymentMissingParams
    case sepaPaymentMissingParams
    case giropayPaymentMissingParams
    case p24PaymentMissingParams
    case afterpayClearpayPaymentMissingParams
    case klarnaPaymentMissingParams
    case weChatPayPaymentMissingParams
    case usBankAccountPaymentMissingParams
    case usBankAccountPaymentMissingAccountNumber
    case usBankAccountPaymentMissingRoutingNumber
}





extension PaymentMethodError: LocalizedError {
    public var errorDescription: String? {
        switch self {
        case .cardPaymentMissingParams:
            return NSLocalizedString("Card details not complete", comment: "Create payment error")
        case .giropayPaymentMissingParams:
            return NSLocalizedString("You must provide billing details", comment: "Create payment error")
        case .idealPaymentMissingParams:
            return NSLocalizedString("You must provide bank name", comment: "Create payment error")
        case .sofortPaymentMissingParams:
            return NSLocalizedString("You must provide bank account country", comment: "Create payment error")
        case .p24PaymentMissingParams:
            return NSLocalizedString("You must provide billing details", comment: "Create payment error")
        case .bancontactPaymentMissingParams:
            return NSLocalizedString("You must provide billing details", comment: "Create payment error")
        case .sepaPaymentMissingParams:
            return NSLocalizedString("You must provide billing details and IBAN", comment: "Create payment error")
        case .epsPaymentMissingParams:
            return NSLocalizedString("You must provide billing details", comment: "Create payment error")
        case .afterpayClearpayPaymentMissingParams:
            return NSLocalizedString("You must provide billing details", comment: "Create payment error")
        case .paymentNotSupported:
            return NSLocalizedString("This payment type is not supported yet", comment: "Create payment error")
        case .cardPaymentOptionsMissingParams:
            return NSLocalizedString("You must provide CVC number", comment: "Create payment error")
        case .weChatPayPaymentMissingParams:
            return NSLocalizedString("You must provide appId", comment: "Create payment error")
        case .klarnaPaymentMissingParams:
            return NSLocalizedString("Klarna requires that you provide the following billing details: email, country", comment: "Create payment error")
        case .usBankAccountPaymentMissingParams:
            return NSLocalizedString("When creating a US bank account payment method, you must provide the following billing details: name", comment: "Create payment error")
        case .usBankAccountPaymentMissingAccountNumber:
            return NSLocalizedString("When creating a US bank account payment method, you must provide the bank account number", comment: "Create payment error")
        case .usBankAccountPaymentMissingRoutingNumber:
            return NSLocalizedString("When creating a US bank account payment method, you must provide the bank routing number", comment: "Create payment error")
        }
    }
}
