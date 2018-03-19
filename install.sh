#!/usr/bin/env bash

if [[ "$OSTYPE" == "linux-gnu" ]]; then
    sudo apt-get update
    sudo apt-get install libqt5widgets5
fi

git clone --branch dev --single-branch --depth 1 https://github.com/forcedotcom/SalesforceMobileSDK-Package.git

if [ "$CIRCLECI" ]; then
    sudo npm install -g
else
    npm install -g
fi

cordova telemetry off
gem install --no-document fastlane