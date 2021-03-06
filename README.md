eGHL React Native
=============================
 
Example of React Native integration with the eGHL SDK.


Linking Libraries
------------

### iOS

Refer [here](https://github.com/eGHL/reactnative-eghl/wiki/ios-Linking-libraries) on how to link ios sdk

See also:

- http://facebook.github.io/react-native/docs/linking-libraries-ios.html

### Android 

Refer [here](https://github.com/eGHL/reactnative-eghl/wiki/Android---Linking-libraries) on how to link android sdk

Usage
-----

### Request payment

Import Native Modules

```
import {
  NativeModules
} from 'react-native';
```

Pass payment info as json string to eGHL via `execute` method

```javascript
    var eGHL = NativeModules.EGHL;

    var paymentId = "DEMO" + new Date().getTime();

    if (eGHL) {
      var paymentInfo = {
        "TransactionType": "SALE",
        "Amount": "1.00",
        "CurrencyCode": "MYR",
        "PaymentID": paymentId,
        "OrderNumber": paymentId,
        "PaymentDesc": "Testing Payment",
        "PymtMethod": "ANY",

        "CustEmail": "somebody@someone.com",
        "CustName": "Somebody",
        "CustPhone": "0123456789",

        "MerchantReturnURL": "SDK",
        "MerchantCallBackURL": "http:// ...",

        "ServiceID": "SIT",
        "Password": "sit12345",

        "LanguageCode": "EN",
        "PageTimeout":"600",
        "prod": false      
      }

      eGHL.execute(JSON.stringify(paymentInfo));
    } else {
      console.log(NativeModules);
    }
```

Getting result
-------------

### Android

```javascript
componentWillMount(){
    if (Platform.OS === 'android') {
      DeviceEventEmitter.addListener('eGHLReturn', function(returnObj) {
        if (returnObj.status && typeof returnObj.result != 'undefined') {
        // successfully fetch
        alert(JSON.stringify(returnObj));
      } else {
        // error in linking
        alert(JSON.stringify(returnObj));
      }
    });
    }
  }
```

### iOS

```javascript
if (Platform.OS === 'ios') {
  const eGHLReturnEmitter = new NativeEventEmitter(NativeModules.EGHLReturn);
  var subscription = eGHLReturnEmitter.addListener(
    'eGHLReturn', function(returnObj) {
      if (returnObj.status && typeof returnObj.result != 'undefined') {
        // successfully fetch
        alert(JSON.stringify(returnObj));
      } else {
        // error in linking
        alert(JSON.stringify(returnObj));
      }
    });

} 
```

Remember to remove notification

```javascript
componentWillUnmount(){
    if (subscription) {
      subscription.remove();
    }
}
```
