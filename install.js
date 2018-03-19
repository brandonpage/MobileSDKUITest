#!/usr/bin/env node

var packageJson = require('./package.json')
var execSync = require('child_process').execSync;
var path = require('path');
var fs = require('fs');

console.log('Installing Packager');
var packagerDependency = 'salesforcemobilesdk-package';
var repoUrlWithBranch = packageJson.testAppDependencies[packagerDependency];
var parts = repoUrlWithBranch.split('#'), repoUrl = parts[0], branch = parts.length > 1 ? parts[1] : 'master';
execSync('git clone --branch ' + branch + ' --single-branch --depth 1 ' + repoUrl, {stdio:[0,1,2]});

console.log('Installing npm dependencies');
execSync('npm install -g', {stdio:[0,1,2]});
execSync('cordova telemetry off')
execSync('gem install --no-document fastlane')