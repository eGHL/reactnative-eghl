//
//  ShowViewController.h
//  eghl
//
//  Created by mac on 15/1/27.
//  Copyright (c) 2015年 eghl. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "EGHLPayment.h"


@interface ShowViewController : UIViewController

-(id)initWithValue:(PaymentRequestPARAM *)payment;
+ (NSString *)displayResponseParam:(PaymentRespPARAM *)respParam;
+ (NSString *)displayRequestParam:(PaymentRequestPARAM *)reqParam;
@property (nonatomic, strong) EGHLPayment *eghlpay;
@end
