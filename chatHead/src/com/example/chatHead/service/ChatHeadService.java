package com.example.chatHead.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.chatHead.ui.MainPanel;


public class ChatHeadService extends Service{
  
  private static final String TAG = "ChatHeadService";
  
  private WindowManager windowManager;
  private MainPanel panel;
  
  @Override public IBinder onBind(Intent arg0) {
    Log.d(TAG, "onBind");
    return null;
  }
 
  @Override public void onCreate() {
    super.onCreate();
    this.windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

    panel = new MainPanel(this);
    windowManager.addView(panel, 
        ChatHeadResManager.getInstance().getWindowManagerParams());
  }
  
  @Override public void onDestroy() {
    super.onDestroy();
    if (panel != null) {
      windowManager.removeView(panel);
    }
  }
}
