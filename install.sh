#!/usr/bin/env bash

git submodule init
git submodule sync
git submodule update --init --recursive

sudo npm install -g ios-sim
sudo npm install -g cordova@7.0.0

pwd
ls

cd SalesforceMobileSDK-Package

pwd
ls

git checkout packagename
./install.js
