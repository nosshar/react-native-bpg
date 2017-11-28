package com.kornell.bpg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.facebook.react.bridge.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RNReactNativeBpgModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNReactNativeBpgModule(ReactApplicationContext reactContext) {
      super(reactContext);
      this.reactContext = reactContext;
    }

    @Override
    public String getName() {
      return "RNReactNativeBpg";
    }

    /**
     * Get list of assets
     *
     * @param path relative to asset:/
     * @param errorCallback
     * @param successCallback
     */
    @ReactMethod
    public void getList(String path, Callback errorCallback, Callback successCallback) {
        WritableArray array = new WritableNativeArray();
        try {
            for (String file : getReactApplicationContext().getAssets().list(path)) {
                array.pushString(file);
            }
        } catch (IOException e) {
            errorCallback.invoke(path);
            return;
        }
        successCallback.invoke(array);
    }

    /**
     * Get image in JPEG format encoded in given quality
     *
     * @param image image path relative to asset:/
     * @param quality JPEG output quality &lt;= 100
     * @param errorCallback
     * @param successCallback
     */
    @ReactMethod
    public void getJpegFromBPG(String image, Integer quality, Callback errorCallback, Callback successCallback) {

        long startTime = System.currentTimeMillis();
        Bitmap bitmap = getDecodedBitmap(image);
        if (bitmap == null) {
            errorCallback.invoke(image);
            return;
        }

        long stopTime = System.currentTimeMillis();
        String timeString = "read bpg " + String.valueOf(stopTime - startTime);

        startTime = System.currentTimeMillis();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        stopTime = System.currentTimeMillis();
        timeString += ", bmp->jpg " + String.valueOf(stopTime - startTime);

        successCallback.invoke(
                "Image read " + String.valueOf(bitmap.getWidth()) + "x" + String.valueOf(bitmap.getHeight()) + " in " + timeString,
                "data:image/jpeg;base64," + Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT | Base64.NO_PADDING | Base64.NO_WRAP),
                bitmap.getWidth(),
                bitmap.getHeight()
        );
    }

    private static byte[] toByteArray(InputStream input) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

    private Bitmap getDecodedBitmap(String file) {
        Bitmap bm = null;
        try (InputStream is = getReactApplicationContext().getAssets().open(file)) {
            byte[] byteArray = toByteArray(is);
            byte[] decBuffer = null;
            int decBufferSize = 0;
            decBuffer = DecoderWrapper.decodeBuffer(byteArray, byteArray.length);
            decBufferSize = decBuffer.length;
            if (decBuffer != null) {
                bm = BitmapFactory.decodeByteArray(decBuffer, 0, decBufferSize);
            }
        } catch (IOException ignored) {
        }
        return bm;
    }
}