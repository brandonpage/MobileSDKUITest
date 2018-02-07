#!/usr/bin/env bash

git submodule init
git submodule sync
git submodule update --init --recursive

cd SalesforceMobileSDK-Package && install.js
npm install ios-sim -g