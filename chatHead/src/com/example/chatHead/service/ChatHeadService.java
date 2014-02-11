package com.example.chatHead.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadService extends Service{
  
  private static final String TAG = "ChatHeadService";
  
  private WindowManager windowManager;
  
  @Override public IBinder onBind(Intent arg0) {
    Log.d(TAG, "onBind");
    return null;
  }
 
  @Override public void onCreate() {
    super.onCreate();
    this.windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    
    final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.TYPE_PHONE,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);
    
    params.gravity = Gravity.TOP | Gravity.LEFT;
    params.x = 0;
    params.y = 100;
    ImageView chatHead = (ImageView) ChatHeadResManager.getInstance().getChatHeadViewGroup();
    windowManager.addView(chatHead, params);
    
    initChatHeadTouchListener(params);
    
    initChatHeadClickListener();
    
  }

  private void initChatHeadTouchListener(final WindowManager.LayoutParams params) {
    try {
      final ImageView chatHead = (ImageView) ChatHeadResManager.getInstance().getChatHeadViewGroup();
      if (chatHead == null) return;
      
      chatHead.setOnTouchListener(new View.OnTouchListener() {
        private WindowManager.LayoutParams paramsF = params;
        private int initialX;
        private int initialY;
        private float initialTouchX;
        private float initialTouchY;
        
        @Override public boolean onTouch(View v, MotionEvent event) {
          switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
              initialX = paramsF.x;
              initialY = paramsF.y;
              initialTouchX = event.getRawX();
              initialTouchY = event.getRawY();
              break;
            case MotionEvent.ACTION_UP:
              break;
            case MotionEvent.ACTION_MOVE:
              paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
              paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
              windowManager.updateViewLayout(chatHead, paramsF);
              break;
            default:
              break;
          }
          return false;
        }
      });
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    }
  }

  private void initChatHeadClickListener() {
    final ImageView chatHead = (ImageView) ChatHeadResManager.getInstance().getChatHeadViewGroup();
    if (chatHead != null) {
      chatHead.setOnClickListener(ChatHeadResManager.getInstance().getChatHeadClickListener());
    }
    
  }
  
  @Override public void onDestroy() {
    super.onDestroy();
    View chatHead = ChatHeadResManager.getInstance().getChatHeadViewGroup();
    if (chatHead != null) {
      windowManager.removeView(chatHead);
    }
  }
}
