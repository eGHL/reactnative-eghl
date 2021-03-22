//
//  EghlStaticLibrary.m
//  EghlStaticLibrary
//
//  Created by Arif Jusoh on 14/02/2018.
//  Copyright © 2018 GHL ePayments Sdn Bhd. All rights reserved.
//

#import "ShowViewController.h"
#import <React/RCTLog.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import "EghlStaticLibrary.h"

#define validString(args) ([args isKindOfClass:[NSString class]] && [args length]>0)?args:@""
#define validBool(args) (args)?[args boolValue]:true

@interface CallbackToJS: RCTEventEmitter <RCTBridgeModule>
@end
@implementation CallbackToJS
RCT_EXPORT_MODULE(EGHLReturn);

+ (id)allocWithZone:(NSZone *)zone {
    static CallbackToJS *sharedInstance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        sharedInstance = [super allocWithZone:zone];
    });
    return sharedInstance;
}

- (NSArray<NSString *> *)supportedEvents
{
    return @[@"eGHLReturn"];
}

- (void)postEvent:(NSDictionary *)info{
    [self sendEventWithName:@"eGHLReturn" body:info];
}
@end
@interface EghlStaticLibrary () <UIActionSheetDelegate>
@property (nonatomic) BOOL realHost;
@end
@implementation EghlStaticLibrary
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

- (void)setupServiceForRealHost:(BOOL)realHost {
    self.realHost = realHost;
}

- (void)presentSaleVC:(NSDictionary *)info {
    __block PaymentRequestPARAM *paypram = [[PaymentRequestPARAM alloc] init];
    
    [self setupServiceForRealHost:validBool(info[@"prod"])];
    
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
    paypram.IssuingBank = validString(info[@"IssuingBank"]);
    paypram.settingDict = @{
        EGHL_DEBUG_PAYMENT_URL: [NSNumber numberWithBool:!self.realHost],
    };
    paypram.PaymentID = validString(info[@"PaymentID"]);
    paypram.OrderNumber = validString(info[@"OrderNumber"]);

    paypram.MerchantName = validString(info[@"MerchantName"]);
    paypram.Password = validString(info[@"Password"]);
    paypram.ServiceID = validString(info[@"ServiceID"]);
    
    dispatch_async(dispatch_get_main_queue(), ^{
        UIWindow *keyWindow = [[UIApplication sharedApplication] keyWindow];

        UIViewController *vc = keyWindow.rootViewController;

        EGHLPayment * eghlpay = [[EGHLPayment alloc]init];
        [eghlpay execute:paypram fromViewController:vc successBlock:^(PaymentRespPARAM *responseData) {
//            NSLog(@"\n%@", [ShowViewController displayResponseParam:responseData]);
            
            NSString * resultString;
            if ([responseData.mpLightboxError isKindOfClass:[NSDictionary class]]) {
                resultString = [NSString stringWithFormat:@"%@", responseData.mpLightboxError];
            } else {
                resultString = [ShowViewController displayResponseParam:responseData];
            }
            
            CallbackToJS *callback = [CallbackToJS allocWithZone: nil];
             NSMutableDictionary * resultMutDict = [@{@"status":@YES} mutableCopy];
            
             // serialize jsonstring to JSON
             NSString *jsonString = [ShowViewController displayResponseParam: responseData];
            NSData *jsonData = [jsonString dataUsingEncoding:NSUTF8StringEncoding];
            id jsonResult = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:nil];
            resultMutDict[@"result"] = jsonResult;
             
             [callback postEvent:resultMutDict];
        
        } failedBlock:^(NSString *errorCode, NSString *errorData, NSError *error) {
            NSLog(@"errordata:%@ (%@)", errorData, errorCode);
            
            if (error) {
                NSString * urlstring = [error userInfo][@"NSErrorFailingURLKey"];
                if (urlstring) {
                    NSLog(@"NSErrorFailingURLKey:%@",urlstring);
                }
            }
            
            CallbackToJS *callback = [CallbackToJS allocWithZone: nil];
            
            [callback postEvent:@{
                                  @"status":@NO,
                                  @"message":errorData
                                  }];
        }];
    });

}
@end
