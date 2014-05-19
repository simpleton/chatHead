package com.example.chatHead.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import com.example.chatHead.ui.MainPanel;


public class ChatHeadService extends Service{
  
  private static final String TAG = "ChatHeadService";
  
  private WindowManager windowManager;
  private MainPanel panel;

  private final IBinder mBinder = new LocalBinder();

  @Override public IBinder onBind(Intent arg0) {
    Log.d(TAG, "onBind");
    return mBinder;
  }

  public class LocalBinder extends Binder {
    ChatHeadService getService() {
      return ChatHeadService.this;
    }
  }

  @Override public void onCreate() {
    super.onCreate();
    this.windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

    panel = new MainPanel(this);
    windowManager.addView(panel, 
        ChatHeadResManager.getInstance().getWholeScrennParams());
  }
  
  @Override public void onDestroy() {
    super.onDestroy();
    if (panel != null) {
      windowManager.removeView(panel);
    }
  }

  public void MinimalPannel(int x, int y) {
    if (panel != null) {
      WindowManager.LayoutParams layoutParams = ChatHeadResManager.getInstance().getChatHeadParams();
      layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
      layoutParams.x = x;
      layoutParams.y = y;
      Log.d(TAG, "Minimal Pannel x:" + x + " y:" + y);
      windowManager.updateViewLayout(panel, layoutParams);
    }
  }

  public void MaximalPannel() {
    if (panel != null) {
      windowManager.updateViewLayout(panel, ChatHeadResManager.getInstance().getWholeScrennParams());
    }
  }
}
