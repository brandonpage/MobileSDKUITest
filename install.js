#!/usr/bin/env node

var packageJson = require('./package.json')
var execSync = require('child_process').execSync;

console.log('Installing Packager');
var packagerDependency = 'salesforcemobilesdk-package';
var repoUrlWithBranch = packageJson.testAppDependencies[packagerDependency];
var parts = repoUrlWithBranch.split('#'), repoUrl = parts[0], branch = parts.length > 1 ? parts[1] : 'master';
execSync('git clone --branch ' + branch + ' --single-branch --depth 1 ' + repoUrl, {stdio:[0,1,2]});

console.log('Installing NPM Dependencies');
if(process.platform === 'linux') {
    execSync('sudo apt-get update');
    execSync('sudo apt-get install libqt5widgets5');
}
var sudoPrefix = (process.env.CIRCLECI) ? 'sudo' : '';
execSync(`npm install -g`, {stdio:[0,1,2]});
console.log('Force install Cordova...');
execSync(`${sudoPrefix} npm install -g cordova@7.0.0`, {stdio:[0,1,2]});
execSync('cordova telemetry off');
execSync('gem install --no-document fastlane');