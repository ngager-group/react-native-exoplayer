# react-native-exoplayer
React native video player component, used for Android, iOS

## Table of contents
- [Install](#install)
  - [iOS](#ios)
  - [Android](#android)
- [Usage](#usage)

## Install

`npm install react-native-exoplayer --save`

## Link

`react-native link react-native-exoplayer`

OR

Use [rnpm](https://github.com/rnpm/rnpm) to automatically complete the installation:  
`rnpm link react-native-exoplayer`

or link manually like so:

### iOS
- Add RNVideoPlayer in ios folder to your ios project
- Open 'node_modules/react-native-exoplayer/ios/RNVideoPlayer.xcodeproj' with Xcode
- Go to Build Settings > Search Paths > Header Search Paths
- Edit the path variable from `$(SRCROOT)/../../../HuntersLog` to `$(SRCROOT)/../../../[YOUR PROJECT FOLDER NAME]`

### Android
```gradle
// file: android/settings.gradle
...

include ':react-native-exoplayer'
project(':react-native-exoplayer').projectDir = new File(settingsDir, '../node_modules/react-native-exoplayer/android')
```
```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':react-native-exoplayer')
}
```
```java
// file: android/app/src/main/java/com/<...>/MainApplication.java
...

import com.wog.videoplayer.VideoPlayerPackage;; // <-- add this import

public class MainApplication extends Application implements ReactApplication {
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new VideoPlayerPackage() // <-- add this line
        );
    }
...
}

```
## Usage

```javascript
var VideoPlayer = require('react-native-exoplayer');

/**
 * The method will launch native module
 * @param {String} url Video link
 */
VideoPlayer.showVideoPlayer(url).then(() => {
  // onReachEnd
});
