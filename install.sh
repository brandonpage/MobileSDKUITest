#!/usr/bin/env bash

git submodule init
git submodule sync
git submodule update --init --recursive

cd SalesforceMobileSDK-Package && git checkout packagename && install.js
npm install -g ios-sim
npm install -g cordova@7.0.0
