//
//  CardFieldView.swift
//  iosApp
//
//  Created by Abdul Basith on 12/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//


import Foundation
import UIKit
import Stripe

class CardFieldView: UIView, STPPaymentCardTextFieldDelegate {
    
    private var cardField = STPPaymentCardTextField()
    
    public var cardParams: STPPaymentMethodParams? = nil
    public var cardPostalCode: String? = nil
    
    public var disabled: Bool = false {
        didSet {
            cardField.isUserInteractionEnabled = !disabled
        }
    }
    
    public var postalCodeEnabled: Bool = true {
        didSet {
            cardField.postalCodeEntryEnabled = postalCodeEnabled
        }
    }
    
    public var countryCode: String? {
        didSet {
            cardField.countryCode = countryCode
        }
    }
    
    public var preferredNetworks: [Int]? {
        didSet {
            if let preferredNetworks = preferredNetworks {
                cardField.preferredNetworks = preferredNetworks.map(Mappers.intToCardBrand).compactMap { $0 }
            }
        }
    }
    
    public var placeholders: [String: String] = [:] {
        didSet {
            cardField.numberPlaceholder = placeholders["number"] ?? "1234123412341234"
            cardField.expirationPlaceholder = placeholders["expiration"] ?? "MM/YY"
            cardField.cvcPlaceholder = placeholders["cvc"] ?? "CVC"
            cardField.postalCodePlaceholder = placeholders["postalCode"] ?? "ZIP"
        }
    }
    
    public var cardStyle: [String: Any] = [:] {
        didSet {
            cardField.borderWidth = CGFloat(cardStyle["borderWidth"] as? Int ?? 0)
            cardField.backgroundColor = UIColor(named: cardStyle["backgroundColor"] as? String ?? "#FFFFFF")
            cardField.borderColor = UIColor(named: cardStyle["borderColor"] as? String ?? "#000000")
            cardField.cornerRadius = CGFloat(cardStyle["borderRadius"] as? Int ?? 0)
//            cardField.cursorColor = UIColor(hexString: cardStyle["cursorColor"] as? String ?? "#000000")
//            cardField.textColor = UIColor(hexString: cardStyle["textColor"] as? String ?? "#000000")
//            cardField.textErrorColor = UIColor(hexString: cardStyle["textErrorColor"] as? String ?? "#FF0000")
            
            let fontSize = cardStyle["fontSize"] as? Int ?? 14
            if let fontFamily = cardStyle["fontFamily"] as? String {
                cardField.font = UIFont(name: fontFamily, size: CGFloat(fontSize)) ?? UIFont.systemFont(ofSize: CGFloat(fontSize))
            } else {
                cardField.font = UIFont.systemFont(ofSize: CGFloat(fontSize))
            }
            
            if let placeholderColor = cardStyle["placeholderColor"] as? String {
//                cardField.placeholderColor = UIColor(hexString: placeholderColor)
            }
        }
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        cardField.delegate = self
        self.addSubview(cardField)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func focus() {
        cardField.becomeFirstResponder()
    }
    
    func blur() {
        cardField.resignFirstResponder()
    }
    
    func clear() {
        cardField.clear()
    }
    
    func paymentCardTextFieldDidChange(_ textField: STPPaymentCardTextField) {
        let brand = STPCardValidator.brand(forNumber: textField.cardNumber ?? "")
        let validExpiryDate = STPCardValidator.validationState(
            forExpirationYear: textField.formattedExpirationYear ?? "",
            inMonth: textField.formattedExpirationMonth ?? ""
        )
        let validCVC = STPCardValidator.validationState(forCVC: textField.cvc ?? "", cardBrand: brand)
        let validNumber = STPCardValidator.validationState(forNumber: textField.cardNumber ?? "", validatingCardBrand: true)
        
        var cardData: [String: Any?] = [
            "expiryMonth": textField.expirationMonth,
            "expiryYear": textField.expirationYear,
            "complete": textField.isValid,
            "brand": Mappers.mapFromCardBrand(brand) ?? NSNull(),
            "last4": textField.paymentMethodParams.card?.last4 ?? "",
            "validExpiryDate": Mappers.mapFromCardValidationState(state: validExpiryDate),
            "validCVC": Mappers.mapFromCardValidationState(state: validCVC),
            "validNumber": Mappers.mapFromCardValidationState(state: validNumber)
        ]
        
        if cardField.postalCodeEntryEnabled {
            cardData["postalCode"] = textField.postalCode ?? ""
        }
        
        if textField.isValid {
            self.cardParams = textField.paymentMethodParams
            self.cardPostalCode = textField.postalCode
        } else {
            self.cardParams = nil
            self.cardPostalCode = nil
        }
        
        // Callback to KMM can be implemented here, or via a delegate method
    }
    
    override func layoutSubviews() {
        cardField.frame = self.bounds
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
}
