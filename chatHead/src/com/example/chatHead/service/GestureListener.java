package com.example.chatHead.service;

import com.example.chatHead.ui.OnCHTouchListener;

import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class GestureListener extends SimpleOnGestureListener {
  private static final String TAG = "GestureHelper";
  private final OnCHTouchListener listener;
  
  public interface OnSingleClickListener {
    public void onClick();
  }
  
  public GestureListener(final OnCHTouchListener l) {
    this.listener = l;
  }
  
  @Override 
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    //Log.i(TAG, "OnScroll: " + e2.getAction() + "(" + e1.getX() + "," + e1.getY() + ")" + ",(" + e2.getX() + "," + e2.getY() + ")");
    if (listener != null) {
      listener.onMove((int) e2.getX(), (int) e2.getY());
    }
    return false;
  }
  
  @Override 
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    //Log.i(TAG, "OnFling: " + e2.getAction() + "(" + e1.getX() + "," + e1.getY() + ")" + ",(" + e2.getX() + "," + e2.getY() + ")");
    return false;
  }
  
  @Override 
  public boolean onDown(MotionEvent e) {
    Log.i(TAG, "onDown: " + e.getAction() + "(" + e.getX() + "," + e.getY() +")");
    if (listener != null) {
      listener.onDown((int) e.getX(), (int) e.getY());
    }
    return false;
  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    Log.i(TAG, "onSingleTapUp" + e.getAction() + "(" + e.getX() + "," + e.getY() + ")");
    ChatHeadResManager.getInstance().getChatHeadClickListener().onClick();
    return false;
  }
}
