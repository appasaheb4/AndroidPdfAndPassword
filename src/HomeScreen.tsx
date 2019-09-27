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
    Alert,
    View,
    Text,
    PermissionsAndroid
} from 'react-native';

import { Button } from "native-base";

export default class HomeScreen extends Component {

    _isContains( json: any, value: any ) {
        try {
            let contains = false;
            Object.keys( json ).some( key => {
                contains = typeof json[ key ] === 'object' ? this._isContains( json[ key ], value ) : json[ key ] === value;
                return contains;
            } );
            return contains;
        } catch ( error ) {
            Alert.alert( error )
        }
    }


    click_Createpdf = async () => {
        try {
            const grantedWrite = await PermissionsAndroid.requestMultiple( [
                PermissionsAndroid.PERMISSIONS.WRITE_EXTERNAL_STORAGE,
                PermissionsAndroid.PERMISSIONS.READ_EXTERNAL_STORAGE,
            ] );
            let flat_Perm = this._isContains( grantedWrite, "granted" );
            if ( flat_Perm ) {
                this.props.navigation.push( "PdfCreateScreen" );
            }
        } catch ( error ) {
            Alert.alert( error )
        }
    }


    render() {
        return (
            <SafeAreaView>
                <View style={ styles.contaniear }>
                    <Button onPress={ () => this.click_Createpdf() }>
                        <Text>Permission</Text>
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

