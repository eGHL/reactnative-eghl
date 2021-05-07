//
//  EGHLPayment.h
//  EGHL
//
//  Created by Arif Jusoh on 24/04/2018.
//  Copyright © 2018 GHL ePayments Sdn Bhd. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "PaymentRequestPARAM.h"
#import "PaymentRespPARAM.h"

@interface EGHLPayment : NSObject
typedef void (^onSOPTokenResp)(NSString * token, NSString * tokenType, NSString * paymentId);
typedef void (^onSOPBLResp)(NSDictionary * bankDict);

typedef void (^onPaymentResp)(PaymentRespPARAM * responseData);
typedef void (^onErrorCB)(NSString* errorCode, NSString* errorData, NSError * error);
+ (NSString *)version;

/**
 *   @method  execute:(PaymentRequestPARAM *)paymentRequest fromViewController:(id)viewController successBlock:(onPaymentResp)successRequestCB failedBlock:(onErrorCB)failedCB
 *
 *   @param   paymentRequest    A <code>PaymentRequestPARAM</code> object.
 *                              include Amount,PaymentID,OrderNumber,MerchantName,ServiceID,PymtMethod,PaymentType,MerchantReturnURL,CustEmail,Password,CustPhone,
 *                                  CurrencyCode,CustName,LanguageCode,PaymentDesc,PageTimeout,CustIP,MerchantApprovalURL,CustMAC,MerchantUnApprovalURL,CardHolder,
 *                                  CardNo,CardExp,CardCVV2,BillAddr,ShipPostal,ShipCity,ShipRegion,ShipCountry,TokenType,Token,
 *                                  SessionID,IssuingBank,MerchantCallBackURL,B4TaxAmt,TaxAmt,Param6,Param7.
 *   @param   viewController    A viewController for the sdk to launch from.
 *   @param   successBlock      A success block.
 *   @param   failedBlock       A fail block.
 *
 *   @discussion    start enter to sale payment page and perform an online payment transaction.
 */
- (void)execute:(PaymentRequestPARAM *)paymentRequest fromViewController:(id)viewController successBlock:(onPaymentResp)successBlock failedBlock:(onErrorCB)failedBlock;

/**
 *   @method  executeMasterpass:(PaymentRequestPARAM *)paymentParam successBlock:(onPaymentResp)success failedBlock:(onErrorCB)failed
 *
 *   @param   paymentRequest    A <code>PaymentRequestPARAM</code> object.
 *                           include ServiceID, Password, CurrencyCode,
 *                                   PaymentDesc, TokenType, Token.
 *   @param   successBlock      A success block.
 *   @param   failedBlock       A fail block.
 *   @discussion API to get access info for masterpass express checkout (Masterpass first request).
 */
- (void)executeMasterpass:(PaymentRequestPARAM *)paymentRequest successBlock:(onPaymentResp)successBlock failedBlock:(onErrorCB)failedBlock;

/**
 *   @method  loadCardView:(NSDictionary *)cardInfo fromViewController:(id)viewController successBlock:(onPaymentResp)successRequestCB failedBlock:(onErrorCB)failedCB
 *
 *   @param   cardView          A view for the sdk to load the card UI.
 *   @param   paymentRequest    A <code>PaymentRequestPARAM</code> object.
 *                              include PaymentID, ServiceID, CustEmail, Password, CustPhone, TokenType, Token
 *   @param   viewController    A viewController for the sdk to refer.
 *   @param   failedBlock       A fail block.
 *
 *   @discussion    load the card view to desire view give by merchant.
 */
- (void)loadCardView:(UIView *)cardView paymentRequest:(PaymentRequestPARAM *)paymentRequest fromViewController:(id)viewController failedBlock:(onErrorCB)failedBlock;

/**
 *   @method  fetchSOPTokenSuccess:(onSOPTokenResp)successBlock failedBlock:(onErrorCB)failedBlock
 *
 *   @param   successBlock      A success block. Return one time token (token type: SOP)
 *   @param   failedBlock       A fail block.
 *
 *   @discussion    Fetch SOP Token (temporary token for optimize module).
 */
- (void)fetchSOPTokenSuccess:(onSOPTokenResp)successBlock failed:(onErrorCB)failedBlock;

/**
 *   @method  fetchSOPBankListWithPaymentRequest:(PaymentRequestPARAM *)paymentRequest success:(onSOPTokenResp)successBlock failedBlock:(onErrorCB)failedBlock
 *
 *   @param   paymentRequest    A <code>PaymentRequestPARAM</code> object.
 *                              include PaymentID, ServiceID, Currency
 *   @param   successBlock      A success block. Return one time token (token type: SOP)
 *   @param   failedBlock       A fail block.
 *
 *   @discussion    Fetch SOP Token (temporary token for optimize module).
 */
- (void)fetchSOPBankListWithPaymentRequest:(PaymentRequestPARAM *)paymentRequest success:(onSOPBLResp)successBlock failed:(onErrorCB)failedBlock;
@end
