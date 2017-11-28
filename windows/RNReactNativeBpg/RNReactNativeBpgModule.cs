using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace React.Native.Bpg.RNReactNativeBpg
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNReactNativeBpgModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNReactNativeBpgModule"/>.
        /// </summary>
        internal RNReactNativeBpgModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNReactNativeBpg";
            }
        }
    }
}
