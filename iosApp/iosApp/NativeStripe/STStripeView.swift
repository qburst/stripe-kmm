//
//  STStripeView.swift
//  iosApp
//
//  Created by abdul basith on 01/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct STStripeView: View {
    @StateObject private var stripeSDK = StripeDemoSDK()
    @State private var paymentResult: Result<Void, Error>? = nil

    var body: some View {
        VStack {
            Button("Pay") {
                // Trigger the presentation of the payment sheet
                stripeSDK.showPaymentSheet()
            }
            .sheet(isPresented: $stripeSDK.isPaymentSheetPresented) {
                PaymentSheetPresenter(stripeSdk: stripeSDK) { result in
                    paymentResult = result
                    // Hide the payment sheet after the result is processed
                    stripeSDK.hidePaymentSheet()
                }
            }
            if let result = paymentResult {
                switch result {
                case .success:
                    Text("Payment succeeded")
                case .failure(let error):
                    Text("Payment failed: \(error.localizedDescription)")
                }
            }
        }
        .onAppear {
            // Configure the payment sheet with the client secret
            let clientSecret = "pi_1PibQbKJ38Q1wp9dRCmmV2EO_secret_foEQUROXkGXpxlTti07bcUaBy"
            stripeSDK.configurePaymentSheet(clientSecret: clientSecret)
        }
    }
}
