#!/usr/bin/env bash

git submodule init
git submodule sync
git submodule update --init --recursive

sudo npm install -g ios-sim
sudo npm install -g cordova@7.0.0

cd SalesforceMobileSDK-Package
git checkout packagename
node ./install.js
