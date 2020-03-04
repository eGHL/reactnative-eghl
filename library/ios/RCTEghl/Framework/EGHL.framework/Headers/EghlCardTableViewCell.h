//
//  EGHLCardCellTableViewCell.h
//  EGHL
//
//  Created by Arif Jusoh on 16/08/2018.
//  Copyright © 2018 GHL ePayments Sdn Bhd. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface EghlCardTableViewCell : UITableViewCell
@property (nonatomic, strong) IBOutlet UILabel * eghlTextLabel;
@property (nonatomic, strong) IBOutlet UITextField * eghlTextField;

@property (nonatomic, strong) IBOutlet UILabel * eghlOtherTextLabel;
@property (nonatomic, strong) IBOutlet UITextField * eghlOtherTextField;

@property (nonatomic, strong) IBOutlet UIButton * eghlCardExpiryButton;

@property (nonatomic, strong) IBOutlet UIImageView * eghlTypeVisaImageView;
@property (nonatomic, strong) IBOutlet UIImageView * eghlTypeMasterCardImageView;
@property (nonatomic, strong) IBOutlet UIImageView * eghlTypeAmexImageView;
@end

