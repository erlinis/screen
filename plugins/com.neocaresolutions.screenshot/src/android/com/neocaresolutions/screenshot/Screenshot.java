package com.neocaresolutions.screenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

public class Screenshot extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if( "saveScreenshot".equals( action ) ) {
      System.out.println( "Screenshot called." );
      saveScreenshot(callbackContext);
      return true;
    }

    return false;
  }

  // inspired by https://github.com/purplecabbage/phonegap-plugins/blob/master/Android/Screenshot/v2.0.0/src/org/apache/cordova/Screenshot.java
  private void saveScreenshot(final CallbackContext callbackContext) {
    cordova.getActivity().runOnUiThread(new Runnable() {
        public void run() {
            View view = webView.getRootView();
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            try {
              File folder = new File(Environment.getExternalStorageDirectory(), "Screenshot");
              if (!folder.exists()) {
                folder.mkdirs();
              }

              File f = new File(folder, "screenshot_" + System.currentTimeMillis() + ".png");

              FileOutputStream fos = new FileOutputStream(f);
              bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
              fos.flush();
              fos.close();
              scanPhoto(f.toString());
              callbackContext.success();

            } catch (IOException e) {
              callbackContext.error("Cannot save the screenshot.");
            }
        }
    });
  }
  
  // http://jbkflex.wordpress.com/2012/12/23/saving-image-to-android-devices-gallery-phonegap-android/
  private void scanPhoto(String imageFileName) {
    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    File f = new File(imageFileName);
    Uri contentUri = Uri.fromFile(f);
    mediaScanIntent.setData(contentUri);
    this.cordova.getActivity().sendBroadcast(mediaScanIntent); 
  }
}