//
//  PaymentRequestPARAM.h
//  EGHLPayment
//
//  Created by Arif Jusoh on 10/04/2018.
//  Copyright © 2018 ghl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

/**
 * @description Parameter Class for GHL API
 *
 * <code> Amount, PaymentID, OrderNumber, MerchantName, ServiceID, PymtMethod, PaymentType, MerchantReturnURL, CustEmail, Password, CustPhone, CurrencyCode, CustName, LanguageCode, PaymentDesc, PageTimeout, CustIP, MerchantApprovalURL, CustMAC, MerchantUnApprovalURL, CardHolder, CardNo, CardExp, CardCVV2, BillAddr, BillPostal, BillCity, BillRegion, BillCountry, ShipAddr, ShipPostal, ShipCity, ShipRegion, ShipCountry, TokenType, Token, SessionID, IssuingBank, MerchantCallBackURL, B4TaxAmt, TaxAmt, Param6, Param7 </code>
 *
 *
 * Masterpass Fields:
 *
 * <code>ReqVerifier, PairingVerifier, CheckoutResourceURL, ReqToken, PairingToken</code>
 */
@interface PaymentRequestPARAM : NSObject
@property (strong, nonatomic) NSDictionary * settingDict;

@property (strong, nonatomic) NSString * Amount;
@property (strong, nonatomic) NSString * PaymentID;
@property (strong, nonatomic) NSString * OrderNumber;
@property (strong, nonatomic) NSString * MerchantName;
@property (strong, nonatomic) NSString * ServiceID;
@property (strong, nonatomic) NSString * PymtMethod;
@property (strong, nonatomic) NSString * PaymentType;
@property (strong, nonatomic) NSString * MerchantReturnURL;
@property (strong, nonatomic) NSString * CustEmail;
@property (strong, nonatomic) NSString * Password;
@property (strong, nonatomic) NSString * CustPhone;
@property (strong, nonatomic) NSString * CurrencyCode;
@property (strong, nonatomic) NSString * CustName;
@property (strong, nonatomic) NSString * LanguageCode;
@property (strong, nonatomic) NSString * PaymentDesc;
@property (strong, nonatomic) NSString * PageTimeout;
@property (strong, nonatomic) NSString * CustIP;
@property (strong, nonatomic) NSString * CustID;
@property (strong, nonatomic) NSString * MerchantApprovalURL;
@property (strong, nonatomic) NSString * CustMAC;
@property (strong, nonatomic) NSString * MerchantUnApprovalURL;
@property (strong, nonatomic) NSString * CardHolder;
@property (strong, nonatomic) NSString * CardNo;
@property (strong, nonatomic) NSString * CardExp;
@property (strong, nonatomic) NSString * CardCVV2;
@property (strong, nonatomic) NSString * BillAddr;
@property (strong, nonatomic) NSString * BillPostal;
@property (strong, nonatomic) NSString * BillCity;
@property (strong, nonatomic) NSString * BillRegion;
@property (strong, nonatomic) NSString * BillCountry;

@property (strong, nonatomic) NSString * HashValue;

@property (strong, nonatomic) NSString * ShipAddr;
@property (strong, nonatomic) NSString * ShipPostal;
@property (strong, nonatomic) NSString * ShipCity;
@property (strong, nonatomic) NSString * ShipRegion;
@property (strong, nonatomic) NSString * ShipCountry;
@property (strong, nonatomic) NSString * TransactionType;
@property (strong, nonatomic) NSString * TokenType;
@property (strong, nonatomic) NSString * Token;
@property (strong, nonatomic) NSString * SessionID;
@property (strong, nonatomic) NSString * IssuingBank;
@property (strong, nonatomic) NSString * MerchantCallBackURL;
@property (strong, nonatomic) NSString * B4TaxAmt;
@property (strong, nonatomic) NSString * TaxAmt;
@property (strong, nonatomic) NSString * Param6;
@property (strong, nonatomic) NSString * Param7;

@property (strong, nonatomic) NSString * EPPMonth;
@property (strong, nonatomic) NSString * PromoCode;

@property (nonatomic) CGFloat sdkTimeOut;

#pragma mark Masterpass fields
@property (strong, nonatomic) NSString * ReqVerifier;
@property (strong, nonatomic) NSString * PairingVerifier;
@property (strong, nonatomic) NSString * CheckoutResourceURL;
@property (strong, nonatomic) NSString * ReqToken;
@property (strong, nonatomic) NSString * PairingToken;

@property (strong, nonatomic) NSString * CardId;
@property (strong, nonatomic) NSString * PreCheckoutId;
/**
 Lightbox Paramter reference
 https://developer.mastercard.com/page/masterpass-lightbox-parameters
 */
//---------------
@property (strong, nonatomic) NSDictionary * mpLightboxParameter;

@end
