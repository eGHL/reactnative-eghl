//
//  EGHLConstants.h
//  EGHLPayment
//
//  Created by Arif Jusoh on 10/04/2018.
//  Copyright © 2018 ghl. All rights reserved.
//
//---------------------------
#ifndef EGHLConstants_h
#define EGHLConstants_h
//---------------------------
#define EGHL_TRANSACTION_TYPE_SALE       @"SALE"
#define EGHL_TRANSACTION_TYPE_AUTH       @"AUTH"
#define EGHL_TRANSACTION_TYPE_QUERY      @"QUERY"
#define EGHL_TRANSACTION_TYPE_REVERSAL   @"RSALE"
#define EGHL_TRANSACTION_TYPE_REFUND     @"REFUND"
#define EGHL_TRANSACTION_TYPE_CAPTURE    @"CAPTURE"
#define EGHL_TRANSACTION_TYPE_SOP        @"SOP"
#define EGHL_TRANSACTION_TYPE_EMANDATE   @"EMANDATE01"

#define EGHL_PAYMENT_METHOD_ANY     @"ANY"
#define EGHL_PAYMENT_METHOD_CARD    @"CC"
#define EGHL_PAYMENT_METHOD_OTHERS  @"DD"

//---------------------------
#pragma mark - Setting key
//---------------------------
#define EGHL_SHOULD_TRIGGER_RETURN_URL  @"ShouldTriggerReturnURL"
#define EGHL_DEBUG_PAYMENT_URL          @"EGHLDebugPaymentURL"
#define EGHL_WEBVIEW_ZOOMING            @"EGHLWebviewZooming"
#define EGHL_QUERY_EMANDATE             @"EGHLQueryEMandate"

#define EGHL_NAV_BAR_BG_COLOR   @"NavigationBarColor"
#define EGHL_NAV_BAR_TEXT_COLOR   @"NavigationBarTextColor"
#define EGHL_NAV_BAR_TITLE_COLOR   @"NavigationBarTitleColor"
#define EGHL_CARD_PAGE_BG_COLOR @"CardPageBGColor"

#define EGHL_ENABLED_CARD_PAGE  @"enabledCardPage"
#define EGHL_CVV_OPTIONAL       @"cvvOptional"
#define EGHL_TOKENIZE_REQUIRED @"tokenizeRequired"

// https://bitbucket.org/eghl/ios/wiki/Home#markdown-header-scenario-3-redirectingloading-to-bank-page-etc-and-user-exit-sdk
#define EGHL_NUM_OF_REQUERY  @"numOfRequery" // default 12, call every 5 second(1 minute in total)
#define EGHL_HIDE_DEFAULT_CARD_TYPE @"hideDefaultCardType"

//---------------------------
#pragma mark Card Setting key
//---------------------------
#define EGHL_CARD_SETTING   @"EGHL_CARD_SETTING"
#define EGHL_CARD_HOLDER    @"EGHL_CARD_HOLDER"
#define EGHL_CARD_NUMBER    @"EGHL_CARD_NUMBER"
#define EGHL_CARD_EXPIRY    @"EGHL_CARD_EXPIRY"
#define EGHL_CARD_CVV       @"EGHL_CARD_CVV"
#define EGHL_CARD_TYPE      @"EGHL_CARD_TYPE"
#define EGHL_CARD_CUSTOM    @"EGHL_CARD_CUSTOM"
#define EGHL_CARD_CUSTOMER_CONSENT    @"EGHL_CARD_CUSTOMER_CONSENT"

#define EGHL_ATTRIBUTE_SECTION      @"eghlAttributeSection"
#define EGHL_ATTRIBUTE_ROW_HEIGHT   @"eghlAttributeRowHeight"
#define EGHL_ATTRIBUTE_CELL_XIB     @"eghlAttributeCellXib"
#define EGHL_ATTRIBUTE_TITlE        @"eghlAttributeTitle"
#define EGHL_ATTRIBUTE_PLACEHOLDER  @"eghlAttributePlaceholder"
#define EGHL_ATTRIBUTE_CARD_EXP_TAG_MONTH  @"eghlAttributeCardExpTagMonth"
#define EGHL_ATTRIBUTE_CARD_EXP_TAG_SEPERATOR  @"eghlAttributeCardExpTagSeperator"
#define EGHL_ATTRIBUTE_CARD_EXP_TAG_YEAR  @"eghlAttributeCardExpTagYear"

//---------------------------
#pragma mark Typedef enum
//---------------------------
typedef enum {
    EGHLCardPageErrorUnknown,
    EGHLCardPageErrorInvalidCardHolder,
    EGHLCardPageErrorInvalidCardNo,
    EGHLCardPageErrorInvalidCardCVV,
    EGHLCardPageErrorInvalidCardExp
} EGHLCardPageErrorType;

typedef enum {
    EGHLAPIErrorUnknown,
    EGHLAPIErrorSOPInvalidEmailOrPhone
} EGHLAPIErrorType;


typedef enum {
    EGHLTransactionStatusSuccess,
    EGHLTransactionStatusFailed,
    EGHLTransactionStatusPending
} EGHLTransactionStatusType;

//---------------------------
#endif /* EGHLConstants_h */
//---------------------------
