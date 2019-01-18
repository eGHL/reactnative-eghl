package com.eghl.module;

import com.eghl.sdk.params.Params;
import com.eghl.sdk.params.PaymentParams;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arifjusoh on 19/02/2018.
 */

public class PaymentInfo {
    /**
     * Mandatory fields
     */
    @SerializedName(Params.TRANSACTION_TYPE)
    private String transactionType = "";

    @SerializedName(Params.PAYMENT_METHOD)
    private String paymentMethod = "";

    @SerializedName(Params.SERVICE_ID)
    private String serviceId = "";

    @SerializedName(Params.PAYMENT_ID)
    private String paymentId = "";

    @SerializedName(Params.ORDER_NUMBER)
    private String orderNumber = "";

    @SerializedName(Params.PAYMENT_DESC)
    private String paymentDesc = "";

    @SerializedName(Params.MERCHANT_RETURN_URL)
    private String merchantReturnURL = "";

    @SerializedName(Params.AMOUNT)
    private String amount = "";

    @SerializedName(Params.CURRENCY_CODE)
    private String currencyCode = "";

    @SerializedName(Params.PASSWORD)
    private String password = "";

    @SerializedName("prod")
    private Boolean isProduction = true;

    /**
     * Optional Fields
     */
    @SerializedName(Params.CUST_IP)
    private String custIP = "";

    @SerializedName(Params.CUST_NAME)
    private String custName = "";

    @SerializedName(Params.CUST_EMAIL)
    private String custEmail = "";

    @SerializedName(Params.CUST_PHONE)
    private String custPhone = "";

    @SerializedName(Params.B4_TAX_AMT)
    private String beforeTaxAmount = "";

    @SerializedName(Params.TAX_AMT)
    private String taxAmount = "";

    @SerializedName(Params.MERCHANT_NAME)
    private String merchantName = "";

    @SerializedName(Params.CUST_MAC)
    private String custMAC = "";

    @SerializedName(Params.MERCHANT_CALLBACK_URL)
    private String merchantCallbackURL = "";

    @SerializedName(Params.MERCHANT_APPROVAL_URL)
    private String merchantApprovalURL = "";

    @SerializedName(Params.MERCHANT_UNAPPROVAL_URL)
    private String merchantUnApprovalURL = "";

    @SerializedName(Params.LANGUAGE_CODE)
    private String languageCode = "";

    @SerializedName(Params.PAGE_TIMEOUT)
    private String pageTimeout = "";

    @SerializedName(Params.PAYMENT_TIMEOUT)
    private int paymentTimeout = -1;

    @SerializedName(Params.CARD_HOLDER)
    private String cardHolder = "";

    @SerializedName(Params.CARD_NO)
    private String cardNo = "";

    @SerializedName(Params.CARD_EXP)
    private String cardExp = "";

    @SerializedName(Params.CARD_CVV2)
    private String cardCVV = "";

    @SerializedName(Params.ISSUING_BANK)
    private String issuingBank = "";

    @SerializedName(Params.BILL_ADDR)
    private String billAddress = "";

    @SerializedName(Params.BILL_POSTAL)
    private String billPostal = "";

    @SerializedName(Params.BILL_CITY)
    private String billCity = "";

    @SerializedName(Params.BILL_REGION)
    private String billRegion = "";

    @SerializedName(Params.BILL_COUNTRY)
    private String billCountry = "";

    @SerializedName(Params.SHIP_ADDR)
    private String shipAddress = "";

    @SerializedName(Params.SHIP_POSTAL)
    private String shipPostal = "";

    @SerializedName(Params.SHIP_CITY)
    private String shipCity = "";

    @SerializedName(Params.SHIP_REGION)
    private String shipRegion = "";

    @SerializedName(Params.SHIP_COUNTRY)
    private String shipCountry = "";

    @SerializedName(Params.SESSION_ID)
    private String sessionId = "";

    @SerializedName(Params.TOKEN_TYPE)
    private String tokenType = "";

    /* Conditional */
    @SerializedName(Params.TOKEN)
    private String token = "";

    @SerializedName(Params.PARAM6)
    private String param6 = "";

    @SerializedName(Params.PARAM7)
    private String param7 = "";

    @SerializedName(Params.EPP_MONTH)
    private String eppMonth = "";

    @SerializedName(Params.PROMO_CODE)
    private String promoCode = "";

    @SerializedName(Params.TRIGGER_RETURN_URL)
    private boolean triggerReturnURL = false;

    @SerializedName(Params.QUERY_COUNT)
    private int queryCount = 6;

    /**
     * Setter
     */
    public void setProduction(Boolean production) {
        isProduction = production;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }

    public void setMerchantReturnURL(String merchantReturnURL) {
        this.merchantReturnURL = merchantReturnURL;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCustIP(String custIP) {
        this.custIP = custIP;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public void setBeforeTaxAmount(String beforeTaxAmount) {
        this.beforeTaxAmount = beforeTaxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setCustMAC(String custMAC) {
        this.custMAC = custMAC;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setPageTimeout(String pageTimeout) {
        this.pageTimeout = pageTimeout;
    }

    public void setPaymentTimeout(int paymentTimeout) {
        this.paymentTimeout = paymentTimeout;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardExp(String cardExp) {
        this.cardExp = cardExp;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public void setBillPostal(String billPostal) {
        this.billPostal = billPostal;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public void setBillRegion(String billRegion) {
        this.billRegion = billRegion;
    }

    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public void setShipPostal(String shipPostal) {
        this.shipPostal = shipPostal;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }

    public void setParam7(String param7) {
        this.param7 = param7;
    }

    public void setEppMonth(String eppMonth) {
        this.eppMonth = eppMonth;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setTriggerReturnURL(boolean triggerReturnURL) {
        this.triggerReturnURL = triggerReturnURL;
    }

    public void setMerchantCallbackURL(String merchantCallbackURL) {
        this.merchantCallbackURL = merchantCallbackURL;
    }

    public void setMerchantApprovalURL(String merchantApprovalURL) {
        this.merchantApprovalURL = merchantApprovalURL;
    }

    public void setMerchantUnApprovalURL(String merchantUnApprovalURL) {
        this.merchantUnApprovalURL = merchantUnApprovalURL;
    }

    public void setQueryCount(int queryCount) {
        this.queryCount = queryCount;
    }

    public Boolean getProduction() {
        return isProduction;
    }

    /**
     * Getter
     */


    public String getTransactionType() {
        return transactionType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getPaymentDesc() {
        return paymentDesc;
    }

    public String getMerchantReturnURL() {
        return merchantReturnURL;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getPassword() {
        return password;
    }

    public String getCustIP() {
        return custIP;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public String getBeforeTaxAmount() {
        return beforeTaxAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getCustMAC() {
        return custMAC;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getPageTimeout() {
        return pageTimeout;
    }

    public int getPaymentTimeout() {
        return paymentTimeout;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getCardExp() {
        return cardExp;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public String getBillPostal() {
        return billPostal;
    }

    public String getBillCity() {
        return billCity;
    }

    public String getBillRegion() {
        return billRegion;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public String getShipPostal() {
        return shipPostal;
    }

    public String getShipCity() {
        return shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getToken() {
        return token;
    }

    public String getParam6() {
        return param6;
    }

    public String getParam7() {
        return param7;
    }

    public String getEppMonth() {
        return eppMonth;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public boolean isTriggerReturnURL() {
        return triggerReturnURL;
    }

    public String getMerchantCallbackURL() {
        return merchantCallbackURL;
    }

    public String getMerchantApprovalURL() {
        return merchantApprovalURL;
    }

    public String getMerchantUnApprovalURL() {
        return merchantUnApprovalURL;
    }

    public int getQueryCount() {
        return queryCount;
    }
}
