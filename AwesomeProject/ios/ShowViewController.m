//
//  ShowViewController.m
//  eghl
//
//  Created by Arif Jusoh on 14/02/2018.
//  Copyright © 2018 GHL ePayments Sdn Bhd. All rights reserved.
//

#import "ShowViewController.h"
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <React/RCTLog.h>

typedef enum {
    tagAVExitPayment = 900
}tagForAlerView;

@interface ShowViewController () <UIAlertViewDelegate>
@property (strong, nonatomic) PaymentRequestPARAM *paypram;
@end

@implementation ShowViewController

-(id)initWithValue:(PaymentRequestPARAM *)payment
{
    self = [super init];
    
    if (self) {
        _paypram = payment;
    }
    
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)viewDidLayoutSubviews{
    [super viewDidLayoutSubviews];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
}


- (void)backButtonPressed:(id)sender{
    //UIAlertView * alertExit = [[UIAlertView alloc] initWithTitle:@"Are you sure you want to quit" message:@"Pressing BACK button will close and abandon the payment session." delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:@"Exit", nil];

   // [alertExit setTag:tagAVExitPayment];
   // [alertExit show];
}

#pragma mark - convenient method
+ (NSString *)displayResponseParam:(PaymentRespPARAM *)respParam {
    NSMutableString * message = [NSMutableString string];
    
    [message appendFormat:@"{"];
    
    for (NSString * key in @[@"Amount",@"AuthCode",@"BankRefNo",@"CardExp",@"CardHolder",@"CardNoMask",@"CardType",@"CurrencyCode",@"EPPMonth",@"EPP_YN",@"HashValue",@"HashValue2",@"IssuingBank",@"OrderNumber",@"PromoCode",@"PromoOriAmt",@"Param6",@"Param7",@"PaymentID",@"PymtMethod",@"QueryDesc",@"ServiceID",@"SessionID",@"SettleTAID",@"TID",@"TotalRefundAmount",@"Token",@"TokenType",@"TransactionType",@"TxnExists",@"TxnID",@"TxnMessage",@"TxnStatus",@"ReqToken",@"PairingToken",@"PreCheckoutId",@"Cards",@"mpLightboxError"]) {
        NSString * value = [respParam valueForKey:key];
        
        if ([value isKindOfClass:[NSString class]] && value.length>0) {
            [message appendFormat:@"\"%@\": \"%@\",", key,value];
        } else if ([value isKindOfClass:[NSArray class]] || [value isKindOfClass:[NSDictionary class]]) {
            NSError *error;
            NSData *jsonData = [NSJSONSerialization dataWithJSONObject:value
                                                               options:0 //NSJSONWritingPrettyPrinted for readability
                                                                 error:&error];
            
            if (jsonData) {
                NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
                jsonString = [jsonString stringByReplacingOccurrencesOfString:@"\\/" withString:@"/"];
                
                [message appendFormat:@"\"%@\": \"%@\",", key, jsonString];
            }
            
        }
    }
    
    [message appendFormat:@"}"];
    
    return message;
}
+ (NSString *)displayRequestParam:(PaymentRequestPARAM *)reqParam {
    NSMutableString * message = [NSMutableString string];
    
    for (NSString * key in @[@"realHost",@"Amount", @"PaymentID", @"OrderNumber", @"MerchantName", @"ServiceID", @"PymtMethod", @"MerchantReturnURL", @"CustEmail", @"Password", @"CustPhone", @"CurrencyCode", @"CustName", @"LanguageCode", @"PaymentDesc", @"PageTimeout", @"CustIP", @"MerchantApprovalURL", @"CustMAC", @"MerchantUnApprovalURL", @"CardHolder", @"CardNo", @"CardExp", @"CardCVV2", @"BillAddr", @"BillPostal", @"BillCity", @"BillRegion", @"BillCountry", @"ShipAddr", @"ShipPostal", @"ShipCity", @"ShipRegion", @"ShipCountry", @"TransactionType", @"TokenType", @"Token", @"SessionID", @"IssuingBank", @"MerchantCallBackURL", @"B4TaxAmt", @"TaxAmt", @"Param6", @"Param7", @"EPPMonth", @"PromoCode", @"ReqVerifier", @"PairingVerifier", @"CheckoutResourceURL", @"ReqToken", @"PairingToken", @"CardId", @"PreCheckoutId", @"mpLightboxParameter",  @"sdkTimeOut"]) {
        id value = [reqParam valueForKey:key];
        
        if (value) {
            if ([value isKindOfClass:[NSString class]] || [value isKindOfClass:[NSArray class]]) {
                if ([value length]>0) {
                    [message appendFormat:@"%@: %@\n", key, value];
                }
            } else if ([value isKindOfClass:[NSNumber class]]) {
                [message appendFormat:@"%@: %d\n", key, [value intValue]] ;
            }
        }
    }
    return message;
}

@end
