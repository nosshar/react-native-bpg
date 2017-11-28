'use strict';

import { NativeModules } from 'react-native';

const { RNReactNativeBpg } = NativeModules;

const Bpg = {

  getList: function(path, errorCallback, successCallback) {
    return RNReactNativeBpg.getList(path, errorCallback, successCallback);
  },

  getJpegFromBPG(image, quality, errorCallback, successCallback) {
    return RNReactNativeBpg.getJpegFromBPG(image, quality, errorCallback, successCallback);
  }

};

export default Bpg;
