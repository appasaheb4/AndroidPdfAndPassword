/**
 * @format
 */
import React from "react";
import { AppRegistry } from 'react-native';
import App from './App';
import { name as appName } from './app.json';
import { createAppContainer } from "react-navigation";
import { createRootNavigator } from "PdfQrCodePass/src/app/router";

// export default class PdfQrCodePass extends React.Component
// {
//     render ()
//     {
//         const Layout = createRootNavigator(
//             true,
//             "HomeNavigator"
//         );
//         const AppContainer = createAppContainer( Layout );

//     }
// }

const Layout = createRootNavigator(
    true,
    "HomeNavigator"
);
const AppContainer = createAppContainer( Layout );

AppRegistry.registerComponent( appName, () => AppContainer );
