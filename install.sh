#!/usr/bin/env bash

git submodule init
git submodule sync
git submodule update --init --recursive

npm install -g ios-sim
npm install -g cordova@7.0.0

cd SalesforceMobileSDK-Package
git checkout packagename
node ./install.js
