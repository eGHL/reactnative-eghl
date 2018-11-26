//
//  RCTEghl.m
//  RCTEghl
//
//  Created by Arif Jusoh on 14/02/2018.
//  Copyright © 2018 GHL ePayments Sdn Bhd. All rights reserved.
//

#import "RCTEghl.h"
#import <React/RCTLog.h>
#import "EGHLPayment.h"
#import "ShowViewController.h"

#define validString(args) ([args isKindOfClass:[NSString class]] && [args length]>0)?args:@""
#define validBool(args) (args)?[args boolValue]:true

@implementation RCTEghl
// To export a module named
RCT_EXPORT_MODULE(EGHL);
RCT_EXPORT_METHOD(execute:(NSString *)paymentInfoJson)
{
    NSError *error;

    NSDictionary * responseDict = [NSJSONSerialization JSONObjectWithData:[paymentInfoJson dataUsingEncoding:NSUTF8StringEncoding] options:0 error:&error];
    
    if (error) {
        RCTLogInfo(@"Error in Parsing:%@ \nerror: %@", paymentInfoJson, error.localizedDescription);
    } else {
//        RCTLogInfo(@"Info received(Dict): %@", responseDict);
        [self processPayment:responseDict];
    }
}

- (void)processPayment:(NSDictionary *)info {
    if ([info[@"TransactionType"] isKindOfClass:[NSString class]]) {
        NSString * transactionType = info[@"TransactionType"];
       
        if ([[transactionType lowercaseString] isEqualToString:@"sale"]) {
            [self presentSaleVC:info];
        }
    }
}

- (NSString *)generateNewPaymentID {
    NSDateFormatter *df = [[NSDateFormatter alloc] init];
    [df setDateFormat:@"yyyyMMddHHmmss"];
    NSString * dateString = [df stringFromDate:[NSDate date]];
    
    int value =arc4random_uniform(9999 + 1);
    
    return [NSString stringWithFormat:@"AJ%@%d", dateString, value];
}

- (void)presentSaleVC:(NSDictionary *)info {
    PaymentRequestPARAM *paypram = [[PaymentRequestPARAM alloc] init];
    paypram.TransactionType = validString(info[@"TransactionType"]);
    
    paypram.CustEmail = validString(info[@"CustEmail"]);
    paypram.CustName = validString(info[@"CustName"]);
    paypram.MerchantReturnURL = validString(info[@"MerchantReturnURL"]);
    paypram.MerchantCallBackURL = validString(info[@"MerchantCallBackURL"]);
    paypram.CustPhone = validString(info[@"CustPhone"]);
    paypram.LanguageCode = validString(info[@"LanguageCode"]);
    paypram.PaymentDesc = validString(info[@"PaymentDesc"]);
    paypram.PageTimeout = validString(info[@"PageTimeout"]);
    paypram.PymtMethod = validString(info[@"PymtMethod"]);
    paypram.Amount = validString(info[@"Amount"]);
    paypram.CurrencyCode = validString(info[@"CurrencyCode"]);
    paypram.Token = validString(info[@"Token"]);
    paypram.TokenType = validString(info[@"TokenType"]);

    paypram.PaymentID = validString(info[@"PaymentID"]);
    paypram.OrderNumber = validString(info[@"OrderNumber"]);
    
    paypram.shouldTriggerReturnURL = validBool(info[@"shouldTriggerReturnURL"]) ;
    paypram.realHost = validBool(info[@"prod"]);

    paypram.MerchantName = validString(info[@"MerchantName"]);
    paypram.Password = validString(info[@"Password"]);
    paypram.ServiceID = validString(info[@"ServiceID"]);
    
    UIWindow *keyWindow = [[UIApplication sharedApplication] keyWindow];

    UIViewController *vc = keyWindow.rootViewController;
    dispatch_async(dispatch_get_main_queue(), ^{
//        RCTLogInfo(@"TransactionType: %@", [ShowViewController displayRequestParam:paypram]);

        ShowViewController * payVC = [[ShowViewController alloc] initWithValue:paypram];
        payVC.eghlpay = [[EGHLPayment alloc] init];
        
        UINavigationController * nav = [[UINavigationController alloc] initWithRootViewController:payVC];
        [vc presentViewController:nav animated:YES completion:nil];
    });
    // [[[[UIApplication sharedApplication] keyWindow] subviews] lastObject];
}
@end
