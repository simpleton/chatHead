package com.example.chatHead.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


public class ChatHeadService extends Service{
  
  private static final String TAG = "ChatHeadService";
  
  private WindowManager windowManager;
  private GestureDetector gestureDetector;
  
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
  }

  private void initChatHeadTouchListener(final WindowManager.LayoutParams params) {
    final ImageView chatHead = (ImageView) ChatHeadResManager.getInstance().getChatHeadViewGroup();
    if (chatHead == null) return;
    this.gestureDetector = new GestureDetector(this, new GestureListener(params, windowManager, chatHead));
    chatHead.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        if (!gestureDetector.onTouchEvent(event) && event.getAction() == MotionEvent.ACTION_UP) {
          Log.i(TAG, "on Up");
        }
        return false;
      }
    });
  }
  
  @Override public void onDestroy() {
    super.onDestroy();
    View chatHead = ChatHeadResManager.getInstance().getChatHeadViewGroup();
    if (chatHead != null) {
      windowManager.removeView(chatHead);
    }
  }
}
