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
  DeviceEventEmitter,
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
  componentWillMount(){
    if (Platform.OS === 'android') {
      DeviceEventEmitter.addListener('eGHLReturn', function(e) {
        console.log(e);

        if (e.status) {
          var jsonObj = JSON.parse(e.json);
          console.log(jsonObj);

          setTimeout(function(){
            Alert.alert('Demo App', jsonObj["TxnMessage"], [{ text: 'OK' }]);
          },500);
        } else {
          setTimeout(function(){
            Alert.alert('Demo App', e.message, [{ text: 'OK' }]);
          },500);

        }
      });
    }
  }
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
        "MerchantCallBackURL": "http://....",

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
