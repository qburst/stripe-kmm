//
//  PaymentSheetPresenter.swift
//  iosApp
//
//  Created by abdul basith on 30/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct PaymentSheetPresenter: UIViewControllerRepresentable {
    var stripeSdk: StripeDemoSDK
    var completion: (Result<Void, Error>) -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        return UIViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        DispatchQueue.main.async {
            stripeSdk.presentPaymentSheet(from: uiViewController) { success, error in
                if success {
                    completion(.success(()))
                } else if let error = error {
                    completion(.failure(error))
                }
            }
        }
    }
}
