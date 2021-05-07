//
//  PaymentRespPARAM.h
//  EGHLPayment
//
//  Created by Arif Jusoh on 10/04/2018.
//  Copyright © 2018 ghl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface PaymentRespPARAM : NSObject
@property (strong, nonatomic) NSString * Amount;
@property (strong, nonatomic) NSString * AuthCode;
@property (strong, nonatomic) NSString * BankRefNo;

@property (strong, nonatomic) NSString * CardExp;
@property (strong, nonatomic) NSString * CardHolder;
@property (strong, nonatomic) NSString * CardNoMask;
@property (strong, nonatomic) NSString * CardType;
@property (strong, nonatomic) NSString * CustID;
@property (strong, nonatomic) NSString * CurrencyCode;

@property (strong, nonatomic) NSString * EPPMonth;
@property (strong, nonatomic) NSString * EPP_YN;

@property (strong, nonatomic) NSString * HashValue;
@property (strong, nonatomic) NSString * HashValue2;

@property (strong, nonatomic) NSString * IssuingBank;
@property (strong, nonatomic) NSString * OrderNumber;

@property (strong, nonatomic) NSString * PromoCode;
@property (strong, nonatomic) NSString * PromoOriAmt;
@property (strong, nonatomic) NSString * Param6;
@property (strong, nonatomic) NSString * Param7;
@property (strong, nonatomic) NSString * PaymentID;
@property (strong, nonatomic) NSString * PymtMethod;
@property (strong, nonatomic) NSString * PaymentType;

@property (strong, nonatomic) NSString * QueryDesc;

@property (strong, nonatomic) NSString * ServiceID;
@property (strong, nonatomic) NSString * SessionID;
@property (strong, nonatomic) NSString * SettleTAID;

@property (strong, nonatomic) NSString * TID;
@property (strong, nonatomic) NSString * TotalRefundAmount;
@property (strong, nonatomic) NSString * Token;
@property (strong, nonatomic) NSString * TokenType;
@property (strong, nonatomic) NSString * TransactionType;
@property (strong, nonatomic) NSString * TxnExists;
@property (strong, nonatomic) NSString * TxnID;
@property (strong, nonatomic) NSString * TxnMessage;
@property (strong, nonatomic) NSString * TxnStatus;
@property (strong, nonatomic) NSString * RespTime;

@property (strong, nonatomic) NSDictionary * rawResponseDict;
//---------------
#pragma mark Masterpass fields
//---------------
@property (strong, nonatomic) NSString * ReqToken;
@property (strong, nonatomic) NSString * PairingToken;

@property (strong, nonatomic) NSString * PreCheckoutId;
@property (strong, nonatomic) NSArray * Cards;

@property (nonatomic, strong) NSDictionary * mpLightboxError;
@end
