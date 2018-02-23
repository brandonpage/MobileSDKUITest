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
fastlane iOS type:native

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*               Swift                       *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane iOS type:native_swift

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*             Hybrid Local                  *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane iOS type:hybrid_local

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*             Hybrid Remote                 *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane iOS type:hybrid_remote

echo ""
echo ""
echo "*********************************************"
echo "*                                           *"
echo "*             React  Native                 *"
echo "*                                           *"
echo "*********************************************"
echo ""
echo ""
fastlane iOS type:react_native
