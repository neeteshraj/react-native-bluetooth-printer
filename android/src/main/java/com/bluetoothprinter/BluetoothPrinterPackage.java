package com.bluetoothprinter;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bluetoothprinter.escpos.RNBluetoothEscposPrinterModule;
import com.bluetoothprinter.tsc.RNBluetoothTscPrinterModule;

public class BluetoothPrinterPackage implements ReactPackage {
  @NonNull
  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      BluetoothService service = new BluetoothService(reactContext);
      return Arrays.<NativeModule>asList(new RNBluetoothManagerModule(reactContext, service),
              new RNBluetoothEscposPrinterModule(reactContext, service),
              new RNBluetoothTscPrinterModule(reactContext, service));
  }

  // Deprecated from RN 0.47
  public List<Class<? extends JavaScriptModule>> createJSModules() {
      return Collections.emptyList();
  }

  @NonNull
  @Override
  public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }
}
