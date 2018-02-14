#!/usr/bin/env bash

function android {
    type=$1

    rm -rf tmp*
    ./SalesforceMobileSDK-Package/test/test_force.js --os=android --apptype="$type"

    app_name="${type}_androidApp"
    cd tmp*
    cd "$app_name"

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd platforms/android/
    elif [ "$type" == "react_native" ]; then
        npm start&
        cd android
    fi

    if [ "$type" == "native" ]; then
        package_name="com.salesforce.native_java"
    else
        package_name="com.salesforce.$type"
    fi

    echo "                                                                             "
    echo "                                                                             "
    echo "*****************************************************************************"
    echo "*                                                                           *"
    echo "*                            Build                                          *"
    echo "*                                                                           *"
    echo "*****************************************************************************"
    echo "                                                                             "
    echo "                                                                             "

    # adb install app
    adb install app/build/outputs/apk/debug/app-debug.apk

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd ../../../../Android/
    elif [ "$type" == "react_native" ]; then
        cd ../../../Android/
    else
        cd ../../Android/
    fi

    echo "                                                                             "
    echo "                                                                             "
    echo "*****************************************************************************"
    echo "*                                                                           *"
    echo "*                           UI TEST                                         *"
    echo "*                                                                           *"
    echo "*****************************************************************************"
    echo "                                                                             "
    echo "                                                                             "

    # Run Tests
    ./gradlew -Pandroid.testInstrumentationRunnerArguments.packageName="${package_name}" connectedAndroidTest
    cd ../
}

function ios {
    type=$1
    rm -rf tmp*
    ./SalesforceMobileSDK-Package/test/test_force.js --os=ios --apptype="${type}"

    app_name="${type}_iosApp"
    cd tmp*
    cd "${app_name}"

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd platforms/ios/
        bundle_name="com.salesforce.${type}"
    elif [ "$type" == "react_native" ]; then
        npm start&
        bundle_name="com.salesforce.react_native.react-nativeiosApp"
    else
        bundle_name="com.salesforce.${app_name}"
    fi

    # FIX THIS
    if [ "$type" == "native" ]; then
        bundle_name="com.salesforce.native-iosApp"
    elif [ "$type" == "native_swift" ]; then
        bundle_name="com.salesforce.native-swift-iosApp"
    fi


    echo "                                                                             "
    echo "                                                                             "
    echo "*****************************************************************************"
    echo "*                                                                           *"
    echo "*                            Build                                          *"
    echo "*                                                                           *"
    echo "*****************************************************************************"
    echo "                                                                             "
    echo "                                                                             "

    # Build and install App
    mkdir DerivedData

    if [ "$type" == "react_native" ]; then
        react-native bundle --platform ios --dev false --entry-file index.js --bundle-output ios/main.jsbundle
        react-native run-ios
    else
        xcodebuild build -scheme "${app_name}" -workspace "${app_name}".xcworkspace -derivedDataPath ./DerivedData CODE_SIGN_IDENTITY="" -destination 'platform=iOS Simulator,name=iPhone 8,OS=11.2' CODE_SIGNING_REQUIRED=NO
        ios-sim install ./DerivedData/Build/Products/Debug-iphonesimulator/"${app_name}".app --devicetypeid "iPhone-8, 11.2" --exit
    fi
    sleep 2m

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd ../../../../iOS/
    elif [ "$type" == "react_native" ]; then
        cd ../../iOS/
    else
        cd ../../iOS/
    fi

    echo "                                                                             "
    echo "                                                                             "
    echo "*****************************************************************************"
    echo "*                                                                           *"
    echo "*                           UI TEST                                         *"
    echo "*                                                                           *"
    echo "*****************************************************************************"
    echo "                                                                             "
    echo "                                                                             "


    # Run Tests
    xcodebuild -scheme MobileSDKUITest -sdk iphonesimulator -destination 'platform=iOS Simulator,name=iPhone 8,OS=11.2' TEST_APP_BUNDLE=${bundle_name} test
    cd ../
}