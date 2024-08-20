//
//  Mappers.swift
//  iosApp
//
//  Created by Abdul Basith on 12/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

import Stripe
import StripePaymentSheet
import UIKit

class Mappers {
    
    
    
    class func mapToBillingDetails(billingDetails: NSDictionary?) -> STPPaymentMethodBillingDetails? {
        guard let billingDetails = billingDetails else {
            return nil
        }
        let billing = STPPaymentMethodBillingDetails()
        billing.email = billingDetails["email"] as? String
        billing.phone = billingDetails["phone"] as? String
        billing.name = billingDetails["name"] as? String

        let address = STPPaymentMethodAddress()

        if let addressMap = billingDetails["address"] as? NSDictionary {
            address.city = addressMap["city"] as? String
            address.postalCode = addressMap["postalCode"] as? String
            address.country = addressMap["country"] as? String
            address.line1 = addressMap["line1"] as? String
            address.line2 = addressMap["line2"] as? String
            address.state = addressMap["state"] as? String
        }

        billing.address = address

        return billing
    }
    
    
    
    class func intToCardBrand(int: Int) -> STPCardBrand? {
            switch int {
            case 0:
                return STPCardBrand.JCB
            case 1:
                return STPCardBrand.amex
            case 2:
                return STPCardBrand.cartesBancaires
            case 3:
                return STPCardBrand.dinersClub
            case 4:
                return STPCardBrand.discover
            case 5:
                return STPCardBrand.mastercard
            case 6:
                return STPCardBrand.unionPay
            case 7:
                return STPCardBrand.visa
            case 8:
                return STPCardBrand.unknown
            default:
                return nil
            }
        }
    
    
    class func mapFromCard(_ card: STPCard?) -> NSDictionary? {
            if (card == nil) {
                return nil
            }
            let cardMap: NSDictionary = [
                "brand": mapFromCardBrand(card?.brand) ?? NSNull(),
                "country": card?.country ?? NSNull(),
                "currency": card?.currency ?? NSNull(),
                "expMonth": card?.expMonth ?? NSNull(),
                "expYear": card?.expYear ?? NSNull(),
                "last4": card?.last4 ?? NSNull(),
                "funding": mapFromFunding(card?.funding) ?? NSNull(),
                "name": card?.name ?? NSNull(),
                "address": mapFromAddress(address: card?.address),
                "id": card?.stripeID ?? NSNull(),
            ]
            return cardMap
        }
    
    
    
    class func mapFromPaymentMethod(_ paymentMethod: STPPaymentMethod?) -> NSDictionary? {
            guard let paymentMethod = paymentMethod else {
                return nil
            }
            let card: NSDictionary = [
                "brand": Mappers.mapFromCardBrand(paymentMethod.card?.brand) ?? NSNull(),
                "country": paymentMethod.card?.country ?? NSNull(),
                "expYear": paymentMethod.card?.expYear ?? NSNull(),
                "expMonth": paymentMethod.card?.expMonth ?? NSNull(),
                "fingerprint": paymentMethod.card?.fingerprint ?? NSNull(),
                "funding": paymentMethod.card?.funding ?? NSNull(),
                "last4": paymentMethod.card?.last4 ?? NSNull(),
                "preferredNetwork": paymentMethod.card?.networks?.preferred ?? NSNull(),
                "availableNetworks": paymentMethod.card?.networks?.available ?? NSNull(),
                "threeDSecureUsage": [
                  "isSupported": paymentMethod.card?.threeDSecureUsage?.supported ?? false
                ],
            ]

            let sepaDebit: NSDictionary = [
                "bankCode": paymentMethod.sepaDebit?.bankCode ?? NSNull(),
                "country": paymentMethod.sepaDebit?.country ?? NSNull(),
                "fingerprint": paymentMethod.sepaDebit?.fingerprint ?? NSNull(),
                "last4": paymentMethod.sepaDebit?.last4 ?? NSNull(),
            ]
            let bacsDebit: NSDictionary = [
                "fingerprint": paymentMethod.bacsDebit?.fingerprint ?? NSNull(),
                "last4": paymentMethod.bacsDebit?.last4 ?? NSNull(),
                "sortCode": paymentMethod.bacsDebit?.sortCode ?? NSNull()
            ]
            let auBECSDebit: NSDictionary = [
                "bsbNumber": paymentMethod.auBECSDebit?.bsbNumber ?? NSNull(),
                "fingerprint": paymentMethod.auBECSDebit?.fingerprint ?? NSNull(),
                "last4": paymentMethod.auBECSDebit?.last4 ?? NSNull()
            ]
            let USBankAccount: NSDictionary = [
                "routingNumber": paymentMethod.usBankAccount?.routingNumber ?? NSNull(),
                "accountHolderType": mapFromUSBankAccountHolderType(type: paymentMethod.usBankAccount?.accountHolderType),
                "accountType": mapFromUSBankAccountType(type: paymentMethod.usBankAccount?.accountType),
                "last4": paymentMethod.usBankAccount?.last4 ?? NSNull(),
                "bankName": paymentMethod.usBankAccount?.bankName ?? NSNull(),
                "linkedAccount": paymentMethod.usBankAccount?.linkedAccount ?? NSNull(),
                "fingerprint": paymentMethod.usBankAccount?.fingerprint ?? NSNull(),
                "preferredNetworks": paymentMethod.usBankAccount?.networks?.preferred ?? NSNull(),
                "supportedNetworks": paymentMethod.usBankAccount?.networks?.supported ?? NSNull(),
            ]
            let method: NSDictionary = [
                "id": paymentMethod.stripeId,
                "paymentMethodType": Mappers.mapPaymentMethodType(type: paymentMethod.type),
                "livemode": paymentMethod.liveMode,
                "customerId": paymentMethod.customerId ?? NSNull(),
                "billingDetails": Mappers.mapFromBillingDetails(billingDetails: paymentMethod.billingDetails),
                "Card": card,
                "Ideal": [
                    "bankIdentifierCode": paymentMethod.iDEAL?.bankIdentifierCode ?? "",
                    "bankName": paymentMethod.iDEAL?.bankName ?? ""
                ],
                "Fpx": [
                    "bank": paymentMethod.fpx?.bankIdentifierCode ?? "",
                ],
                "SepaDebit": sepaDebit,
                "BacsDebit": bacsDebit,
                "AuBecsDebit": auBECSDebit,
                "Sofort": [
                    "country": paymentMethod.sofort?.country
                ],
                "Upi": [
                    "vpa": paymentMethod.upi?.vpa
                ],
                "USBankAccount": USBankAccount
            ]
            return method
        }
    
    
    class func mapFromUSBankAccountHolderType(type: STPPaymentMethodUSBankAccountHolderType?) -> String {
          if let type = type {
              switch type {
                  case STPPaymentMethodUSBankAccountHolderType.company: return "Company"
                  case STPPaymentMethodUSBankAccountHolderType.individual: return "Individual"
                  case STPPaymentMethodUSBankAccountHolderType.unknown: return "Unknown"
              }
          }
          return "Unknown"
      }
    
    
    
    class func mapFromBillingDetails(billingDetails: STPPaymentMethodBillingDetails?) -> NSDictionary {
        let billing: NSDictionary = [
            "email": billingDetails?.email ?? NSNull(),
            "phone": billingDetails?.phone ?? NSNull(),
            "name": billingDetails?.name ?? NSNull(),
            "address": [
                "city": billingDetails?.address?.city,
                "postalCode": billingDetails?.address?.postalCode,
                "country": billingDetails?.address?.country,
                "line1": billingDetails?.address?.line1,
                "line2": billingDetails?.address?.line2,
                "state": billingDetails?.address?.state,
            ],
        ]

        return billing
    }
    
    
    class func mapFromUSBankAccountType(type: STPPaymentMethodUSBankAccountType?) -> String {
         if let type = type {
             switch type {
                 case STPPaymentMethodUSBankAccountType.savings: return "Savings"
                 case STPPaymentMethodUSBankAccountType.checking: return "Checking"
                 case STPPaymentMethodUSBankAccountType.unknown: return "Unknown"
             }
         }
         return "Unknown"
     }
    
    
    class func mapFromCardBrand(_ brand: STPCardBrand?) -> String? {
            if let brand = brand {
                switch brand {
                case STPCardBrand.visa: return "Visa"
                case STPCardBrand.amex: return "AmericanExpress"
                case STPCardBrand.mastercard: return "MasterCard"
                case STPCardBrand.discover: return "Discover"
                case STPCardBrand.JCB: return "JCB"
                case STPCardBrand.dinersClub: return "DinersClub"
                case STPCardBrand.unionPay: return "UnionPay"
                case STPCardBrand.unknown: return "Unknown"
                default: return nil
                }
            }
            return nil
        }
    
    class func mapFromFunding(_ funding: STPCardFundingType?) -> String? {
          if let funding = funding {
              switch funding {
              case STPCardFundingType.credit: return "Credit"
              case STPCardFundingType.debit: return "Debit"
              case STPCardFundingType.prepaid: return "Prepaid"
              case STPCardFundingType.other: return "Unknown"

              default: return nil
              }
          }
          return nil
      }
    
    class func mapFromCardValidationState(state: STPCardValidationState?) -> String {
           if let state = state {
               switch state {
               case STPCardValidationState.valid: return "Valid"
               case STPCardValidationState.invalid: return "Invalid"
               case STPCardValidationState.incomplete: return "Incomplete"
               default: return "Unknown"
               }
           }
           return "Unknown"
       }
    
    class func mapFromAddress(address: STPAddress?) -> NSDictionary {
           let result: NSDictionary = [
               "city": address?.city ?? NSNull(),
               "postalCode": address?.postalCode ?? NSNull(),
               "country": address?.country ?? NSNull(),
               "line1": address?.line1 ?? NSNull(),
               "line2": address?.line2 ?? NSNull(),
               "state": address?.state ?? NSNull(),
           ]

           return result
       }
    
    class func mapToPaymentMethodType(type: String?) -> STPPaymentMethodType? {
            if let type = type {
                switch type {
                case "Card": return STPPaymentMethodType.card
                case "Alipay": return STPPaymentMethodType.alipay
                case "GrabPay": return STPPaymentMethodType.grabPay
                case "Ideal": return STPPaymentMethodType.iDEAL
                case "Fpx": return STPPaymentMethodType.FPX
                case "CardPresent": return STPPaymentMethodType.cardPresent
                case "SepaDebit": return STPPaymentMethodType.SEPADebit
                case "AuBecsDebit": return STPPaymentMethodType.AUBECSDebit
                case "BacsDebit": return STPPaymentMethodType.bacsDebit
                case "Giropay": return STPPaymentMethodType.giropay
                case "P24": return STPPaymentMethodType.przelewy24
                case "Eps": return STPPaymentMethodType.EPS
                case "Bancontact": return STPPaymentMethodType.bancontact
                case "Oxxo": return STPPaymentMethodType.OXXO
                case "Sofort": return STPPaymentMethodType.sofort
                case "Upi": return STPPaymentMethodType.UPI
                case "AfterpayClearpay": return STPPaymentMethodType.afterpayClearpay
                case "Klarna": return STPPaymentMethodType.klarna
                case "WeChatPay": return STPPaymentMethodType.weChatPay
                case "USBankAccount": return STPPaymentMethodType.USBankAccount
                case "PayPal": return STPPaymentMethodType.payPal
                case "Affirm": return STPPaymentMethodType.affirm
                case "CashApp": return STPPaymentMethodType.cashApp
                case "RevolutPay": return STPPaymentMethodType.revolutPay
                default: return STPPaymentMethodType.unknown
                }
            }
            return nil
        }
    
    class func mapPaymentMethodType(type: STPPaymentMethodType) -> String {
            switch type {
            case STPPaymentMethodType.card: return "Card"
            case STPPaymentMethodType.alipay: return "Alipay"
            case STPPaymentMethodType.grabPay: return "GrabPay"
            case STPPaymentMethodType.iDEAL: return "Ideal"
            case STPPaymentMethodType.FPX: return "Fpx"
            case STPPaymentMethodType.cardPresent: return "CardPresent"
            case STPPaymentMethodType.SEPADebit: return "SepaDebit"
            case STPPaymentMethodType.AUBECSDebit: return "AuBecsDebit"
            case STPPaymentMethodType.bacsDebit: return "BacsDebit"
            case STPPaymentMethodType.giropay: return "Giropay"
            case STPPaymentMethodType.przelewy24: return "P24"
            case STPPaymentMethodType.EPS: return "Eps"
            case STPPaymentMethodType.bancontact: return "Bancontact"
            case STPPaymentMethodType.OXXO: return "Oxxo"
            case STPPaymentMethodType.sofort: return "Sofort"
            case STPPaymentMethodType.UPI: return "Upi"
            case STPPaymentMethodType.afterpayClearpay: return "AfterpayClearpay"
            case STPPaymentMethodType.klarna: return "Klarna"
            case STPPaymentMethodType.USBankAccount: return "USBankAccount"
            case STPPaymentMethodType.payPal: return "PayPal"
            case STPPaymentMethodType.affirm: return "Affirm"
            case STPPaymentMethodType.cashApp: return "CashApp"
            case STPPaymentMethodType.revolutPay: return "RevolutPay"
            case STPPaymentMethodType.unknown: return "Unknown"
            default: return "Unknown"
            }
        }

        
    
    
    class func mapUICustomization(_ params: NSDictionary) -> STPThreeDSUICustomization {
            let uiCustomization = STPThreeDSUICustomization()
            if let labelSettings = params["label"] as? Dictionary<String, Any?> {
                if let headingTextColor = labelSettings["headingTextColor"] as? String {
                    uiCustomization.labelCustomization.headingTextColor = UIColor(hexString: headingTextColor)
                }
                if let textColor = labelSettings["textColor"] as? String {
                    uiCustomization.labelCustomization.textColor = UIColor(hexString: textColor)
                }
                if let headingFontSize = labelSettings["headingFontSize"] as? Int {
                    uiCustomization.labelCustomization.headingFont = UIFont.systemFont(ofSize: CGFloat(headingFontSize))
                }
                if let textFontSize = labelSettings["textFontSize"] as? Int {
                    uiCustomization.labelCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
            }

            if let navigationBarSettings = params["navigationBar"] as? Dictionary<String, Any?> {
                if let barTintColor = navigationBarSettings["barTintColor"] as? String {
                    uiCustomization.navigationBarCustomization.barTintColor = UIColor(hexString: barTintColor)
                }
                if let barStyle = navigationBarSettings["barStyle"] as? Int {
                    uiCustomization.navigationBarCustomization.barStyle = UIBarStyle(rawValue: barStyle) ?? .default
                }
                if let headerText = navigationBarSettings["headerText"] as? String {
                    uiCustomization.navigationBarCustomization.headerText = headerText
                }
                if let buttonText = navigationBarSettings["buttonText"] as? String {
                    uiCustomization.navigationBarCustomization.buttonText = buttonText
                }
                if let textFontSize = navigationBarSettings["textFontSize"] as? Int {
                    uiCustomization.navigationBarCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
                if let textColor = navigationBarSettings["textColor"] as? String {
                    uiCustomization.navigationBarCustomization.textColor = UIColor(hexString: textColor)
                }
                if let translucent = navigationBarSettings["translucent"] as? Bool {
                    uiCustomization.navigationBarCustomization.translucent = translucent
                }
            }

            if let textFieldSettings = params["textField"] as? Dictionary<String, Any?> {
                if let borderColor = textFieldSettings["borderColor"] as? String {
                    uiCustomization.textFieldCustomization.borderColor = UIColor(hexString: borderColor)
                }
                if let borderWidth = textFieldSettings["borderWidth"] as? Int {
                    uiCustomization.textFieldCustomization.borderWidth = CGFloat(borderWidth)
                }
                if let borderRadius = textFieldSettings["borderRadius"] as? Int {
                    uiCustomization.textFieldCustomization.cornerRadius = CGFloat(borderRadius)
                }
                if let textColor = textFieldSettings["textColor"] as? String {
                    uiCustomization.textFieldCustomization.textColor = UIColor(hexString: textColor)
                }
                if let textFontSize = textFieldSettings["textFontSize"] as? Int {
                    uiCustomization.textFieldCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
            }

            if let footerSettings = params["footer"] as? Dictionary<String, Any?> {
                if let backgroundColor = footerSettings["backgroundColor"] as? String {
                    uiCustomization.footerCustomization.backgroundColor = UIColor(hexString: backgroundColor)
                }
                if let chevronColor = footerSettings["chevronColor"] as? String {
                    uiCustomization.footerCustomization.chevronColor = UIColor(hexString: chevronColor)
                }
                if let headingTextColor = footerSettings["headingTextColor"] as? String {
                    uiCustomization.footerCustomization.headingTextColor = UIColor(hexString: headingTextColor)
                }
                if let textColor = footerSettings["textColor"] as? String {
                    uiCustomization.footerCustomization.textColor = UIColor(hexString: textColor)
                }
            }

            if let submitButtonSettings = params["submitButton"] as? Dictionary<String, Any?> {
                let buttonCustomization = uiCustomization.buttonCustomization(for: STPThreeDSCustomizationButtonType.submit)

                if let backgroundColor = submitButtonSettings["backgroundColor"] as? String {
                    buttonCustomization.backgroundColor = UIColor(hexString: backgroundColor)
                }
                if let borderRadius = submitButtonSettings["borderRadius"] as? Int {
                    buttonCustomization.cornerRadius = CGFloat(borderRadius)
                }
                if let textFontSize = submitButtonSettings["textFontSize"] as? Int {
                    buttonCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
                if let textColor = submitButtonSettings["textColor"] as? String {
                    buttonCustomization.textColor = UIColor(hexString: textColor)
                }

                uiCustomization.setButtonCustomization(buttonCustomization, for: STPThreeDSCustomizationButtonType.submit)
            }

            if let submitButtonSettings = params["cancelButton"] as? Dictionary<String, Any?> {
                let buttonCustomization = uiCustomization.buttonCustomization(for: STPThreeDSCustomizationButtonType.cancel)

                if let backgroundColor = submitButtonSettings["backgroundColor"] as? String {
                    buttonCustomization.backgroundColor = UIColor(hexString: backgroundColor)
                }
                if let borderRadius = submitButtonSettings["borderRadius"] as? Int {
                    buttonCustomization.cornerRadius = CGFloat(borderRadius)
                }
                if let textFontSize = submitButtonSettings["textFontSize"] as? Int {
                    buttonCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
                if let textColor = submitButtonSettings["textColor"] as? String {
                    buttonCustomization.textColor = UIColor(hexString: textColor)
                }

                uiCustomization.setButtonCustomization(buttonCustomization, for: STPThreeDSCustomizationButtonType.cancel)
            }

            if let submitButtonSettings = params["continueButton"] as? Dictionary<String, Any?> {
                let buttonCustomization = uiCustomization.buttonCustomization(for: STPThreeDSCustomizationButtonType.continue)

                if let backgroundColor = submitButtonSettings["backgroundColor"] as? String {
                    buttonCustomization.backgroundColor = UIColor(hexString: backgroundColor)
                }
                if let borderRadius = submitButtonSettings["borderRadius"] as? Int {
                    buttonCustomization.cornerRadius = CGFloat(borderRadius)
                }
                if let textFontSize = submitButtonSettings["textFontSize"] as? Int {
                    buttonCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
                if let textColor = submitButtonSettings["textColor"] as? String {
                    buttonCustomization.textColor = UIColor(hexString: textColor)
                }

                uiCustomization.setButtonCustomization(buttonCustomization, for: STPThreeDSCustomizationButtonType.continue)
            }

            if let submitButtonSettings = params["nextButton"] as? Dictionary<String, Any?> {
                let buttonCustomization = uiCustomization.buttonCustomization(for: STPThreeDSCustomizationButtonType.next)

                if let backgroundColor = submitButtonSettings["backgroundColor"] as? String {
                    buttonCustomization.backgroundColor = UIColor(hexString: backgroundColor)
                }
                if let borderRadius = submitButtonSettings["borderRadius"] as? Int {
                    buttonCustomization.cornerRadius = CGFloat(borderRadius)
                }
                if let textFontSize = submitButtonSettings["textFontSize"] as? Int {
                    buttonCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
                if let textColor = submitButtonSettings["textColor"] as? String {
                    buttonCustomization.textColor = UIColor(hexString: textColor)
                }

                uiCustomization.setButtonCustomization(buttonCustomization, for: STPThreeDSCustomizationButtonType.next)
            }

            if let submitButtonSettings = params["resendButton"] as? Dictionary<String, Any?> {
                let buttonCustomization = uiCustomization.buttonCustomization(for: STPThreeDSCustomizationButtonType.resend)

                if let backgroundColor = submitButtonSettings["backgroundColor"] as? String {
                    buttonCustomization.backgroundColor = UIColor(hexString: backgroundColor)
                }
                if let borderRadius = submitButtonSettings["borderRadius"] as? Int {
                    buttonCustomization.cornerRadius = CGFloat(borderRadius)
                }
                if let textFontSize = submitButtonSettings["textFontSize"] as? Int {
                    buttonCustomization.font = UIFont.systemFont(ofSize: CGFloat(textFontSize))
                }
                if let textColor = submitButtonSettings["textColor"] as? String {
                    buttonCustomization.textColor = UIColor(hexString: textColor)
                }

                uiCustomization.setButtonCustomization(buttonCustomization, for: STPThreeDSCustomizationButtonType.resend)
            }

            if let backgroundColor = params["backgroundColor"] as? String {
                uiCustomization.backgroundColor = UIColor(hexString: backgroundColor)
            }
            return uiCustomization
        }
    
    
    
    class func mapFromSetupIntentLastPaymentErrorType(_ errorType: STPSetupIntentLastSetupErrorType?) -> String? {
          if let errorType = errorType {
              switch errorType {
              case STPSetupIntentLastSetupErrorType.apiConnection: return "api_connection_error"
              case STPSetupIntentLastSetupErrorType.API: return "api_error"
              case STPSetupIntentLastSetupErrorType.authentication: return "authentication_error"
              case STPSetupIntentLastSetupErrorType.card: return "card_error"
              case STPSetupIntentLastSetupErrorType.idempotency: return "idempotency_error"
              case STPSetupIntentLastSetupErrorType.invalidRequest: return "invalid_request_error"
              case STPSetupIntentLastSetupErrorType.rateLimit: return "rate_limit_error"
              case STPSetupIntentLastSetupErrorType.unknown: return nil
              default: return nil
              }
          }
          return nil
      }
    
    class func mapFromPaymentIntentLastPaymentErrorType(_ errorType: STPPaymentIntentLastPaymentErrorType?) -> String? {
        if let errorType = errorType {
            switch errorType {
            case STPPaymentIntentLastPaymentErrorType.apiConnection: return "api_connection_error"
            case STPPaymentIntentLastPaymentErrorType.api: return "api_error"
            case STPPaymentIntentLastPaymentErrorType.authentication: return "authentication_error"
            case STPPaymentIntentLastPaymentErrorType.card: return "card_error"
            case STPPaymentIntentLastPaymentErrorType.idempotency: return "idempotency_error"
            case STPPaymentIntentLastPaymentErrorType.invalidRequest: return "invalid_request_error"
            case STPPaymentIntentLastPaymentErrorType.rateLimit: return "rate_limit_error"
            case STPPaymentIntentLastPaymentErrorType.unknown: return nil
            default: return nil
            }
        }
        return nil
    }
    
    
    
    
}
