import {
    createStackNavigator,
    createDrawerNavigator,
    createBottomTabNavigator
} from "react-navigation";

import HomeScreen from "../HomeScreen";
import PdfCreateScreen from "../PdfCreateScreen";


const HomeStackNavigator = createStackNavigator( {
    HomeScreen: {
        screen: HomeScreen,
        navigationOptions: { header: null }
    },
    PdfCreateScreen: {
        screen: PdfCreateScreen,
        navigationOptions: { header: null }
    }
}, {
    initialRouteName: "HomeScreen"
}
);


//TODO: RootNavigator
//TODO: RootNavigator:createRootNavigator
export const createRootNavigator = (
    signedIn = false,
    screenName = "HomeNavigator"
) => {
    return createStackNavigator(
        {
            HomeNavigator: {
                screen: HomeStackNavigator,
                navigationOptions: { header: null }
            }
        },
        {
            initialRouteName: signedIn ? "HomeNavigator" : screenName
        }
    );
};