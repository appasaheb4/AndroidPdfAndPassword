/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';
import {
    SafeAreaView,
    StyleSheet,
    ScrollView,
    View,
    Text,
    NativeModules,
    Alert
} from 'react-native';

import { Button } from "native-base";

export default class PdfCreateScreen extends Component {


    click_pdfFile = async () => {
        try {
            var PdfPassword = NativeModules.PdfPassword;
            PdfPassword.pdfCreate( "For Share 4th", "qrcode4thshare1.png", ( err: any ) => { console.log( err ) }, ( msg: any ) => { console.log( msg ) } );
        } catch ( error ) {
            Alert.alert( error )
        }
    }

    render() {
        return (
            <SafeAreaView>
                <View style={ styles.contaniear }>
                    <Button onPress={ () => this.click_pdfFile() }>
                        <Text>pdf</Text>
                    </Button>
                </View>
            </SafeAreaView>
        );
    }
}

const styles = StyleSheet.create( {
    contaniear: {
        flex: 1
    }
} );

