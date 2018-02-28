/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  Button,
  Alert,
  View,
  NativeEventEmitter,
  NativeModules
} from 'react-native';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});
if (Platform.OS === 'ios') {
  const eGHLReturnEmitter = new NativeEventEmitter(NativeModules.EGHLReturn);
    var subscription = eGHLReturnEmitter.addListener(
    'eGHLReturn', function(returnObj) {
      console.log(returnObj.status);
      if (returnObj.status) {
        alert(JSON.stringify(returnObj.result));
      } else {
        alert(JSON.stringify(returnObj.message));
      }
  });

} 

type Props = {};
export default class App extends Component<Props> {
  // onReturn = (result) => {
  //   alert("Status:"+result.status);
  // }
// (sdkReturn) => console.log(sdkReturn.PaymentID)
  componentWillUnmount(){
    if (!subscription) {
      subscription.remove();
    }
  }

  onPressPay(){
    var eGHL = NativeModules.EGHL;

    var paymentId = "DEMO" + new Date().getTime();

    if (eGHL) {
      var paymentInfo = {
        "Amount": "1.00",
        "PymtMethod": "ANY",
        "CustEmail": "arif.jusoh@ghl.com",
        "MerchantCallBackURL": "http://arifall.my/eGHL/mp_callback.php",
        "PaymentDesc": "Testing Payment",
        "CurrencyCode": "MYR",
        "MerchantReturnURL": "SDK",
        "PaymentID": paymentId,
        "LanguageCode": "EN",
        "CustName": "Arif",
        "ServiceID": "SIT",
        "Password": "sit12345",
        "TransactionType": "SALE",
        "MerchantName": "eGHL Payment Testing",
        "OrderNumber": paymentId,
        "CustPhone": "0123456789",
        "PageTimeout":"600",
        "prod": false      
      }

      eGHL.execute(JSON.stringify(paymentInfo));
    } else {
      console.log(NativeModules);
    }
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit App.js
        </Text>
        <Text style={styles.instructions}>
          {instructions}
        </Text>
        <Button
        onPress={this.onPressPay}
        title="Pay"
        color="#841584"
        accessibilityLabel="Pay now"
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
