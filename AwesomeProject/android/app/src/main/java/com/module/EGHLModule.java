package com.module;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * eGHL's import
 */
import com.eghl.sdk.EGHL;
import com.eghl.sdk.params.PaymentParams;
/** End eGHL */


import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

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
                        .setPaymentType(paymentInfo.getPaymentType())
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
                .setQueryCount(paymentInfo.getQueryCount())

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
            WritableMap map = Arguments.createMap();

            if (requestCode == EGHL.REQUEST_PAYMENT && intent != null) {
                String rawResponse = "";

                if (!TextUtils.isEmpty(intent.getStringExtra(EGHL.RAW_RESPONSE))) {
                    rawResponse = intent.getStringExtra(EGHL.RAW_RESPONSE);
                }

                JSONObject jsonObject;

                try {
                    jsonObject = new JSONObject(rawResponse);
                    map.putMap("result", jsonToWritableMap(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                map.putBoolean("status", true);

            } else {
                map.putBoolean("status", false);
            }

            sendEvent(getReactApplicationContext(), "eGHLReturn", map);

        }
    };

    private WritableMap jsonToWritableMap(JSONObject jsonObject) throws JSONException {
        WritableMap writableMap = Arguments.createMap();
        Iterator iterator = jsonObject.keys();
        while(iterator.hasNext()) {
            String key = (String) iterator.next();
            Object value = jsonObject.get(key);
            if (value instanceof Float || value instanceof Double) {
                writableMap.putDouble(key, jsonObject.getDouble(key));
            } else if (value instanceof Integer) {
                writableMap.putInt(key, jsonObject.getInt(key));
            } else if (value instanceof String) {
                writableMap.putString(key, jsonObject.getString(key));
            } else if (value == JSONObject.NULL){
                writableMap.putNull(key);
            }
        }

        return writableMap;
    }
}
