package com.example.chatHead;

import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

public class GestureListener extends SimpleOnGestureListener {
  private static final String TAG = "GestureHelper";
  private final WindowManager.LayoutParams params;
  private final WindowManager windowManager;
  private ImageView imageView;
  
  private int initialX;
  private int initialY;
  private float initialTouchX;
  private float initialTouchY;
  
  public GestureListener(WindowManager.LayoutParams p, WindowManager wm, ImageView iv) {
    this.params = p;
    this.windowManager = wm;
    this.imageView = iv;
  }
  
  @Override 
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    Log.i(TAG, "OnScroll: " + e2.getAction() + "(" + e1.getX() + "," + e1.getY() + ")" + ",(" + e2.getX() + "," + e2.getY() + ")");
    params.x = initialX + (int) (e2.getRawX() - initialTouchX);
    params.y = initialY + (int) (e2.getRawY() - initialTouchY);
    windowManager.updateViewLayout(imageView, params);
    return false;
  }
  
  @Override 
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    Log.i(TAG, "OnFling: " + e2.getAction() + "(" + e1.getX() + "," + e1.getY() + ")" + ",(" + e2.getX() + "," + e2.getY() + ")");
    return false;
  }
  
  @Override 
  public boolean onDown(MotionEvent e) {
    Log.i(TAG, "onDown: " + e.getAction() + "(" + e.getX() + "," + e.getY() +")");
    initialX = params.x;
    initialY = params.y;
    initialTouchX = e.getRawX();
    initialTouchY = e.getRawY();
    return false;
  }
  
  @Override 
  public boolean onSingleTapUp(MotionEvent e) {
    Log.i(TAG, "onSingleTapUp" + e.getAction() + "(" + e.getX() + "," + e.getY() + ")");
    return false;
  }
  
  
}
