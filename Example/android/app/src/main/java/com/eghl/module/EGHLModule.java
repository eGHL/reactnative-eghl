package com.eghl.module;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/**
 * eGHL's import
 */
import com.eghl.sdk.EGHL;
import com.eghl.sdk.params.PaymentParams;
/** End eGHL */


import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.gson.Gson;

import javax.annotation.Nullable;


/**
 * Created by arifjusoh on 18/02/2018.
 */

public class EGHLModule extends ReactContextBaseJavaModule {
    private static final String TAG = "EGHLModule";

    public static final String PROD_HOST = "https://securepay.e-ghl.com/IPG/Payment.aspx";
    public static final String TEST_HOST = "https://test2pay.ghl.com/IPGSG/Payment.aspx";

    private PaymentParams.Builder params;
    private EGHL eghl;

    public EGHLModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(mActivityResultListener);
        eghl = EGHL.getInstance();
    }

    @Override
    public String getName() {
        return "EGHL";
    }

    @ReactMethod
    public void execute(String paymentInfoJson) {
        PaymentInfo paymentInfo;
        try {
            paymentInfo = new Gson().fromJson(paymentInfoJson, PaymentInfo.class);

            if (paymentInfo.getTransactionType().equalsIgnoreCase("sale")) {
                PaymentParams.Builder params;
                params = new PaymentParams.Builder()
                        .setTransactionType(paymentInfo.getTransactionType())
                        .setPaymentMethod(paymentInfo.getPaymentMethod())
                        .setServiceId(paymentInfo.getServiceId())
                        .setPaymentId(paymentInfo.getPaymentId())
                        .setOrderNumber(paymentInfo.getOrderNumber())
                        .setPaymentDesc(paymentInfo.getPaymentDesc())
                        .setMerchantReturnUrl(paymentInfo.getMerchantReturnURL())
                        .setAmount(paymentInfo.getAmount())
                        .setCurrencyCode(paymentInfo.getCurrencyCode())
                        .setPassword(paymentInfo.getPassword())
                        .setPaymentGateway(paymentInfo.getProduction()?PROD_HOST:TEST_HOST)

                        .setCustIp(paymentInfo.getCustIP())
                        .setCustName(paymentInfo.getCustName())
                        .setCustEmail(paymentInfo.getCustEmail())
                        .setCustPhone(paymentInfo.getCustPhone())
                        .setB4TaxAmt(paymentInfo.getBeforeTaxAmount())
                        .setTaxAmt(paymentInfo.getTaxAmount())
                        .setMerchantName(paymentInfo.getMerchantName())
                        .setCustMac(paymentInfo.getCustMAC())
                        .setMerchantCallbackUrl(paymentInfo.getMerchantCallbackURL())
                        .setMerchantApprovalUrl(paymentInfo.getMerchantApprovalURL())
                        .setMerchantUnapprovalUrl(paymentInfo.getMerchantUnApprovalURL())
                        .setLanguageCode(paymentInfo.getLanguageCode())
                        .setPageTimeout(paymentInfo.getPageTimeout())
                        .setPaymentTimeout(paymentInfo.getPaymentTimeout())
                        .setCardHolder(paymentInfo.getCardHolder())
                        .setCardNo(paymentInfo.getCardNo())
                        .setCardExp(paymentInfo.getCardExp())
                        .setCardCvv2(paymentInfo.getCardCVV())
                        .setIssuingBank(paymentInfo.getIssuingBank())
                        .setBillAddr(paymentInfo.getBillAddress())
                        .setBillPostal(paymentInfo.getBillPostal())
                        .setBillCity(paymentInfo.getBillCity())
                        .setBillRegion(paymentInfo.getBillRegion())
                        .setBillCountry(paymentInfo.getBillCountry())
                        .setShipAddr(paymentInfo.getShipAddress())
                        .setShipPostal(paymentInfo.getShipPostal())
                        .setShipCity(paymentInfo.getShipCity())
                        .setShipRegion(paymentInfo.getShipRegion())
                        .setShipCountry(paymentInfo.getShipCountry())
                        .setSessionId(paymentInfo.getSessionId())
                        .setTokenType(paymentInfo.getTokenType())
                        .setToken(paymentInfo.getToken())
                        .setParam6(paymentInfo.getParam6())
                        .setParam7(paymentInfo.getParam7())
                        .setEppMonth(paymentInfo.getEppMonth())
                        .setPromoCode(paymentInfo.getPromoCode())
                        .setTriggerReturnURL(paymentInfo.isTriggerReturnURL());

                Bundle paymentParams = params.build();
                eghl.executePayment(paymentParams, getCurrentActivity());
            }
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getReactApplicationContext());
            builder.setTitle("Invalid Payment Info");
            builder.setMessage("Unable to read given payment info");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private final ActivityEventListener mActivityResultListener = new BaseActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            if (requestCode == EGHL.REQUEST_PAYMENT && intent != null) {
                String message = intent.getStringExtra(EGHL.TXN_MESSAGE);

                WritableMap map = Arguments.createMap();

                if (!TextUtils.isEmpty(intent.getStringExtra(EGHL.RAW_RESPONSE))) {
                    map.putString("json", intent.getStringExtra(EGHL.RAW_RESPONSE));
                }

                map.putInt("resultCode", resultCode);

                switch (resultCode) {
                    case EGHL.TRANSACTION_SUCCESS:
                        map.putBoolean("status", true);
                        break;

                    case EGHL.TRANSACTION_FAILED:
                        map.putBoolean("status", false);

                        if (message != null) {
                            Log.d(TAG, "onActivityResult: payment failure or cancelled '"+message+"'");
                            map.putString("message", message);
                        } else {
                            Log.d(TAG, "onActivityResult: payment failure;" + resultCode);
                            map.putString("message", "(" + resultCode + ")");
                        }
                        break;

                    default:
                        Log.d(TAG, "onActivityResult: " + resultCode);
                        map.putBoolean("status", false);

                        if (message != null) {
                            map.putString("message", message);
                        } else {
                            map.putString("message", "(" + resultCode + ")");
                        }
                        break;
                }

                sendEvent(getReactApplicationContext(), "eGHLReturn", map);

            }
        }
    };
}
