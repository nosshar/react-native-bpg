
# react-native-bpg

## Getting started

`$ npm install react-native-bpg --save`

### Mostly automatic installation

`$ react-native link react-native-bpg`

### Manual installation

NDK should be installed. This can be done with SDK manager.

Project should contain ```./android/local.properties``` with correct paths to SDK/NDK (it is possible to set up environment variables). 

Windows example:
```
sdk.dir=D\:\\Development\\_SDK\\AndroidSDK\\AndroidSDK
ndk.dir=D\:\\Development\\_SDK\\AndroidSDK\\ndk-bundle
```

_**There will be the warnings during the native libraries build phase.**_

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeBpgPackage;` to the imports at the top of the file
  - Add `new RNReactNativeBpgPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-bpg'
  	project(':react-native-bpg').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-bpg/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-bpg')
  	```

#### iOS

Not implemented for iOS

#### Windows

Not implemented for Windows


## Usage

Example project can be found in link section

## Links

[Thank to the author of this article](http://pragmaticjoe.blogspot.ru/2015/05/using-bpg-image-format-on-android.html)

[BPG](https://bellard.org/bpg/)

[GIT:react-native-bpg-example](https://github.com/nosshar/react-native-bpg-example)

[NPM:react-native-bpg-example](https://www.npmjs.com/package/react-native-bpg-example)


## ToDo list
> filter getList with .bpg$ pattern

> add png support

> add gif support

> add download from web (check with self-signed ssl and provide option to enable skip-checking)

> add methods for ```*AsPromise```, rename current methods to ```*AsCallback```

> rename methods for AsCallback

## Troubleshooting

If native libs are not building, try several techniques to clean cache:

npm start -- --reset-cache

remove node_modules and install dependencies again with npm i

On Windows
remove ```%USER_HOME%.gradle\caches\```

On Mac or Linux: ```> rm -rf $HOME/.gradle/caches/```

On Both

```> cd android```

```> gradlew cleanBuildCache```

then rebuild an application.