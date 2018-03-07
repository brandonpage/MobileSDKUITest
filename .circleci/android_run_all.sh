#!/usr/bin/env bash

gem install --no-document fastlane
./install.sh

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*               Native                      *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane android type:native

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*               Kotlin                      *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane android type:native_kotlin

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*             Hybrid Local                  *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane android type:hybrid_local

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*             Hybrid Remote                 *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane android type:hybrid_remote

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*             React  Native                 *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane android type:react_native