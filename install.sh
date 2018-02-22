#!/usr/bin/env bash

git submodule init
git submodule sync
git submodule update --init --recursive

sudo apt-get update
sudo apt-get install libqt5widgets5
sudo npm install -g shelljs@0.7.0
sudo npm install -g cordova@7.0.0
cordova telemetry off

cd SalesforceMobileSDK-Package
git checkout packagename
node ./install.js
