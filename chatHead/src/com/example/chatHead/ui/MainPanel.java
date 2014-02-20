package com.example.chatHead.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.chatHead.service.ChatHeadResManager;
import com.example.chatHead.service.GestureListener;

public class MainPanel extends SurfaceView implements SurfaceHolder.Callback{

  private static final String TAG = "MainPanel";
  private final CanvasThread thread;
  private final GestureDetector gestureDetector;
  private ChatHeadView chatHeadView;
  private final OnCHTouchListener listener;
  
  public MainPanel(Context context) {
    this(context, 100, 100);
  }
  public MainPanel(Context context, int x, int y) {
    super(context);
    this.chatHeadView = new ChatHeadView(ChatHeadResManager.getInstance().getChatHeadBitmap(), x, y);
    this.thread = new CanvasThread(getHolder(), this);
    this.listener = chatHeadView;
    this.gestureDetector = new GestureDetector(context, new GestureListener(listener));
    
    setFocusable(true);
    setZOrderOnTop(true);
    getHolder().addCallback(this);
    getHolder().setFormat(PixelFormat.TRANSPARENT);
  }

  @Override public void surfaceCreated(SurfaceHolder holder) {
    Log.d(TAG, "On surfaceCreated");
    thread.setRunning(true);
    thread.start();
  }

  @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    // TODO Auto-generated method stub
    Log.d(TAG, "On surfaceChanged");
  }

  @Override public void surfaceDestroyed(SurfaceHolder holder) {
    Log.d(TAG, "On surfaceDestoryed");
    boolean retry = true;
    thread.setRunning(false);
    while (retry) {
      try {
        thread.join();
        retry = false;
      } catch (InterruptedException e) {
        //TODO: try again shutting down the tread
      }
    }
  }
  public void render(Canvas canvas) {
    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    chatHeadView.draw(canvas);
  }
  
  @Override public boolean onTouchEvent(MotionEvent event) {
    if (!gestureDetector.onTouchEvent(event) && event.getAction() == MotionEvent.ACTION_UP) {
      Log.i(TAG, "on Up");
      if (listener != null) {
        listener.onUp((int)event.getX(), (int)event.getY());
      }
    }
    return false;
  }
}
