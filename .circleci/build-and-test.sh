#!/usr/bin/env bash

function android {
    type=$1

    rm -rf tmp*
    ./SalesforceMobileSDK-Package/test/test_force.js --os=android --apptype="$type"

    app_name="${type}androidApp"
    cd tmp*
    cd "$app_name"

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd platforms/android/
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

    ./gradlew assemble
    # adb install app
    adb install app/build/outputs/apk/debug/app-debug.apk

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd ../../../../Android/
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
    adb shell am instrument -w -r -e debug false -e packageName "${package_name}" com.salesforce.mobilesdk.mobilesdkuitest.test/android.support.test.runner.AndroidJUnitRunner
    cd ../
}

function ios {
    type=$1
    rm -rf tmp*
    ./SalesforceMobileSDK-Package/test/test_force.js --os=ios --apptype="${type}"

    # FIX THIS
    app_name="${type}iosApp"
    cd tmp*
    cd "${app_name}"

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd platforms/ios/
        bundle_name="com.salesforce.${type}"
    else
        bundle_name="com.salesforce.${app_name}"
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
    xcodebuild build -scheme "${app_name}" -derivedDataPath ./DerivedData CODE_SIGN_IDENTITY="" -destination 'platform=iOS Simulator,name=iPhone 8,OS=11.2' CODE_SIGNING_REQUIRED=NO
    ios-sim install ./DerivedData/Build/Products/Debug-iphonesimulator/"${app_name}".app --devicetypeid "iPhone-8, 11.2" --exit
    sleep 2m

    if [ "$type" == "hybrid_local" ] || [ "$type" == "hybrid_remote" ]; then
        cd ../../../../iOS/
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

# xcodebuild -scheme MobileSDKUITest -sdk iphonesimulator -destination 'platform=iOS Simulator,name=iPhone 8,OS=11.2' TEST_APP_BUNDLE=com.mycompany.hybrid_localtestApp test