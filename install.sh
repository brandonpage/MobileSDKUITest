#!/usr/bin/env bash

if [[ "$OSTYPE" == "linux-gnu" ]]; then
    sudo apt-get update
    sudo apt-get install libqt5widgets5
fi

git clone --branch dev --single-branch --depth 1 https://github.com/forcedotcom/SalesforceMobileSDK-Package.git
if [[ -v "CIRCLECI" ]]; then
    sudo npm install -g shelljs@0.7.0
    sudo npm install -g cordova@7.0.0
    sudo npm install -g sfdx-cli
else
    npm install -g shelljs@0.7.0
    npm install -g cordova@7.0.0
    npm install -g sfdx-cli
fi

cordova telemetry off
gem install --no-document fastlane