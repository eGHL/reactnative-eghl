//
//  ShowViewController.h
//  eghl
//
//  Created by Arif Jusoh on 14/02/2018.
//  Copyright © 2018 GHL ePayments Sdn Bhd. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "EGHLPayment.h"


@interface ShowViewController : UIViewController

-(id)initWithValue:(PaymentRequestPARAM *)payment;
+ (NSString *)displayResponseParam:(PaymentRespPARAM *)respParam;
+ (NSString *)displayRequestParam:(PaymentRequestPARAM *)reqParam;
@property (nonatomic, strong) EGHLPayment *eghlpay;
@end
